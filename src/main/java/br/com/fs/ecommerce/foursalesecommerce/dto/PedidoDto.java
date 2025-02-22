package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.domain.Status;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDto {

    private String id;
    private UsuarioDto usuario;

    @Builder.Default
    private Status status = Status.PENDENTE;

    private BigDecimal subtotal;
    private Integer version;

    @Builder.Default
    private List<PedidoProdutoDto> pedidoProdutos = new ArrayList<>();

    public static PedidoDto of(Pedido pedido) {
        return PedidoDto.builder()
                .id(pedido.getId())
                .usuario(UsuarioDto.of(pedido.getUsuario()))
                .status(pedido.getStatus())
                .subtotal(pedido.getSubtotal())
                .version(pedido.getVersion())
                .build();
    }

    public void calcularSubtotal() {
        this.subtotal = pedidoProdutos.stream()
                .map(PedidoProdutoDto::getPrecoUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}