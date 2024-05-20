package com.app.Backend.controller;


import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/auth/tienda")
public class AuthTiendaController {

    @Autowired
    private TiendaService tiendaService;

    @PostMapping("/register")
    public ResponseEntity<Tienda> registerStore(@RequestPart("tienda") Tienda tienda, @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(tiendaService.registerTienda(tienda,file));
    }

    @PostMapping("/login")
    public ResponseEntity<Tienda> loginStore(@RequestBody Tienda tienda) {
        Optional<Tienda> validatedUser = tiendaService.validateUser(tienda.getDniPropietario(), tienda.getPassword());
        if (validatedUser.isPresent()) {
            return ResponseEntity.ok(validatedUser.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
