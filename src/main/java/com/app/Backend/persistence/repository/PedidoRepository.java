package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Pedido;
import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario(Usuario usuario);
    List<Pedido> findByTienda(Tienda tienda);
}
