package com.app.Backend.service;

import com.app.Backend.persistence.entities.Pedido;
import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.persistence.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> getAllPedidos();
    Pedido savePedido(Pedido pedido);
    String generarNumeroSerie();
    Optional<Pedido> getPedidoById(Long idPedido);
    List<Pedido> getPedidosByUsuario(Usuario usuario);
    List<Pedido> getPedidosByTienda(Tienda tienda);
}
