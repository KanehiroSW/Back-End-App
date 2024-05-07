package com.app.Backend.controller.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TiendaDTO {
    private Long idTienda;
    private String nombreTienda;
    private String nombrePropietario;
    private String dniPropietario;
    private String direccion;
    private String email;
    private String telefono;
    private String imagen;
}
