package com.app.Backend.service;

import com.app.Backend.persistence.entities.Repartidor;

import java.util.Optional;

public interface RepartidorService {
    Optional<Repartidor> getRepartidorById(Long repartidorId);
    Repartidor registerRepartidor(Repartidor usuario);
    Optional<Repartidor> validateUser(String dni,String password);
}
