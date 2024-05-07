package com.app.Backend.Auth.AuthUsuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authUser")
@RequiredArgsConstructor
@CrossOrigin
public class AuthUsuarioController {
    private final AuthUsuarioService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthUsuarioResponse> login(@RequestBody LoginUsuarioRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthUsuarioResponse> register(@RequestBody RegisterUsuarioRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
