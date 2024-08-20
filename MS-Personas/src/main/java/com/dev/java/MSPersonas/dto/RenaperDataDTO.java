package com.dev.java.MSPersonas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RenaperDataDTO{
    @NotNull String dni;
    @NotNull Boolean isAuthorize;

}
