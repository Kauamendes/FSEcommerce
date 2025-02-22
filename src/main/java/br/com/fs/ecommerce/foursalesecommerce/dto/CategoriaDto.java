package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDto implements Serializable {

    private String id;
    private String nome;
    private String descricao;
    private Integer version;

    public static CategoriaDto of(Categoria categoria) {
        return CategoriaDto.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .version(categoria.getVersion())
                .build();
    }
}
