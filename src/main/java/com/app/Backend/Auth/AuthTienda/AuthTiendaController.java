package com.app.Backend.Auth.AuthTienda;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<AuthTiendaResponse> register(@RequestBody RegisterTiendaRequest request, @RequestParam("img") MultipartFile file) throws IOException {
        return ResponseEntity.ok(authService.register(request, file));
    }
}
