package com.dev.java.MSPersonas.model;

import lombok.*;

import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {
    private String cuenta;
    private String tarjeta;
}
