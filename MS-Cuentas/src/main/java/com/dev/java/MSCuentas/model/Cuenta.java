package com.dev.java.MSCuentas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Cuenta implements Serializable {
    @Id
    private String numcue;

    @ManyToOne
    @JoinColumn(name = "divisa")
    private CodigoMoneda divisa;

    @ManyToOne
    @JoinColumn(name = "estado")
    private EstadoCuenta estado;

    private int persnum;
    private double saldo;
}
