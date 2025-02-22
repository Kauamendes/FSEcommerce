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
public class UsuarioDto implements Serializable {

    private String id;

    @NotBlank(message = "O nome do usuário não pode ser vazio")
    private String nome;

    @NotBlank(message = "O email do usuário não pode ser vazio")
    private String email;

    private String senha;
    private UserRole role;

    public static UsuarioDto of(Usuario usuario) {
        return UsuarioDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}