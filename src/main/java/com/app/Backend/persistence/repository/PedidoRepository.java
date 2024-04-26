package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(" SELECT P FROM Pedido P WHERE P.estado = 1 ")
    List<Pedido> findAllPedidos();

    @Transactional
    @Modifying
    @Query(" UPDATE Pedido P SET P.estado = 0 WHERE P.idPedido = :idPedido ")
    void disableProducto(@Param("idPedido") Long idPedido);
}
