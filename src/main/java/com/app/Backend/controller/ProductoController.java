package com.app.Backend.controller;

import com.app.Backend.persistence.entities.Producto;
import com.app.Backend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/list")
    public List<Producto> listaProductos() {
        return productoService.getAllProductos();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto registrarProducto(@RequestBody Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        return productoService.saveProducto(producto, file);
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
