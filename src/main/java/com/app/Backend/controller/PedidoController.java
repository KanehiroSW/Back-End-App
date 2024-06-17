package com.app.Backend.controller;

import com.app.Backend.persistence.entities.DetallePedido;
import com.app.Backend.persistence.entities.Pedido;
import com.app.Backend.persistence.entities.Tienda;
import com.app.Backend.persistence.entities.Usuario;
import com.app.Backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/save")
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        if (pedido.getDetallePedidos() == null || pedido.getDetallePedidos().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Pedido savedPedido = pedidoService.savePedido(pedido);
        return ResponseEntity.ok(savedPedido);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoService.getPedidoById(id).orElse(null);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> getPedidosByUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);
        List<Pedido> pedidos = pedidoService.getPedidosByUsuario(usuario);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/tienda/{tiendaId}")
    public ResponseEntity<List<Pedido>> getPedidosByTienda(@PathVariable Long tiendaId) {
        Tienda tienda = new Tienda();
        tienda.setIdTienda(tiendaId);
        List<Pedido> pedidos = pedidoService.getPedidosByTienda(tienda);
        return ResponseEntity.ok(pedidos);
    }
}