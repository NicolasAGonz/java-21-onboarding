package com.dev.java.MSTarjetas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "tarjetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta {
    @Id
    @Column(nullable = false, length = 30)
    private String numtarj;

    @Column(nullable = false)
    private int numcue;

    @Column(nullable = false, length = 10)
    private String f_vencimiento;

    @Column(nullable = false)
    private int pin;

    @ManyToOne
    @JoinColumn(name = "estado", nullable = false)
    private EstadoTarjeta estado;

    @Column(nullable = false, length = 10)
    private String f_emision;

    @Column(nullable = false, length = 1)
    private String tipo;
}
