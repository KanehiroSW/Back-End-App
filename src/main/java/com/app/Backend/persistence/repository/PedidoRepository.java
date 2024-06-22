package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Pedido;
import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p WHERE p.usuario = :usuario AND (p.estadoPedido = 'ENTREGADO' OR p.estadoPedido = 'RECHAZADO')")
    List<Pedido> findHistorialPedidos(@Param("usuario") Usuario usuario);

    @Query("SELECT p FROM Pedido p WHERE p.usuario = :usuario AND p.estadoPedido NOT IN ('ENTREGADO', 'RECHAZADO')")
    List<Pedido> findPendingPedidos(@Param("usuario") Usuario usuario);

    @Query("SELECT p FROM Pedido p WHERE p.tienda = :tienda AND p.estadoPedido = 'PENDIENTE'")
    List<Pedido> findPendingPedidosByTienda(@Param("tienda") Tienda tienda);

    List<Pedido> findByTienda(Tienda tienda);

    @Query("SELECT p FROM Pedido p WHERE DATE(p.fechaPedido) = :fecha AND p.estadoPedido = 'ENTREGADO'")
    List<Pedido> findByFechaPedido(@Param("fecha") Date fecha);

    @Query("SELECT p FROM Pedido p WHERE p.tienda = :tienda AND (p.estadoPedido = 'ENTREGADO' OR p.estadoPedido = 'RECHAZADO')")
    List<Pedido> findHistorialPedidosByTienda(@Param("tienda") Tienda tienda);

    @Query("SELECT p FROM Pedido p WHERE p.tienda = :tienda AND (p.estadoPedido = 'ACEPTADO' OR p.estadoPedido = 'EN_PREPARACION')")
    List<Pedido> findPedidosEnProcesoByTienda(@Param("tienda") Tienda tienda);

    @Query("SELECT p FROM Pedido p WHERE p.tienda = :tienda AND (p.estadoPedido = 'ORDEN_LISTA' OR p.estadoPedido = 'EN_CAMINO')")
    List<Pedido> findPedidosByTiendaDelivery(@Param("tienda") Tienda tienda);
}