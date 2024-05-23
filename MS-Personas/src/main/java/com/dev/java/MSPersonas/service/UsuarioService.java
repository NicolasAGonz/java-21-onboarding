package com.dev.java.MSPersonas.service;

import com.dev.java.MSPersonas.dto.UsuarioDTO;
import com.dev.java.MSPersonas.model.EstadoUsuario;
import com.dev.java.MSPersonas.model.TipoUsuario;
import com.dev.java.MSPersonas.model.Usuario;
import com.dev.java.MSPersonas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public CompletableFuture<String> validarUsuario(UsuarioDTO usuarioDTO) {

        return CompletableFuture.supplyAsync(() -> {

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
                    case "Suspendido":
                    case "Cancelado":
                        // Reactivar el usuario
                        existingUser.setEstadoUsuario(new EstadoUsuario(usuarioDTO.estadoId(), ""));
                        existingUser.setNombre(usuarioDTO.nombre());
                        existingUser.setApellido(usuarioDTO.apellido());
                        usuarioRepository.save(existingUser);
                        return "Usuario reactivado correctamente.";
                    default:
                        throw new IllegalArgumentException("Estado desconocido: " + estadoDescripcion);
                }
            } else {
                throw new IllegalArgumentException("El usuario no existe.");
            }
        });
    }
}
