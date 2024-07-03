package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, Long> {

    /*ENCONTRAR UN REPARTIDOR POR EMAIL*/
    @Query(" SELECT R FROM Repartidor R WHERE R.dni = :dni ")
    Optional<Repartidor> findRepartidorByDni(@Param("dni") String dni);
}
