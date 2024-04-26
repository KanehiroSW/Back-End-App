package com.app.Backend.persistence.repository;

import com.app.Backend.persistence.entities.Producto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    /*LISTAR TODOS LOS PRODUCTOS*/
    @Query(" SELECT P FROM Producto P WHERE P.estado = 1 ")
    List<Producto> findAllProductos();

    /*ENCONTRAR PRODUCTO POR NOMBRE*/
    @Query(" SELECT P FROM Producto P WHERE P.nombreProducto = :nombre ")
    Optional<Producto> findProductoByNombre(@Param("nombre") String nombre);

    /*DESACTIVAR PRODUCTO*/
    @Transactional
    @Modifying
    @Query(" UPDATE Producto P SET P.estado = 0 WHERE P.idProducto = :idProducto ")
    void disableProducto(@Param("idProducto") Long idProducto);

    /*ACTUALIZAR STOCK*/
    @Transactional
    @Modifying
    @Query(
            value = "UPDATE productos SET stock = ?1 WHERE id_producto = ?2",
            nativeQuery = true
    )
    void actualizarStock(int cantidad, Long idProducto);
}
