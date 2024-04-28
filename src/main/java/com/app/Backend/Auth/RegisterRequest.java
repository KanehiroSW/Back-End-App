package com.app.Backend.Auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String usuario;
    private String password;
}