package com.app.Backend.service;

import com.app.Backend.exception.ProductoNotFoundException;
import com.app.Backend.persistence.entities.Producto;
import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TiendaService tiendaService;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<Producto> getAllProductos(Long idTienda) {
        return productoRepository.findAllProductos(idTienda);
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
    public Producto saveProducto(Producto producto, MultipartFile file, Long tiendaId) throws IOException {
        if (producto.getIdProducto() == null) {
            Tienda tienda = tiendaService.getTiendaById(tiendaId).orElseThrow(() -> new IllegalArgumentException("Tienda no encontrada."));
            String nombreImagen = uploadFileService.saveImageProducto(file, tienda.getNombreTienda());
            producto.setImagen(nombreImagen);
            producto.setTienda(tienda);
        } else {
            throw new IllegalArgumentException("No se puede crear un producto existente.");
        }
        producto.setEstado(1);
        return productoRepository.save(producto);
    }

    @Override
    public void updateProducto(Long idProducto, Producto producto, MultipartFile file) throws IOException {

        Optional<Producto> productoOptional = getProductoById(idProducto);

        if (productoOptional.isEmpty()) {

            Producto productoBD = productoOptional.get();
            productoBD.setNombreProducto(producto.getNombreProducto());
            productoBD.setDescripcion(producto.getDescripcion());
            productoBD.setPrecio(producto.getPrecio());

            if (file.isEmpty()) {
                producto.setImagen(productoBD.getImagen());
            } else {
                if (!productoBD.getImagen().equals("default.jpg")) {
                    uploadFileService.deleteImageProducto(productoBD.getImagen());
                }
                productoRepository.save(productoBD);
            }
        } else {
            throw new ProductoNotFoundException("¡Producto no encontrado!");
        }
    }

    @Override
    public void deleteProductoById(Long idProducto) {

        if (idProducto != null) {
            Optional<Producto> productoOptional = getProductoById(idProducto);
            Producto productoBD = productoOptional.get();

            if (!productoBD.getImagen().equals("default.jpg")) {
                uploadFileService.deleteImageProducto(productoBD.getImagen());
            }
            productoRepository.disableProducto(idProducto);
        } else {
            throw new ProductoNotFoundException("¡Producto no encontrado!");
        }
    }
}
