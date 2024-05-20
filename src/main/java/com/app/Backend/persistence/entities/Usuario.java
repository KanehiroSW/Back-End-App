package com.app.Backend.persistence.entities;

import com.app.Backend.persistence.entities.Pedido;
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
public class Usuario implements UserDetails {
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
    private String usuario;
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    private int estado;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return dni;
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
