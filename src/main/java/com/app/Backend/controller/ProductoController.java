package com.app.Backend.controller;

import com.app.Backend.persistence.entities.Producto;
import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.persistence.entities.Usuario;
import com.app.Backend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMaping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listaProductos")
    public List<Producto> listarProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/create")
    public Producto create(@RequestBody Producto producto){
        Usuario usuario = usuarioService.findUsuarioById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        producto.setUsuario(usuario);
        /*IMG*/
        if (producto.getIdProducto() == null) {
            String nombreImagen= uploadFileService.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {

        }
        productoService.saveProducto(producto);
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Producto producto = productoService.findProductoById(id);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public void update(@RequestParam("id") Integer id, Producto producto, @RequestParam("img") MultipartFile file) throws IOException{
        Producto productoDB = productoService.findProductoById(id);
        producto.setIdProducto(productoDB.getIdProducto());
        productoDB.setNombre(producto.getNombre());
        productoDB.setDescripcion(producto.getDescripcion());
        if (file.isEmpty()) {
            producto.setImagen(productoDB.getImagen());
        } else {
            if (!productoDB.getImagen().equals("default.jpg")) {
                uploadFileService.deleteImage(productoDB.getImagen());
            }
            String nombreImagen = uploadFileService.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoDB.setPrecio(producto.getPrecio());
        productoDB.setCantidad(producto.getCantidad());
        producto.setUsuario(productoDB.getUsuario());
        productoService.updateProducto(producto);
        redirectAttributes.addFlashAttribute("msgConfirmacion","¡Producto actualizado correctamente!");
        return producto;
    }

    @GetMapping("/delete/{id}")
    public String deleteEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        Producto productoDB = productoService.findProductoById(id);
        /*Para eliminar cuando no sea la imagen por defecto*/
        if (!productoDB.getImagen().equals("default.jpg")) {
            uploadFileService.deleteImage(productoDB.getImagen());
        }
        productoService.deleteProducto(id);
        redirectAttributes.addFlashAttribute("msgConfirmacion","¡Producto eliminado correctamente!");
        return "redirect:/productos";
    }
}
