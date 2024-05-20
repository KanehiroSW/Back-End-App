package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /*ENCONTRAR UN USUARIO POR EMAIL*/
    @Query(" SELECT U FROM Usuario U WHERE U.dni = :dni ")
    Optional<Usuario> findUsuarioByDni(@Param("dni") String dni);

    /*ACTUALIZAR USUARIO*/
    @Modifying()
    @Query(" UPDATE Usuario U SET U.dni = :dni," +
            "U.nombre = :nombre," +
            "U.apellido = :apellido," +
            "U.telefono = :telefono," +
            "U.email = :email," +
            "U.usuario = :usuario WHERE U.idUsuario = :idUsuario" )
    void updateUsuario(@Param(value = "idUsuario") Long idUsuario,
                    @Param(value = "dni") String dni,
                    @Param(value = "nombre") String nombre,
                    @Param(value = "apellido") String apellido,
                    @Param(value = "telefono") String telefono,
                    @Param(value = "email") String email,
                    @Param(value = "usuario") String usuario);

    /*DESACTIVAR USUARIO*/
    @Transactional
    @Modifying
    @Query(" UPDATE Usuario U SET U.estado = 0 WHERE U.idUsuario = :idUsuario ")
    void disableUsuario(@Param("idUsuario") Long idUsuario);
}
