package com.app.Backend.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_usuarios"
)
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
    private String email;
    private String usuario;
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
    @OneToOne(mappedBy = "usuario")
    private HistorialPedidos historialPedidos;
}
