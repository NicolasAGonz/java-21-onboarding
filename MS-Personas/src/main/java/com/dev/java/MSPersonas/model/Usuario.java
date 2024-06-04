package com.dev.java.MSPersonas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
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
