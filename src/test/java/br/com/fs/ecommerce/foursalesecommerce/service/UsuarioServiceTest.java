package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.UserRole;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.EmailJaCadastradoException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UsuarioServiceTest extends AbstractH2Test {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void deve_salvar_usuario_com_senha_criptografada() {
        UsuarioDto dto = new UsuarioDto(null, "Dev", "dev@loja.com", "senha123", UserRole.ROLE_USUARIO);
        Usuario salvo = usuarioService.salvar(dto);

        assertTrue(passwordEncoder.matches("senha123", salvo.getSenha()));
        assertEquals("dev@loja.com", salvo.getEmail());
    }

    @Test
    void deve_lancar_excecao_para_email_duplicado() {
        UsuarioDto dto = new UsuarioDto(null, "User1", "dup@loja.com", "123", UserRole.ROLE_USUARIO);
        usuarioService.salvar(dto);

        assertThrows(EmailJaCadastradoException.class, () -> usuarioService.salvar(dto));
    }
}
