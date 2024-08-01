package com.dev.java.MSPersonas.model;

import lombok.*;

import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private String cuenta;
    private Optional<String> tarjeta;
}
