package com.dev.java.MSTarjetas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "estado_tarjeta")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTarjeta {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String detalle;
}
