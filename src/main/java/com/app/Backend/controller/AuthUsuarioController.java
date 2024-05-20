package com.app.Backend.controller;

import com.app.Backend.persistence.entities.Usuario;
import com.app.Backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth/usuario")
public class AuthUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registerUser(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.registerUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> loginUser(@RequestBody Usuario usuario) {
        Optional<Usuario> validatedUser = usuarioService.validateUser(usuario.getDni(), usuario.getPassword());
        if (validatedUser.isPresent()) {
            return ResponseEntity.ok(validatedUser.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
