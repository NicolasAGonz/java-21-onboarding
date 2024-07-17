package com.dev.java.MSPersonas.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record DomicilioDTO (
        @NotNull String calle,
        @NotNull String numero,
        @NotNull String provincia,
        @NotNull String localidad
){}
