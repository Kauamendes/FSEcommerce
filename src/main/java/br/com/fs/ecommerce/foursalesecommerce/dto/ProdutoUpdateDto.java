package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoUpdateDto {

    @NotBlank(message = "O nome do produto não pode ser vazio")
    private String nome;
    private String descricao;

    @NotNull(message = "O preço do produto não pode ser nulo")
    private BigDecimal preco;

    @NotNull(message = "A categoria do produto não pode ser nulo")
    private CategoriaDto categoria;

    private Integer quantidade;
    private Integer quantidadeReservada;

    public static ProdutoUpdateDto of(Produto produto) {
        return ProdutoUpdateDto.builder()
                .nome(produto.getNome())
                .categoria(CategoriaDto.of(produto.getCategoria()))
                .preco(produto.getPreco())
                .descricao(produto.getDescricao())
                .quantidade(produto.getQuantidade())
                .quantidadeReservada(produto.getQuantidadeReservada())
                .build();
    }
}