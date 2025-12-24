package br.com.fs.ecommerce.foursalesecommerce.domain;

import br.com.fs.ecommerce.foursalesecommerce.annotations.Tsid;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoUpdateDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto extends TenantEntity {

    @Id
    @Tsid
    private Long id;

    private String nome;
    private String descricao;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    private Integer quantidade;

    @Column(name = "quantidade_reservada")
    private Integer quantidadeReservada;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @JsonIgnore
    private List<PedidoProduto> pedidoProdutos = new ArrayList<>();

    private Boolean ativo;

    public static Produto of(ProdutoDto produtoDto) {
        if (isNull(produtoDto)) return null;
        return Produto.builder()
                .id(produtoDto.getId())
                .nome(produtoDto.getNome())
                .descricao(produtoDto.getDescricao())
                .preco(produtoDto.getPreco())
                .categoria(Categoria.of(produtoDto.getCategoria()))
                .quantidade(produtoDto.getQuantidade())
                .quantidadeReservada(produtoDto.getQuantidadeReservada())
                .build();
    }

    public static Produto of(Long id, ProdutoUpdateDto produtoDto) {
        if (isNull(produtoDto)) return null;
        return Produto.builder()
                .id(id)
                .nome(produtoDto.getNome())
                .descricao(produtoDto.getDescricao())
                .preco(produtoDto.getPreco())
                .categoria(Categoria.of(produtoDto.getCategoria()))
                .quantidade(produtoDto.getQuantidade())
                .quantidadeReservada(produtoDto.getQuantidadeReservada())
                .build();
    }
}