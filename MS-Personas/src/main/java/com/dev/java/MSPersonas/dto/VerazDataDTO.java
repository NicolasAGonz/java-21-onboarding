package com.dev.java.MSPersonas.dto;

import jakarta.validation.constraints.NotNull;

public record VerazDataDTO(
        @NotNull String dni,
        @NotNull double score
) {}
