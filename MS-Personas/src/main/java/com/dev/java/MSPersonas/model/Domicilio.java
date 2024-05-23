package com.dev.java.MSPersonas.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "domicilio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddomicilio;

    @ManyToOne
    @JoinColumn(name = "persnum", nullable = false)
    private Usuario usuario;

    private String calle;
    private String numero;
    private String provincia;
    private String localidad;

}