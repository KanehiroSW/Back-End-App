package com.app.Backend.controller;

import com.app.Backend.persistence.entities.DetallePedido;
import com.app.Backend.persistence.entities.Pedido;
import com.app.Backend.service.DetallePedidoService;
import com.app.Backend.service.PedidoService;
import com.app.Backend.service.ProductoService;
import com.app.Backend.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private TiendaService tiendaService;
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

    @PostMapping("/save")
    public Pedido savePedido(@RequestBody Pedido pedido) {
        Date fechaPedido = new Date();
        pedido.setFechaPedido(fechaPedido);
        pedido.setNumeroSerie(pedidoService.generarNumeroSerie());
//        pedido.setTienda();


        return pedidoService.savePedido(pedido);
    }
}
