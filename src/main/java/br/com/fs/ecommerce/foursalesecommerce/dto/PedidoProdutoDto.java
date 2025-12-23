package br.com.fs.ecommerce.foursalesecommerce.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoProdutoDto {

    private Long id;
    private PedidoDto pedido;
    private ProdutoDto produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
}