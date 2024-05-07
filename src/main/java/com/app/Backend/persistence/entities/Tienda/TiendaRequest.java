package com.app.Backend.persistence.entities.Tienda;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TiendaRequest {
    private Long idTienda;
    private String nombreTienda;
    private String nombrePropietario;
    private String dniPropietario;
    private String direccion;
    private String email;
    private String telefono;
    private String imagen;
}
