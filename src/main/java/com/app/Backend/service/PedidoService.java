package com.app.Backend.service;

import com.app.Backend.persistence.entities.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> getAllPedidos();
    Optional<Pedido> getPedidoById(Long idPedido);
    Pedido savePedido(Pedido pedido);
    void deletePedidoById(Long idPedido);
}
