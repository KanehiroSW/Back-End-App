package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Tienda.Tienda;
import com.app.Backend.persistence.entities.Usuario.Usuario;
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
    List<Tienda> findAllTienda();

    /*OBTENER TIENDA POR DNI DEL PROPIETARIO*/
    @Query(" SELECT T FROM Tienda T WHERE T.dniPropietario = :dniPropietario ")
    Optional<Tienda> findTiendaByDni(@Param("dniPropietario") String dniPropietario);


    /*OBTENER UNA TIENDA POR SU ID*/
    @Query(" SELECT T FROM Tienda T WHERE T.idTienda = :idTienda ")
    Optional<Tienda> findTiendaById(@Param("idTienda") Long idTienda);

    @Modifying()
    @Query(" UPDATE Tienda T SET T.nombreTienda = :nombreTienda," +
            "T.nombrePropietario = :nombrePropietario," +
            "T.dniPropietario = :dniPropietario," +
            "T.direccion= :direccion," +
            "T.email= :email," +
            "T.telefono = :telefono," +
            "T.imagen = :imagen WHERE T.idTienda = :idTienda" )
    void updateTienda(@Param(value = "idTienda") Long idTienda,
                       @Param(value = "nombreTienda") String nombreTienda,
                       @Param(value = "nombrePropietario") String nombrePropietario,
                       @Param(value = "dniPropietario") String dniPropietario,
                       @Param(value = "direccion") String direccion,
                       @Param(value = "email") String email,
                       @Param(value = "telefono") String telefono,
                       @Param(value = "imagen") String imagen);

    /*DESACTIVAR TIENDA*/
    @Transactional
    @Modifying
    @Query(" UPDATE Tienda T SET T.estado = 0 WHERE T.idTienda = :idTienda ")
    void disableTienda(@Param("idTienda") Long idTienda);
}
