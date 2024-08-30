package com.dev.java.MSCuentas.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class Cuenta implements Serializable {
    @Id
    @Column(nullable = false)
    private String numcue;

    @ManyToOne
    @JoinColumn(name = "divisa", referencedColumnName = "cod_moneda",nullable = false)
    private CodigoMoneda codigoMoneda;

    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id",nullable = false)
    private EstadoCuenta estadoCuenta;

    @Column(nullable = false)
    private int persnum;
    @Column(nullable = false)
    private double saldo;
}
