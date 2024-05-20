package com.app.Backend.controller;

import com.app.Backend.persistence.entities.DetallePedido;
import com.app.Backend.persistence.entities.Pedido;
import com.app.Backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private DetallePedidoService detallePedidoService;

    List<DetallePedido> detallePedidos = new ArrayList<DetallePedido>();
    Pedido pedido = new Pedido();

    @GetMapping("/all")
    public List<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("{idPedido}")
    public Pedido getPedidoById(@PathVariable Long idPedido) {
        return pedidoService.getPedidoById(idPedido).get();
    }

//    @PostMapping("/car")
//    public ResponseEntity<?> addToCar(@RequestParam Integer id, @RequestParam Integer cantidad) {
//        DetalleOrden detalleOrden = new DetalleOrden();
//        Producto producto = productoService.findProductoById(id);
//
//        if (producto == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        detalleOrden.setCantidad(cantidad);
//        detalleOrden.setPrecio(producto.getPrecio());
//        detalleOrden.setNombre(producto.getNombre());
//        detalleOrden.setTotal(producto.getPrecio() * cantidad);
//        detalleOrden.setProducto(producto);
//
//        // Validar que el producto no se añada dos veces
//        boolean ingresado = detalleOrdens.stream().anyMatch(p -> p.getProducto().getIdProducto().equals(producto.getIdProducto()));
//
//        if (!ingresado) {
//            detalleOrdens.add(detalleOrden);
//        }
//
//        double sumaTotal = detalleOrdens.stream().mapToDouble(DetalleOrden::getTotal).sum();
//        orden.setTotal(sumaTotal);
//
//        return ResponseEntity.ok(detalleOrdens);
//    }
//
//    @DeleteMapping("/car/{id}")
//    public ResponseEntity<?> deleteProductoCar(@PathVariable Integer id) {
//        List<DetalleOrden> ordenesNueva = detalleOrdens.stream()
//                .filter(detalleOrden -> !detalleOrden.getProducto().getIdProducto().equals(id))
//                .collect(Collectors.toList());
//
//        detalleOrdens = ordenesNueva;
//
//        double sumaTotal = detalleOrdens.stream().mapToDouble(DetalleOrden::getTotal).sum();
//        orden.setTotal(sumaTotal);
//
//        return ResponseEntity.ok(detalleOrdens);
//    }
//
//    @GetMapping("/car")
//    public ResponseEntity<?> getCar() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("car", detalleOrdens);
//        response.put("orden", orden);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/order")
//    public ResponseEntity<?> getOrder(HttpSession session) {
//        Usuario usuario = usuarioService.findUsuarioById(Integer.parseInt(session.getAttribute("idusuario").toString())).orElse(null);
//
//        if (usuario == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("car", detalleOrdens);
//        response.put("orden", orden);
//        response.put("usuario", usuario);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/order")
//    public ResponseEntity<?> saveOrder(HttpSession session) {
//        Usuario usuario = usuarioService.findUsuarioById(Integer.parseInt(session.getAttribute("idusuario").toString())).orElse(null);
//
//        if (usuario == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        orden.setFechaCreacion(new Date());
//        orden.setNumero(ordenService.generarNumeroOrden());
//        orden.setUsuario(usuario);
//        ordenService.saveOrden(orden);
//
//        for (DetalleOrden dt : detalleOrdens) {
//            dt.setOrden(orden);
//            detalleOrdenService.saveDetalleOrden(dt);
//            productoRepository.actualizarStock(dt.getProducto().getCantidad() - dt.getCantidad(), dt.getProducto().getIdProducto());
//        }
//
//        orden = new Orden();
//        detalleOrdens.clear();
//
//        return ResponseEntity.ok("¡Orden registrada!");
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<Producto>> searchProduct(@RequestParam String nombre) {
//        List<Producto> productos = productoService.findAllProductos().stream()
//                .filter(p -> p.getNombre().contains(nombre))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(productos);
//    }
}
