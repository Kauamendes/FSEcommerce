package br.com.fs.ecommerce.foursalesecommerce.dto;

import br.com.fs.ecommerce.foursalesecommerce.domain.UserRole;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String senha;

    @JsonIgnore
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
