package com.app.Backend.controller.http;

import com.app.Backend.persistence.entities.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoRequest {
    private String direccionEntrega;
    private Usuario usuario;
    private Tienda tienda;
    private List<DetallePedidoRequest> detalles;
}
