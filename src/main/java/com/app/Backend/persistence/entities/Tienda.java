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
        name = "tbl_tiendas"
)
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tienda")
    private Long idTienda;

    @Column(name = "nombre_tienda")
    private String nombreTienda;
    private String nombrePropietario;
    private String dniPropietario;
    private String ruc;
    private String direccion;
    private String telefono;
    private String imagen;
    private String usuario;
    private String password;
    private int estado;

    @OneToMany(mappedBy = "tienda")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "tienda")
    private List<Producto> productos;
}
