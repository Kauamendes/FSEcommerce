package br.com.fs.ecommerce.foursalesecommerce.domain;

import br.com.fs.ecommerce.foursalesecommerce.annotations.Tsid;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaUpdateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static java.util.Objects.isNull;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria extends TenantEntity {

    @Id
    @Tsid
    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo;

    public static Categoria of(CategoriaDto categoriaDto) {
        if (isNull(categoriaDto)) return null;
        return Categoria.builder()
                .id(categoriaDto.getId())
                .nome(categoriaDto.getNome())
                .descricao(categoriaDto.getDescricao())
                .build();
    }

    public static Categoria of(Long id, CategoriaUpdateDto categoriaDto) {
        if (isNull(categoriaDto)) return null;
        return Categoria.builder()
                .id(id)
                .nome(categoriaDto.getNome())
                .descricao(categoriaDto.getDescricao())
                .build();
    }
}
