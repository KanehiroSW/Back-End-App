package com.app.Backend.service;

import com.app.Backend.persistence.entities.Producto;

import java.util.*;

public interface ProductoService {
    List<Producto> getAllProductos();
    Optional<Producto> getProductoById(Long idProducto);
    Optional<Producto> getProductoByNombre(String nombre);
    Producto saveProducto(Producto producto);
    void updateProducto(Long idProducto, Producto producto);
    void deleteProductoById(Long idProducto);
}
