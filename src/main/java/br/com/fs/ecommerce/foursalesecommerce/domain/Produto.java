package br.com.fs.ecommerce.foursalesecommerce.domain;

import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto extends BaseEntity {

    @Id
    private String id;
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

    @PrePersist
    public void prePersist() {
        if (isNull(id)) {
            id = UUID.randomUUID().toString();
        }
    }

    public static Produto of(ProdutoDto produtoDto) {
        if (isNull(produtoDto)) return null;
        Produto produto = Produto.builder()
                .id(produtoDto.getId())
                .nome(produtoDto.getNome())
                .descricao(produtoDto.getDescricao())
                .preco(produtoDto.getPreco())
                .categoria(Categoria.of(produtoDto.getCategoria()))
                .quantidade(produtoDto.getQuantidade())
                .quantidadeReservada(produtoDto.getQuantidadeReservada())
                .build();
        produto.setVersion(produtoDto.getVersion());
        return produto;
    }
}