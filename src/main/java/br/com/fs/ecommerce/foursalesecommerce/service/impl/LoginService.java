package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.config.security.TokenService;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.AuthDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.LoginDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.EmailOuSenhaIncorretoException;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthDto logar(LoginDto loginDto) throws AuthException {
        Usuario usuario = usuarioService.buscarPorEmail(loginDto.getEmail());

        if (passwordEncoder.matches(loginDto.getSenha(), usuario.getSenha())) {
            return AuthDto.builder()
                    .token(this.tokenService.generateToken(usuario))
                    .build();
        }

        throw new EmailOuSenhaIncorretoException("Email ou senha incorretos");
    }
}