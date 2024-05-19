package com.app.Backend.Auth.AuthTienda;

import com.app.Backend.Auth.AuthUsuario.AuthUsuarioResponse;
import com.app.Backend.Auth.AuthUsuario.LoginUsuarioRequest;
import com.app.Backend.Auth.AuthUsuario.RegisterUsuarioRequest;
import com.app.Backend.Jwt.JwtService;
import com.app.Backend.persistence.entities.Tienda.Tienda;
import com.app.Backend.persistence.entities.Usuario.Usuario;
import com.app.Backend.persistence.repository.TiendaRepository;
import com.app.Backend.persistence.repository.UsuarioRepository;
import com.app.Backend.service.UploadFileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthTiendaService {

    private final TiendaRepository tiendaRepository;
    private final UploadFileService uploadFileService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final HttpSession session;

    public AuthTiendaResponse login(LoginTiendaRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getDniPropietario(), request.getPassword()));
        UserDetails tienda = tiendaRepository.findTiendaByDni(request.getDniPropietario()).orElseThrow();
        /*ESTABLECER SESION TIENDA*/
        Optional<Tienda> tiendaOptional = tiendaRepository.findTiendaByDni(request.getDniPropietario());
        session.setAttribute("sesion_id_tienda", tiendaOptional.get().getIdTienda());

        String token = jwtService.getToken(tienda);
        return AuthTiendaResponse.builder()
                .token(token)
                .build();
    }

    public AuthTiendaResponse register(RegisterTiendaRequest request, MultipartFile file) throws IOException {

        Tienda tiendaExistente = tiendaRepository.findTiendaByDni(request.getDniPropietario()).orElse(null);
        if (tiendaExistente != null) {
            throw new IllegalArgumentException("No se puede crear una tienda existente.");
        }

        Tienda tienda = Tienda.builder()
                .nombreTienda(request.getNombreTienda())
                .nombrePropietario(request.getNombrePropietario())
                .dniPropietario(request.getDniPropietario())
                .direccion(request.getDireccion())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .password(passwordEncoder.encode(request.getPassword()))
                .estado(1)
                .build();

        if (tienda.getIdTienda() == null) {
            String nombreImagen = uploadFileService.saveImageTienda(file);
            tienda.setImagen(nombreImagen);
        } else {
            throw new IllegalArgumentException("No se puede crear una tienda existente.");
        }

        tiendaRepository.save(tienda);

        return AuthTiendaResponse.builder()
                .token(jwtService.getToken(tienda))
                .build();
    }
}
