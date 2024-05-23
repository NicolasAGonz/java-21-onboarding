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
    private int idtipo_usuario;
    private String descripcion;

}
