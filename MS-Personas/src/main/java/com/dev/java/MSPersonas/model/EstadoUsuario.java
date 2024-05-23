package com.dev.java.MSPersonas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUsuario {
    @Id
    private int idestado_usuario;
    private String descripcion;

}
