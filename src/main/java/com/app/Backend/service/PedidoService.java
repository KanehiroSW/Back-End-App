package com.app.Backend.service;

import com.app.Backend.persistence.entities.*;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> getHistorialPedidos(Usuario usuario);
    Pedido savePedido(Pedido pedido);
    String generarNumeroSerie();
    List<Pedido> getPendingPedidos(Usuario usuario);
    List<Pedido> getAllPedidos();
    Optional<Pedido> getPedidoById(Long idPedido);
    List<Pedido> getPedidosByTienda(Tienda tienda);
    List<Pedido> getPendingPedidosByTienda(Tienda tienda);
    Pedido updatePedidoStatus(Long idPedido, EstadoPedido estado);
    List<Pedido> getPedidosEnProcesoByTienda(Tienda tienda);
    List<Pedido> getPedidosByTiendaDelivery(Tienda tienda);
    List<Pedido> getHistorialPedidosByTienda(Tienda tienda);
}
