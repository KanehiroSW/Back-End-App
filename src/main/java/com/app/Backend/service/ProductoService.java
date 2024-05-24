package com.app.Backend.service;

import com.app.Backend.persistence.entities.Producto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public interface ProductoService {
    List<Producto> getAllProductos(Long idTienda);
    Optional<Producto> getProductoById(Long idProducto);
    Optional<Producto> getProductoByNombre(String nombre);
    Producto saveProducto(Producto producto, MultipartFile file, Long tiendaId) throws IOException;
    void updateProducto(Long idProducto, Producto producto, MultipartFile file) throws IOException;
    void deleteProductoById(Long idProducto);
}
