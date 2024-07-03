package com.app.Backend.service;

import com.app.Backend.persistence.entities.Usuario;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> getUsuarioById(Long usuarioId);
    Usuario registerUsuario(Usuario usuario);
    Optional<Usuario> validateUser(String dni,String password);
}
