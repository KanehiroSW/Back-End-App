package com.app.Backend.service;

import com.app.Backend.exception.ProductoNotFoundException;
import com.app.Backend.persistence.entities.*;
import com.app.Backend.persistence.repository.DetallePedidoRepository;
import com.app.Backend.persistence.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        pedido.setNumeroSerie(generarNumeroSerie());
        pedido.setFechaPedido(new Date());
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);

        Pedido savedPedido = pedidoRepository.save(pedido);

        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            detalle.setPedido(savedPedido);
            detallePedidoRepository.save(detalle);
        }
        return savedPedido;
    }

    @Override
    public Optional<Pedido> getPedidoById(Long idPedido) {
        if (idPedido != null) {
            return pedidoRepository.findById(idPedido);
        } else {
            throw new ProductoNotFoundException("¡Pedido no encontrado!");
        }
    }

    @Override
    public List<Pedido> getPedidosByUsuario(Usuario usuario) {
        return pedidoRepository.findByUsuario(usuario);
    }

    @Override
    public List<Pedido> getPedidosByTienda(Tienda tienda) {
        return pedidoRepository.findByTienda(tienda);
    }

    @Override
    public String generarNumeroSerie() {
        int numero = 0;
        String numeroUnido = "";
        List<Pedido> pedidos = getAllPedidos();
        List<Integer> numeros = new ArrayList<Integer>();

        pedidos.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumeroSerie())));

        if (pedidos.isEmpty()) {
            numero = 1;
        }else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero < 10) {
            numeroUnido = "000000000"+String.valueOf(numero);
        } else if (numero < 100) {
            numeroUnido = "00000000"+String.valueOf(numero);
        } else if (numero < 1000) {
            numeroUnido = "0000000"+String.valueOf(numero);
        } else if (numero < 10000) {
            numeroUnido = "000000"+String.valueOf(numero);
        }
        return numeroUnido;
    }
}
