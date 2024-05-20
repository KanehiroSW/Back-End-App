package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Tienda;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {
    /*LISTAR TODOS LOS TIENDA*/
    @Query(" SELECT T FROM Tienda T WHERE T.estado = 1 ")
    List<Tienda> findAllTiendas();

    /*OBTENER TIENDA POR DNI DEL PROPIETARIO*/
    @Query(" SELECT T FROM Tienda T WHERE T.dniPropietario = :dniPropietario ")
    Optional<Tienda> findTiendaByDni(@Param("dniPropietario") String dniPropietario);

    /*DESACTIVAR TIENDA*/
    @Transactional
    @Modifying
    @Query(" UPDATE Tienda T SET T.estado = 0 WHERE T.idTienda = :idTienda ")
    void disableTienda(@Param("idTienda") Long idTienda);
}
