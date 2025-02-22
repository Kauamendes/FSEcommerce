package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDto {

    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaDto categoria;
    private Integer quantidade;
    private Integer quantidadeReservada;
    private Integer version;

    public static ProdutoDto of(Produto produto) {
        return ProdutoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .categoria(CategoriaDto.of(produto.getCategoria()))
                .preco(produto.getPreco())
                .descricao(produto.getDescricao())
                .quantidade(produto.getQuantidade())
                .quantidadeReservada(produto.getQuantidadeReservada())
                .version(produto.getVersion())
                .build();
    }
}