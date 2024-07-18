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

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {

        // 1. Abrir un nuevo thread
        // 2. Validar la existencia del usuario
        // 3. Si no existe, crearlo
        // 4. Disparar el mensaje a los otros servicios

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            Callable<Usuario> userCreationTask = createUserTask(usuarioDTO, usuarioRepository );

            Future<Usuario> newUserFuture = executorService.submit(userCreationTask); //end of submit

            Usuario nuevoUsuario = newUserFuture.get();

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


                //TODO:Kafka notif
                //kafkaTemplate.send("newUserCreatedTopic", new NewUserCreatedEvent("userData"));

                return nuevoUsuario;
            }
        };
    }

}



