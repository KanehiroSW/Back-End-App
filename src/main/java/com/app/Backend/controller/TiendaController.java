package com.app.Backend.controller;

import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping("/all")
    public List<Tienda> getAllTiendas() {
        return tiendaService.getAllTiendas();
    }
}
