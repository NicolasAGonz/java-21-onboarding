package com.dev.java.MSPersonas.service;


import com.dev.java.MSPersonas.dto.NewUserWithProductDTO;
import com.dev.java.MSPersonas.dto.UsuarioDTO;
import com.dev.java.MSPersonas.kafka.KafkaProducer;
import com.dev.java.MSPersonas.model.EstadoUsuario;
import com.dev.java.MSPersonas.model.Producto;
import com.dev.java.MSPersonas.model.Usuario;
import com.dev.java.MSPersonas.repository.DomicilioRepository;
import com.dev.java.MSPersonas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.*;


@Service
@RequiredArgsConstructor
public class UsuarioService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;
    private final DomicilioRepository domicilioRepository;
    private final KafkaProducer kafkaProducer;
    private final NodeServiceClient nodeServiceClient;
    private final ProductTableService productTableService;

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            logger.info("INICIO DEL TRY, NUEVO VTHREAD CREADO");

            Callable<Usuario> userCreationTask = createUserTask(usuarioDTO, usuarioRepository);
            Future<Usuario> newUserFuture = executorService.submit(userCreationTask); //end of submit
            Usuario nuevoUsuario = newUserFuture.get();

            //Consultar al servicio de veraz y matriz de productos
            Optional<Usuario> createdUserOpt = usuarioRepository.findByDni(nuevoUsuario.dni());
            //TODO: guardar el domicilio

            String createdUserDNI = createdUserOpt.get().getDni();
            int createdUserPersnum = createdUserOpt.get().getPersnum();

            logger.info("LLAMANDO A LOS SERVICIOS NODE CON EL DNI DE USUARIO: " + createdUserDNI );

            //Llamar a los servicios
            String worldsysData = nodeServiceClient.getWorldsysData(createdUserDNI);
            String verazData = nodeServiceClient.getVerazData(createdUserDNI);
            String renaperData = nodeServiceClient.getRenaperData(createdUserDNI);

            //consultar la tabla de productos y obtener el producto correspondiente

            BigDecimal sueldoBruto = usuarioDTO.sueldoBruto();

            logger.info("CONSULTANDO LA TABLA DE PRODUCTOS CON LOS SIGUIENTES DATOS");
            logger.info("worldsysData: " + worldsysData);
            logger.info("verazData: " + verazData);
            logger.info("renaperData" + renaperData);

            Producto producto = productTableService.getProduct(sueldoBruto, worldsysData, verazData, renaperData );

            logger.info("SE HA CONSULTADO A LA TABLA DE PRODUCTOS Y SE IDENTIFICARON LOS SIGUIENTES PRODUCTOS PARA EL CLIENTE");
            logger.info(producto.toString());

            //Notificar a los servicios de Cuentas y Tarjetas, pasando el nuevo DTO con toda la info del usuario creado necesaria + el producto a crear

            NewUserWithProductDTO newUserWithProduct = NewUserWithProductDTO.builder()
                    .persnum(createdUserPersnum)
                    .dni(createdUserDNI)
                    .producto(producto)
                    .build();

            logger.info("ENVIANDO MENSAJE MEDIANTE KAFKA PARA EL NUEVO USUARIO CREADO CON PROUCTOS");
            logger.info(newUserWithProduct.toString());

            kafkaProducer.sendNewUserWithProductMessage(newUserWithProduct);

            return nuevoUsuario;
        } //END OF TRY
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Callable<Usuario> createUserTask (UsuarioDTO usuarioDTO, UsuarioRepository usuarioRepository) {

        return () -> {

            logger.warn("TASK INVOCADA: createUserTask");
            Optional<Usuario> existingUserOpt = usuarioRepository.findByDni(usuarioDTO.dni());

            if (existingUserOpt.isPresent()) {
                Usuario existingUser = existingUserOpt.get();
                String estadoDescripcion = existingUser.getEstadoUsuario().getDescripcion();

                switch (estadoDescripcion) {
                    case "Activo":
                        throw new IllegalArgumentException("El usuario ya está dado de alta.");
                    case "Bloqueado":
                        throw new IllegalArgumentException("El usuario está bloqueado.");
                    case "Inactivo":
                        throw new IllegalArgumentException("El usuario está Inactivo.");
                    case "Suspendido":
                        throw new IllegalArgumentException("El usuario está Suspendido.");
                    case "Cancelado":
                        // Reactivar el usuario
                        existingUser.setEstadoUsuario(new EstadoUsuario(1, "Activo"));
                        existingUser.setNombre(usuarioDTO.nombre());
                        existingUser.setApellido(usuarioDTO.apellido());
                        usuarioRepository.save(existingUser);
                        return existingUser;
                    default:
                        throw new IllegalArgumentException("Estado desconocido: " + estadoDescripcion);
                }
            } else {

                Usuario nuevoUsuario = Usuario.builder()
                        .nombre(usuarioDTO.nombre())
                        .apellido(usuarioDTO.apellido())
                        .dni(usuarioDTO.dni())
                        .estadoUsuario(new EstadoUsuario(1, "Activo"))
                        .tipoUsuario(usuarioDTO.tipoUsuario())
                        .build();

                logger.warn("GUARDANDO NUEVO USUARIO", nuevoUsuario);

                usuarioRepository.save(nuevoUsuario);

                return nuevoUsuario;
            }
        };
    }

}



