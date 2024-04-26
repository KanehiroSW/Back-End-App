package com.app.Backend.service;

import com.app.Backend.exception.ProductoNotFoundException;
import com.app.Backend.persistence.entities.Producto;
import com.app.Backend.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAllProductos();
    }

    @Override
    public Optional<Producto> getProductoById(Long idProducto) {
        if (idProducto != null) {
            return productoRepository.findById(idProducto);
        } else {
            throw new ProductoNotFoundException("¡Producto no encontrado!");
        }
    }

    @Override
    public Optional<Producto> getProductoByNombre(String nombre) {
        Optional<Producto> producto = productoRepository.findProductoByNombre(nombre);
        if (producto.isEmpty()) {
            throw new ProductoNotFoundException("No se encontró ningún producto con el nombre: " + nombre);
        }
        return producto;
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void updateProducto(Long idProducto, Producto producto) {
        Optional<Producto> productoOptional = getProductoById(idProducto);
        if (productoOptional.isEmpty()) {
            Producto productoBD = productoOptional.get();
            productoBD.setNombreProducto(producto.getNombreProducto());
            productoBD.setDescripcion(producto.getDescripcion());
            productoBD.setStock(producto.getStock());
            productoBD.setPrecio(producto.getPrecio());
            productoRepository.save(productoBD);
        } else {
            throw new ProductoNotFoundException("¡Producto no encontrado!");
        }
    }

    @Override
    public void deleteProductoById(Long idProducto) {
        if (idProducto != null) {
            productoRepository.disableProducto(idProducto);
        } else {
            throw new ProductoNotFoundException("¡Producto no encontrado!");
        }
    }
}
