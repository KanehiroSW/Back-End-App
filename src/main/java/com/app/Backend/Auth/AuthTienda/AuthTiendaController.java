package com.app.Backend.Auth.AuthTienda;

import com.app.Backend.persistence.entities.Tienda.Tienda;
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

//    @PostMapping(value = "register")
//    public ResponseEntity<AuthTiendaResponse> register(@RequestBody RegisterTiendaRequest request,MultipartFile file) throws IOException {
//        return ResponseEntity.ok(authService.register(request, file));
//    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthTiendaResponse> saveTienda(@RequestPart("tienda") RegisterTiendaRequest request, @RequestPart("file") MultipartFile file) {
        try {
            AuthTiendaResponse savedTienda = authService.register(request, file);
            return ResponseEntity.ok(savedTienda);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
