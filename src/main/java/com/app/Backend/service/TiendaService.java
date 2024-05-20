package com.app.Backend.service;

import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.persistence.entities.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface TiendaService {
    List<Tienda> getAllTiendas();
    Optional<Tienda> getTiendaById(Long tiendaId);
    Tienda registerTienda(Tienda tienda, MultipartFile file);
    Optional<Tienda> validateUser(String dniPropietario, String password);
}
