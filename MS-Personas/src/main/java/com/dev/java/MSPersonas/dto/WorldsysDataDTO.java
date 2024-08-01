package com.dev.java.MSPersonas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public record WorldsysDataDTO(
        @NotNull String dni,
        @NotNull Boolean isTerrorist
) {}
