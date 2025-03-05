package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.domain.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoUpdateDto {

    @NotNull(message = "O usuario do pedido não pode ser nulo")
    private UsuarioDto usuario;

    @Builder.Default
    private Status status = Status.PENDENTE;

    private BigDecimal subtotal;

    @Builder.Default
    @NotEmpty(message = "Os itens do pedido não podem ser vazios")
    private List<PedidoProdutoDto> pedidoProdutos = new ArrayList<>();

    public static PedidoUpdateDto of(Pedido pedido) {
        return PedidoUpdateDto.builder()
                .usuario(UsuarioDto.of(pedido.getUsuario()))
                .status(pedido.getStatus())
                .subtotal(pedido.getSubtotal())
                .build();
    }

    public void calcularSubtotal() {
        this.subtotal = pedidoProdutos.stream()
                .map(PedidoProdutoDto::getPrecoUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}