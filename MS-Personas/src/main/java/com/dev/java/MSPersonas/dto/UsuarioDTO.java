package com.dev.java.MSPersonas.dto;

import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        @NotNull String nombre,
        @NotNull String apellido,
        @NotNull String dni,
        @NotNull int estadoId,
        @NotNull int tipoId
) {}
