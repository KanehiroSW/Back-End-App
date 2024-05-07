package com.app.Backend.Auth.AuthTienda;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginTiendaRequest {
    String dniPropietario;
    String password;
}
