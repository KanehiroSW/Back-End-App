package com.app.Backend.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_productos"
)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;
    private String descripcion;

    private int cantidad;
    private double precio;
    private String imagen;

    @ManyToOne
    private Tienda tienda;

    private int estado;
}
