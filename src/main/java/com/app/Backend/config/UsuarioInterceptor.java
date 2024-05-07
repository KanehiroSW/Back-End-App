package com.app.Backend.config;

import com.app.Backend.persistence.entities.Usuario.Usuario;
import com.app.Backend.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class UsuarioInterceptor implements HandlerInterceptor {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("sesion_id_usuario") != null) {
            Long usuarioId = Long.parseLong(session.getAttribute("sesion_id_usuario").toString());
            Optional<Usuario> optionalUsuario = usuarioService.getUsuarioById(usuarioId);
            if (optionalUsuario.isPresent()) {
                request.setAttribute("usuario", optionalUsuario.get());
            }
        }
        return true;
    }
}

