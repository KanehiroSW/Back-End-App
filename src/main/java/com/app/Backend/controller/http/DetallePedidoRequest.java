package com.app.Backend.controller.http;

import com.app.Backend.persistence.entities.Producto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallePedidoRequest {
    private Producto producto;
    private int cantidad;
}
