package com.app.Backend.service;

import com.app.Backend.controller.dto.UsuarioDTO;
import com.app.Backend.persistence.entities.Usuario.*;
import com.app.Backend.persistence.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Optional<Usuario> getUsuarioById(Long idUsuario) {
        return usuarioRepository.findUsuarioById(idUsuario);
    }

    @Transactional
    public UsuarioResponse actualizarUsuario(UsuarioRequest userRequest) {

        Usuario usuario = Usuario.builder()
                .idUsuario(userRequest.getIdUsuario())
                .dni(userRequest.getDni())
                .nombre(userRequest.getNombre())
                .apellido(userRequest.getApellido())
                .telefono(userRequest.getTelefono())
                .email(userRequest.getEmail())
                .usuario(userRequest.getUsuario())
                .build();

        usuarioRepository.updateUsuario(usuario.getIdUsuario(),
                usuario.getDni(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getEmail(),
                usuario.getUsuario());

        return new UsuarioResponse("¡El usuario se actualizó satisfactoriamente!");
    }

    public UsuarioDTO getUsuario(Long idUsuario) {
        Usuario usario = usuarioRepository.findById(idUsuario).orElse(null);

        if (usario != null)
        {
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .idUsuario(usario.getIdUsuario())
                    .dni(usario.getDni())
                    .nombre(usario.getNombre())
                    .apellido(usario.getApellido())
                    .telefono(usario.getTelefono())
                    .email(usario.getEmail())
                    .usuario(usario.getUsuario())
                    .build();
            return usuarioDTO;
        }
        return null;
    }
}
