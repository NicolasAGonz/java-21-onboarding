package com.dev.java.MSPersonas.dto;

import com.dev.java.MSPersonas.model.Producto;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class NewUserWithProductDTO {
    int persnum;
    String dni;
    Producto producto;
}
