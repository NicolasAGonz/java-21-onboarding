package com.dev.java.MSPersonas.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int persnum;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String dni;

    @ManyToOne
    @JoinColumn(name = "estado", nullable = false)
    private EstadoUsuario estadoUsuario;

    @ManyToOne
    @JoinColumn(name = "tipo", nullable = false)
    private TipoUsuario tipoUsuario;

    // Getters y Setters
}
