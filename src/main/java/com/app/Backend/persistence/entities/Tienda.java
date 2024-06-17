package com.app.Backend.persistence.entities;

import com.app.Backend.persistence.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
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

    @Column(
            name = "nombre_tienda",
            unique = true,
            nullable = false
    )
    private String nombreTienda;
    private String nombrePropietario;

    @Column(
            unique = true,
            nullable = false
    )
    private String dniPropietario;
    private String direccion;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    private String telefono;
    private String imagen;
    private String password;
    private int estado;

    @OneToMany(mappedBy = "tienda")
    @JsonIgnore
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "tienda")
    @JsonIgnore
    private List<Producto> productos;
}
