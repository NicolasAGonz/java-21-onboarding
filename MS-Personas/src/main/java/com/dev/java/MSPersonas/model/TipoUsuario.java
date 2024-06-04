package com.dev.java.MSPersonas.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tipo_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuario {
    @Id
    @Column(nullable = false)
    private int idtipo_usuario;

    @Column(nullable = false)
    private String descripcion;

}
