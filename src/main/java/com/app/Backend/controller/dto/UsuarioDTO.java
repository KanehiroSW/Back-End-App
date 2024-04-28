package com.app.Backend.controller.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private Long idUsuario;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String usuario;
}
