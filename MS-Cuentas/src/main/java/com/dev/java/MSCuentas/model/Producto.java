package com.dev.java.MSCuentas.model;

import lombok.*;

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
