package com.dev.java.MSCuentas.dto;

import com.dev.java.MSCuentas.model.Producto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewUserWithProductDTO {
    private int persnum;
    private String dni;
    private Producto producto;
}
