package com.app.Backend.Auth;

import com.app.Backend.Jwt.JwtService;
import com.app.Backend.persistence.entities.Usuario.Usuario;
import com.app.Backend.persistence.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
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

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}