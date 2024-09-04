package com.dev.java.MSCuentas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "estado_cuenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class EstadoCuenta implements Serializable {
    @Id
    private int id;
    private String detalle;

}
