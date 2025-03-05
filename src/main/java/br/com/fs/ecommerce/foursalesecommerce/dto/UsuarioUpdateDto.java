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
public class UsuarioUpdateDto implements Serializable {

    @NotBlank(message = "O nome do usuário não pode ser vazio")
    private String nome;

    @NotBlank(message = "O email do usuário não pode ser vazio")
    private String email;

    private String senha;
    private UserRole role;

    public static UsuarioUpdateDto of(Usuario usuario) {
        return UsuarioUpdateDto.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}