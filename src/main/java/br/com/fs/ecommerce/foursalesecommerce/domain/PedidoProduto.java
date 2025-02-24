package br.com.fs.ecommerce.foursalesecommerce.domain;

import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoProdutoDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity
@Table(name = "pedido_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoProduto {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @PrePersist
    public void prePersist() {
        if (isNull(id)) {
            id = UUID.randomUUID().toString();
        }
    }

    public static PedidoProduto of(PedidoProdutoDto pedidoProdutoDto) {
        if (isNull(pedidoProdutoDto)) return null;
        return PedidoProduto.builder()
                .id(pedidoProdutoDto.getId())
                .produto(Produto.of(pedidoProdutoDto.getProduto()))
                .pedido(Pedido.of(pedidoProdutoDto.getPedido()))
                .quantidade(pedidoProdutoDto.getQuantidade())
                .precoUnitario(pedidoProdutoDto.getPrecoUnitario())
                .build();
    }
}