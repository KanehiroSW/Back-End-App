package com.app.Backend.persistence.entities;

import com.app.Backend.persistence.entities.Tienda.Tienda;
import com.app.Backend.persistence.entities.Usuario.Usuario;
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

    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL
    )
    private List<DetallePedido> detallePedidos;

    @Column(name = "total_pedido")
    private double totalPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pedido")
    private EstadoPedido estadoPedido;
}

