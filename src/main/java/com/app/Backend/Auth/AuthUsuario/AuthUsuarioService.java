package com.app.Backend.Auth.AuthUsuario;

import com.app.Backend.Jwt.JwtService;
import com.app.Backend.persistence.entities.Usuario.Usuario;
import com.app.Backend.persistence.repository.UsuarioRepository;
import com.app.Backend.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final HttpSession session;

    public AuthUsuarioResponse login(LoginUsuarioRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getDni(), request.getPassword()));
        UserDetails usuario = usuarioRepository.findUsuarioByDni(request.getDni()).orElseThrow();
        /*ESTABLECER SESION USUARIO*/
        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByDni(request.getDni());
        session.setAttribute("sesion_id_usuario", usuarioOptional.get().getIdUsuario());

        String token = jwtService.getToken(usuario);
        return AuthUsuarioResponse.builder()
                .token(token)
                .build();
    }

    public AuthUsuarioResponse register(RegisterUsuarioRequest request) {
        Usuario usuario = Usuario.builder()
                .dni(request.getDni())
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .usuario(request.getUsuario())
                .estado(1)
                .build();

        usuarioRepository.save(usuario);

        return AuthUsuarioResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}