package com.app.Backend.config;

import com.app.Backend.persistence.entities.Tienda.Tienda;
import com.app.Backend.service.TiendaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class TiendaInterceptor implements HandlerInterceptor {

    @Autowired
    private TiendaService tiendaService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("sesion_id_tienda") != null) {
            Long tiendaId = Long.parseLong(session.getAttribute("sesion_id_tienda").toString());
            Optional<Tienda> optionalTienda = tiendaService.getTiendaById(tiendaId);
            if (optionalTienda.isPresent()) {
                request.setAttribute("tienda", optionalTienda.get());
            }
        }
        return true;
    }
}

