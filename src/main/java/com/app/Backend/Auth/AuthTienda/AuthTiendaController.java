package com.app.Backend.Auth.AuthTienda;

import com.app.Backend.Auth.AuthUsuario.AuthUsuarioResponse;
import com.app.Backend.Auth.AuthUsuario.AuthUsuarioService;
import com.app.Backend.Auth.AuthUsuario.LoginUsuarioRequest;
import com.app.Backend.Auth.AuthUsuario.RegisterUsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authStore")
@RequiredArgsConstructor
@CrossOrigin
public class AuthTiendaController {
    private final AuthTiendaService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthTiendaResponse> login(@RequestBody LoginTiendaRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthTiendaResponse> register(@RequestBody RegisterTiendaRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
