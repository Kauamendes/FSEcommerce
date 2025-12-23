package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.UserRole;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResumidoDto implements Serializable {

    private Long id;
    private String nome;
    private String email;

    public static UsuarioResumidoDto of(Usuario usuario) {
        return UsuarioResumidoDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}