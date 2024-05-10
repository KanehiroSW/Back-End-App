package com.app.Backend.config;

import com.app.Backend.persistence.entities.Tienda.Tienda;
import com.app.Backend.persistence.entities.Usuario.Usuario;
import com.app.Backend.persistence.repository.TiendaRepository;
import com.app.Backend.persistence.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UsuarioRepository usuarioRepository;
    private final TiendaRepository tiendaRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        return dni -> {
            Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByDni(dni);
            if (usuarioOptional.isPresent()) {
                return usuarioOptional.get();
            }
            Optional<Tienda> tiendaOptional = tiendaRepository.findTiendaByDni(dni);
            if (tiendaOptional.isPresent()) {
                return tiendaOptional.get();
            }
            throw new UsernameNotFoundException("¡Entidad no encontrada!");
        };
    }
}
