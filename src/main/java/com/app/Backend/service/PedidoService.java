package com.app.Backend.service;

import com.app.Backend.persistence.entities.*;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> getAllPedidos();
    Pedido savePedido(Pedido pedido);
    String generarNumeroSerie();
    Optional<Pedido> getPedidoById(Long idPedido);
    List<Pedido> getHistorialPedidos(Usuario usuario);
    List<Pedido> getPendingPedidos(Usuario usuario);
    List<Pedido> getPedidosByTienda(Tienda tienda);
    List<Pedido> getPendingPedidosByTienda(Tienda tienda); // Nuevo método
    Pedido updatePedidoStatus(Long idPedido, EstadoPedido estado); // Nuevo método
}
