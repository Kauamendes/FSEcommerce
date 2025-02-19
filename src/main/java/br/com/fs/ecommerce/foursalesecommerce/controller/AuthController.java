package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.config.security.TokenService;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.AuthDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.LoginDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.EmailOuSenhaIncorretoException;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public AuthDto login(@RequestBody LoginDto loginDto){
        Usuario usuario = this.repository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Usuario"));

        if (passwordEncoder.matches(loginDto.getSenha(), usuario.getSenha())) {
            return AuthDto.builder()
                    .token(this.tokenService.generateToken(usuario))
                    .build();
        }

        throw new EmailOuSenhaIncorretoException("Email ou senha incorretos");
    }


    @PostMapping("/register")
    public UsuarioDto register(@RequestBody UsuarioDto usuarioDto){
        Optional<Usuario> usuario = this.repository.findByEmail(usuarioDto.getEmail());

        if (usuario.isPresent()) {

        }
        Usuario newUser = new User();
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setName(body.name());
        this.repository.save(newUser);
        return ResponseEntity.badRequest().build();
    }
}