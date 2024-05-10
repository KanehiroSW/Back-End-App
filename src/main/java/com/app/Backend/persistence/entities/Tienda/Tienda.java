package com.app.Backend.persistence.entities.Tienda;

import com.app.Backend.persistence.entities.*;
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
public class Tienda implements UserDetails {
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
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "tienda")
    private List<Producto> productos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return dniPropietario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
