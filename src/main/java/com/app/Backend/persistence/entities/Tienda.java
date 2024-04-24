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

    private String nombre;
    private String direccion;
    private String informacionContacto;
    private String imagen;

    @OneToMany(mappedBy = "tienda")
    private List<Producto> productos;
}
