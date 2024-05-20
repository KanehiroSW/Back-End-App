package com.app.Backend.service;

import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.persistence.entities.Usuario;
import com.app.Backend.persistence.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TiendaServiceImpl implements TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Tienda> getAllTiendas() {
        return tiendaRepository.findAllTiendas();
    }

    @Override
    public Optional<Tienda> getTiendaById(Long tiendaId) {
        return tiendaRepository.findById(tiendaId);
    }

    @Override
    public Tienda registerTienda(Tienda tienda, MultipartFile file) {

        Tienda tiendaExistente = tiendaRepository.findTiendaByDni(tienda.getDniPropietario()).orElse(null);
        if (tiendaExistente != null) {
            throw new RuntimeException("La Tienda ya existe");
        }

        try {
            String imageUrl = uploadFileService.saveImageTienda(file);
            tienda.setImagen(imageUrl);
            tienda.setPassword(passwordEncoder.encode(tienda.getPassword()));
            tienda.setEstado(1);
            return tiendaRepository.save(tienda);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }
    }

    @Override
    public Optional<Tienda> validateUser(String dniPropietario, String password) {
        Optional<Tienda> tienda = tiendaRepository.findTiendaByDni(dniPropietario);
        if (tienda.isPresent() && passwordEncoder.matches(password, tienda.get().getPassword())) {
            return tienda;
        }
        return Optional.empty();
    }
}
