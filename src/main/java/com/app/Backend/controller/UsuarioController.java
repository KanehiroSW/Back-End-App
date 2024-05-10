package com.app.Backend.controller;

import com.app.Backend.controller.dto.UsuarioDTO;
import com.app.Backend.persistence.entities.Usuario.UsuarioRequest;
import com.app.Backend.persistence.entities.Usuario.UsuarioResponse;
import com.app.Backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/usuario")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping(value = "{idUsuario}")
    public ResponseEntity<UsuarioDTO> getUser(@PathVariable Long idUsuario)
    {
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(idUsuario);
        if (usuarioDTO == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping()
    public ResponseEntity<UsuarioResponse> updateUsuario(@RequestBody UsuarioRequest usuarioRequest)
    {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuarioRequest));
    }
}