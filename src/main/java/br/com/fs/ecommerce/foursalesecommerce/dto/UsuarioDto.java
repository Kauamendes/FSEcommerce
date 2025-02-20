package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.UserRole;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto implements Serializable {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private UserRole role;

    public static UsuarioDto of(Usuario usuario) {
        return UsuarioDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .build();
    }
}
