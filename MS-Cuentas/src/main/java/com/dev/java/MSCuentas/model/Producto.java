package com.dev.java.MSCuentas.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class Producto {
    private String cuenta;
    private String tarjeta;

    @JsonCreator
    public Producto(@JsonProperty("cuenta") String cuenta,
                    @JsonProperty("tarjeta") String tarjeta) {
        this.cuenta = cuenta;
        this.tarjeta = tarjeta;
    }

}
