package br.com.fs.ecommerce.foursalesecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

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
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    private Integer quantidade;

    @PrePersist
    public void prePersist() {
        if (isNull(id)) {
            id = UUID.randomUUID().toString();
        }
    }
}
