package com.app.Backend.controller;

import com.app.Backend.persistence.entities.Producto;
import com.app.Backend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/list")
    public List<Producto> listaProductos(@RequestParam Long idTienda) {
        return productoService.getAllProductos(idTienda);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Producto> registrarProducto(
            @RequestParam("tiendaId") Long tiendaId,
            @RequestParam("img") MultipartFile file,
            @RequestParam("nombreProducto") String nombreProducto,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio) throws IOException {

        Producto producto = new Producto();
        producto.setNombreProducto(nombreProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);

        Producto savedProducto = productoService.saveProducto(producto, file, tiendaId);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    @PostMapping("/update/{idProducto}")
    public void actualizarProducto(@PathVariable Long idProducto, @RequestBody Producto producto, @RequestParam("img") MultipartFile file) throws IOException{
        productoService.updateProducto(idProducto, producto, file);
    }

    @GetMapping("/disable/{idProducto}")
    public void desactivarProducto(@PathVariable Long idProducto){
        productoService.deleteProductoById(idProducto);
    }
}
