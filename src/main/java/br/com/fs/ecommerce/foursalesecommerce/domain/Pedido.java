package br.com.fs.ecommerce.foursalesecommerce.domain;

import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoUpdateDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntity{

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PedidoProduto> pedidoProdutos = new ArrayList<>();

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @PrePersist
    public void prePersist() {
        if (isNull(id)) {
            id = UUID.randomUUID().toString();
        }
    }

    public static Pedido of(PedidoDto pedidoDto) {
        if (isNull(pedidoDto)) return null;
        Pedido pedido = Pedido.builder()
                .id(pedidoDto.getId())
                .usuario(Usuario.of(pedidoDto.getUsuario()))
                .status(pedidoDto.getStatus())
                .subtotal(pedidoDto.getSubtotal())
                .pedidoProdutos(pedidoDto.getPedidoProdutos().stream().map(PedidoProduto::of).toList())
                .build();
        pedido.getPedidoProdutos().forEach(p -> p.setPedido(pedido));
        return pedido;
    }

    public static Pedido of(String id, PedidoUpdateDto pedidoDto) {
        if (isNull(pedidoDto)) return null;
        Pedido pedido = Pedido.builder()
                .id(id)
                .usuario(Usuario.of(pedidoDto.getUsuario()))
                .status(pedidoDto.getStatus())
                .subtotal(pedidoDto.getSubtotal())
                .pedidoProdutos(pedidoDto.getPedidoProdutos().stream().map(PedidoProduto::of).toList())
                .build();
        pedido.getPedidoProdutos().forEach(p -> p.setPedido(pedido));
        return pedido;
    }
}