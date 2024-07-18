package com.dev.java.MSPersonas.dto;

import com.dev.java.MSPersonas.model.TipoUsuario;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        @NotNull String nombre,
        @NotNull String apellido,
        @NotNull String dni,
        @NotNull int tipoId,
        @NotNull DomicilioDTO domicilio,
        @NotNull int sueldo,
        @NotNull TipoUsuario tipoUsuario
) {}
