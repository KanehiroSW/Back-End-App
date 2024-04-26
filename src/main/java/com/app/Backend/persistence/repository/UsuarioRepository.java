package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Transactional
    @Modifying
    @Query(" UPDATE Usuario U SET U.estado = 0 WHERE U.idUsuario = :idUsuario ")
    void disableUsuario(@Param("idUsuario") Long idUsuario);
}
