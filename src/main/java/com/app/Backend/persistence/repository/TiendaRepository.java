package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    /*LISTAR TODOS LOS TIENDA*/
    @Query(" SELECT T FROM Tienda T WHERE T.estado = 1 ")
    List<Tienda> findAllTienda();

    /*DESACTIVAR TIENDA*/
    @Transactional
    @Modifying
    @Query(" UPDATE Tienda T SET T.estado = 0 WHERE T.idTienda = :idTienda ")
    void disableTienda(@Param("idTienda") Long idTienda);
}
