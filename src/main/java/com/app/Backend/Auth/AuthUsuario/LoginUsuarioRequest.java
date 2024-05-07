package com.app.Backend.Auth.AuthUsuario;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUsuarioRequest {
    String dni;
    String password;
}
