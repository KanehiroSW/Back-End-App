package com.app.Backend.service;

import com.app.Backend.exception.ProductoNotFoundException;
import com.app.Backend.persistence.entities.Repartidor;
import com.app.Backend.persistence.repository.RepartidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepartidorServiceImpl implements RepartidorService {

    @Autowired
    private RepartidorRepository repartidorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Repartidor> getRepartidorById(Long repartidorId) {
        if (repartidorId != null) {
            return repartidorRepository.findById(repartidorId);
        } else {
            throw new ProductoNotFoundException("¡Repartidor no encontrado!");
        }
    }

    @Override
    public Repartidor registerRepartidor(Repartidor usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setEstado(1);
        return repartidorRepository.save(usuario);
    }

    @Override
    public Optional<Repartidor> validateUser(String dni, String password) {
        Optional<Repartidor> repartidor = repartidorRepository.findRepartidorByDni(dni);
        if (repartidor.isPresent() && passwordEncoder.matches(password, repartidor.get().getPassword())) {
            return repartidor;
        }
        return Optional.empty();
    }
}
