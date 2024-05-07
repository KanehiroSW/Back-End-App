package com.app.Backend.Auth.AuthTienda;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTiendaRequest {
    private String nombreTienda;
    private String nombrePropietario;
    private String dniPropietario;
    private String direccion;
    private String email;
    private String telefono;
    private String imagen;
    private String password;
}
