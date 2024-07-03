package com.app.Backend.controller;

import com.app.Backend.persistence.entities.Repartidor;
import com.app.Backend.persistence.entities.Usuario;
import com.app.Backend.service.RepartidorService;
import com.app.Backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth/repartidor")
public class AuthRepartidorController {

    @Autowired
    private RepartidorService repartidorService;

    @PostMapping("/register")
    public ResponseEntity<Repartidor> registerUser(@RequestBody Repartidor repartidor) {
        return ResponseEntity.ok(repartidorService.registerRepartidor(repartidor));
    }

    @PostMapping("/login")
    public ResponseEntity<Repartidor> loginUser(@RequestBody Repartidor repartidor) {
        Optional<Repartidor> validatedUser = repartidorService.validateUser(repartidor.getDni(), repartidor.getPassword());
        if (validatedUser.isPresent()) {
            return ResponseEntity.ok(validatedUser.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
