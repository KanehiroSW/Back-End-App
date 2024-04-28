package com.app.Backend.persistence.entities.Usuario;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {
    private Long idUsuario;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String usuario;
}
