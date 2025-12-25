package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.UserRole;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.AuthDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.LoginDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.EmailOuSenhaIncorretoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.UsuarioRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.impl.LoginService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LoginServiceTest extends AbstractH2Test {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void deve_autenticar_usuario_e_gerar_token() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@loja.com");
        usuario.setNome("nome");
        usuario.setSenha(passwordEncoder.encode("123456"));
        usuario.setRole(UserRole.ROLE_USUARIO);
        usuario.setAtivo(true);
        usuarioRepository.save(usuario);

        LoginDto loginDto = new LoginDto("teste@loja.com", "123456");
        AuthDto auth = loginService.logar(loginDto);

        assertNotNull(auth.getToken());
    }

    @Test
    void deve_lancar_excecao_para_senha_incorreta() {
        Usuario usuario = new Usuario();
        usuario.setEmail("erro@loja.com");
        usuario.setNome("nome");
        usuario.setSenha(passwordEncoder.encode("correta"));
        usuario.setRole(UserRole.ROLE_USUARIO);
        usuarioRepository.save(usuario);

        LoginDto loginDto = new LoginDto("erro@loja.com", "errada");

        assertThrows(EmailOuSenhaIncorretoException.class, () -> loginService.logar(loginDto));
    }
}
