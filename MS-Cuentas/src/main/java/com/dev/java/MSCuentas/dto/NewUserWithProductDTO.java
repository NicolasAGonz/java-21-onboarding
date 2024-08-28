package com.dev.java.MSCuentas.dto;

import com.dev.java.MSCuentas.model.Producto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class NewUserWithProductDTO {
    private int persnum;
    private String dni;
    private Producto producto;

    @JsonCreator
    public NewUserWithProductDTO(@JsonProperty("persnum") int persnum,
                                 @JsonProperty("dni") String dni,
                                 @JsonProperty("producto") Producto producto) {
        this.persnum = persnum;
        this.dni = dni;
        this.producto = producto;
    }


}
