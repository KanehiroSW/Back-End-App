package com.app.Backend.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_pedidos"
)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "direccion_entrega")
    private String direccionEntrega;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "id_repartidor")
    private Repartidor repartidor;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @Column(name = "total_pedido")
    private double totalPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pedido")
    private EstadoPedido estadoPedido;
}

