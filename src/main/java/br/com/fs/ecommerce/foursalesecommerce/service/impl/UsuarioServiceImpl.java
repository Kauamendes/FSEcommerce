package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.exception.UsuarioNaoEncontradoPorEmailException;
import br.com.fs.ecommerce.foursalesecommerce.repository.UsuarioRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoPorEmailException(email));
    }

    @Override
    public Usuario salvar(UsuarioDto usuarioDto) {
        if (usuarioRepository.findByEmail(usuarioDto.getEmail()).isPresent()) {
            throw new AuthenticationServiceException("Já existe um usuario cadastrado com este email");
        }
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        return usuarioRepository.save(Usuario.of(usuarioDto));
    }

    @Override
    public Usuario atualizar(String id, UsuarioDto usuarioDto) {
       if (!usuarioRepository.existsById(id)) {
           throw new RegistroNaoEncontradoException(Usuario.class.getSimpleName(), id);
       }
        usuarioDto.setId(id);
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
       return usuarioRepository.save(Usuario.of(usuarioDto));
    }

    @Override
    public void excluir(String id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException(Usuario.class.getSimpleName(), id);
        }
        usuarioRepository.deleteById(id);
    }
}