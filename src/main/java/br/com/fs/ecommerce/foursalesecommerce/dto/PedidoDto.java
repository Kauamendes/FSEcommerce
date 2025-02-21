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
    private Status status;
    private BigDecimal subtotal;

    @Builder.Default
    private List<PedidoProdutoDto> pedidoProdutos = new ArrayList<>();

    public static PedidoDto of(Pedido pedido) {
        return PedidoDto.builder().build();
    }
}