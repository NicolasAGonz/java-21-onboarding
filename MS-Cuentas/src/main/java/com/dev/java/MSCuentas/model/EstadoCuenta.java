package com.dev.java.MSCuentas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "estado_cuenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class EstadoCuenta implements Serializable {
    @Id
    private int id;
    private String detalle;

}
