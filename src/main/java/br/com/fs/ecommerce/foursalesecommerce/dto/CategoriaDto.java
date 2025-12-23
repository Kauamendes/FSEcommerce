package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDto implements Serializable {

    private Long id;

    @NotBlank(message = "O nome da categoria não pode ser vazio")
    private String nome;
    private String descricao;

    public static CategoriaDto of(Categoria categoria) {
        return CategoriaDto.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }
}
