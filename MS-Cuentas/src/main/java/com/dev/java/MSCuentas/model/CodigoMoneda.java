package com.dev.java.MSCuentas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "codigo_moneda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class CodigoMoneda implements Serializable {
    @Id
    private int cod_moneda;
    private String pais;
    private String simbolo;

}
