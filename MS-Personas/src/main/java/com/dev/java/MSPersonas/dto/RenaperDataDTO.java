package com.dev.java.MSPersonas.dto;

import jakarta.validation.constraints.NotNull;

public record RenaperDataDTO(

        @NotNull String dni,
        @NotNull Boolean isAuthorize
) {}
