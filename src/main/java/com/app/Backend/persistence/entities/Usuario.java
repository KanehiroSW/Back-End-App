package com.app.Backend.persistence.entities;

import com.app.Backend.persistence.entities.Pedido;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_usuarios"
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(
            unique = true,
            nullable = false
    )
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            unique = true,
            nullable = false
    )
    private String nombre_usuario;
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    private int estado;
}
