package com.app.Backend.controller;

import com.app.Backend.controller.dto.TiendaDTO;
import com.app.Backend.persistence.entities.Tienda.*;
import com.app.Backend.service.TiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/tienda")
@RequiredArgsConstructor
@CrossOrigin
public class TiendaController {

    private final TiendaService tiendaService;

    @GetMapping(value = "{idTienda}")
    public ResponseEntity<TiendaDTO> getStore(@PathVariable Long idTienda)
    {
        TiendaDTO tiendaDTO = tiendaService.getTienda(idTienda);
        if (tiendaDTO == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tiendaDTO);
    }

    @PutMapping()
    public ResponseEntity<TiendaResponse> updateTienda(@RequestBody TiendaRequest tiendaRequest)
    {
        return ResponseEntity.ok(tiendaService.actualizarTienda(tiendaRequest));
    }
}
