package com.app.Backend.Auth.AuthTienda;

import com.app.Backend.persistence.entities.Tienda.Tienda;
import com.app.Backend.persistence.repository.TiendaRepository;
import com.app.Backend.service.UploadFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authStore")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthTiendaController {

    private final TiendaRepository tiendaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTiendaService authService;
    private final UploadFileService uploadFileService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthTiendaResponse> login(@RequestBody LoginTiendaRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

//    @PostMapping(value = "register")
//    public ResponseEntity<AuthTiendaResponse> register(@RequestBody RegisterTiendaRequest request,MultipartFile file) throws IOException {
//        return ResponseEntity.ok(authService.register(request, file));
//    }

    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registrarUsuario(@RequestPart("tienda") Tienda tienda, @RequestPart("file") MultipartFile file) {

        Tienda tiendaExistente = tiendaRepository.findTiendaByDni(tienda.getDniPropietario()).orElse(null);
        if (tiendaExistente != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "La Tienda ya existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String imageUrl;

        try {
            imageUrl = uploadFileService.saveImageTienda(file);
        } catch (IOException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al guardar la imagen");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        Tienda tiendaBD = Tienda.builder()
                .nombreTienda(tienda.getNombreTienda())
                .nombrePropietario(tienda.getNombrePropietario())
                .dniPropietario(tienda.getDniPropietario())
                .direccion(tienda.getDireccion())
                .email(tienda.getEmail())
                .telefono(tienda.getTelefono())
                .imagen(imageUrl)
                .password(passwordEncoder.encode(tienda.getPassword()))
                .estado(1)
                .build();

        tiendaRepository.save(tiendaBD);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Tienda registrada exitosamente");
        return ResponseEntity.ok(response);
    }
}
