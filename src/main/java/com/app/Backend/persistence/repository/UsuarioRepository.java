package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /*ENCONTRAR UN USUARIO POR EMAIL*/
    @Query(" SELECT U FROM Usuario U WHERE U.email = :email ")
    Optional<Usuario> findByEmail(@Param("email") String email);

    /*ACTUALIZAR USUARIO*/
    @Modifying()
    @Query(" UPDATE Usuario U SET U.dni = :dni," +
            "U.nombre = :nombre," +
            "U.apellido = :apellido," +
            "U.telefono = :telefono," +
            "U.usuario = :usuario WHERE U.idUsuario = :idUsuario" )
    void updateUsuario(@Param(value = "idUsuario") Long idUsuario,
                    @Param(value = "dni") String dni,
                    @Param(value = "nombre") String nombre,
                    @Param(value = "apellido") String apellido,
                    @Param(value = "telefono") String telefono,
                    @Param(value = "usuario") String usuario);

    /*DESACTIVAR USUARIO*/
    @Transactional
    @Modifying
    @Query(" UPDATE Usuario U SET U.estado = 0 WHERE U.idUsuario = :idUsuario ")
    void disableUsuario(@Param("idUsuario") Long idUsuario);
}
