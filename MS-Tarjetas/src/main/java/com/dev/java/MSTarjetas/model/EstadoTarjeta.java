package com.dev.java.MSTarjetas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estado_tarjeta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTarjeta {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String detalle;
}
