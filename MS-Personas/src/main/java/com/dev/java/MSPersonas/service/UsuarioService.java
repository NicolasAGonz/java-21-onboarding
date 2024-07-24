package com.dev.java.MSPersonas.service;

import com.dev.java.MSPersonas.dto.UsuarioDTO;
import com.dev.java.MSPersonas.model.EstadoUsuario;
import com.dev.java.MSPersonas.model.Usuario;
import com.dev.java.MSPersonas.repository.DomicilioRepository;
import com.dev.java.MSPersonas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.*;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DomicilioRepository domicilioRepository;
    private final KafkaTemplate kafkaTemplate;
    private static final String newUserCreatedTopic = "newUserCreatedTopic";

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            Callable<Usuario> userCreationTask = createUserTask(usuarioDTO, usuarioRepository);
            Future<Usuario> newUserFuture = executorService.submit(userCreationTask); //end of submit
            Usuario nuevoUsuario = newUserFuture.get();

            //Consultar al servicio de veraz y matriz de productos
            Optional<Usuario> createdUserOpt = usuarioRepository.findByDni(nuevoUsuario.dni());
            //TODO: guardar el domicilio, llamar al servicio de veraz, consultar la matriz del producto





            //Notificar a los servicios de Cuentas y Tarjetas, pasando el nuevo DTO con toda la info del usuario creado necesaria + el proudcto a crear
            kafkaTemplate.send(newUserCreatedTopic, "NUEVO USUARIO CREADO");

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

                usuarioRepository.save(nuevoUsuario);

                return nuevoUsuario;
            }
        };
    }

}



