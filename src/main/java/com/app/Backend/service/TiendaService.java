package com.app.Backend.service;

import com.app.Backend.controller.dto.TiendaDTO;
import com.app.Backend.controller.dto.UsuarioDTO;
import com.app.Backend.persistence.entities.Tienda.Tienda;
import com.app.Backend.persistence.entities.Tienda.TiendaRequest;
import com.app.Backend.persistence.entities.Tienda.TiendaResponse;
import com.app.Backend.persistence.entities.Usuario.Usuario;
import com.app.Backend.persistence.repository.TiendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TiendaService {
    private final TiendaRepository tiendaRepository;
    private final UploadFileService uploadFileService;

    public List<Tienda> listarTiendas() {
        return tiendaRepository.findAllTienda();
    }

    public Optional<Tienda> getTiendaById(Long idTienda) {
        return tiendaRepository.findTiendaById(idTienda);
    }

    @Transactional
    public TiendaResponse actualizarTienda(TiendaRequest tiendaRequest) {

        Tienda tienda = Tienda.builder()
                .idTienda(tiendaRequest.getIdTienda())
                .nombreTienda(tiendaRequest.getNombreTienda())
                .nombrePropietario(tiendaRequest.getNombrePropietario())
                .dniPropietario(tiendaRequest.getDniPropietario())
                .direccion(tiendaRequest.getDireccion())
                .email(tiendaRequest.getEmail())
                .telefono(tiendaRequest.getTelefono())
                .imagen(tiendaRequest.getImagen())
                .build();

        tiendaRepository.updateTienda(tienda.getIdTienda(),
                tienda.getNombreTienda(),
                tienda.getNombrePropietario(),
                tienda.getDniPropietario(),
                tienda.getDireccion(),
                tienda.getEmail(),
                tienda.getTelefono(),
                tienda.getImagen());

        return new TiendaResponse("¡La tienda se actualizó satisfactoriamente!");
    }

    public TiendaDTO getTienda(Long idTienda) {
        Tienda tienda = tiendaRepository.findById(idTienda).orElse(null);

        if (tienda != null)
        {
            TiendaDTO tiendaDTO = TiendaDTO.builder()
                    .idTienda(tienda.getIdTienda())
                    .nombreTienda(tienda.getNombreTienda())
                    .nombrePropietario(tienda.getNombrePropietario())
                    .dniPropietario(tienda.getDniPropietario())
                    .direccion(tienda.getDireccion())
                    .email(tienda.getEmail())
                    .telefono(tienda.getTelefono())
                    .imagen(tienda.getImagen())
                    .build();
            return tiendaDTO;
        }
        return null;
    }

    public TiendaResponse desactivarTienda(Long idTienda) {
        if (idTienda != null)
        {
            tiendaRepository.disableTienda(idTienda);
        }
        return new TiendaResponse("¡La tienda se ha desactivado con exito!");
    }
}
