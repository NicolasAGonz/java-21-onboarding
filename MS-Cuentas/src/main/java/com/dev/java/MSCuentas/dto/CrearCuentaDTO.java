package com.dev.java.MSCuentas.dto;


import jakarta.validation.constraints.NotNull;

public record CrearCuentaDTO(
        @NotNull String numcue,
        @NotNull int persnum,
        @NotNull int divisa,
        @NotNull int estado,
        @NotNull double saldo
) {}
