package com.app.Backend.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_repartidores"
)
public class Repartidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRepartidor;

    @Column(unique = true, nullable = false)
    private String dni;

    private String nombre;

    private String apellido;

    private String telefono;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @OneToMany(mappedBy = "repartidor")
    private List<Pedido> pedidos;

    private int estado;
}