package br.com.fs.ecommerce.foursalesecommerce.domain;

import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaUpdateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria extends BaseEntity {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private LocalDateTime excluidoEm;

    @PrePersist
    public void prePersist() {
        if (isNull(id)) {
            id = UUID.randomUUID().toString();
        }
    }

    public static Categoria of(CategoriaDto categoriaDto) {
        if (isNull(categoriaDto)) return null;
        return Categoria.builder()
                .id(categoriaDto.getId())
                .nome(categoriaDto.getNome())
                .descricao(categoriaDto.getDescricao())
                .build();
    }

    public static Categoria of(String id, CategoriaUpdateDto categoriaDto) {
        if (isNull(categoriaDto)) return null;
        return Categoria.builder()
                .id(id)
                .nome(categoriaDto.getNome())
                .descricao(categoriaDto.getDescricao())
                .build();
    }
}
