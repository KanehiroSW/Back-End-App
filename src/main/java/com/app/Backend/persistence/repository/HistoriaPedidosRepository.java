package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.HistorialPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaPedidosRepository extends JpaRepository<HistorialPedidos, Long> {
}
