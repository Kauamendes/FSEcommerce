package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.EmailJaCadastradoException;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.exception.UsuarioNaoEncontradoPorEmailException;
import br.com.fs.ecommerce.foursalesecommerce.repository.UsuarioRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<Usuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
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
            throw new EmailJaCadastradoException();
        }
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        return usuarioRepository.save(Usuario.of(usuarioDto));
    }

    @Override
    public Usuario atualizar(Long id, UsuarioUpdateDto usuarioDto) {
       if (!usuarioRepository.existsById(id)) {
           throw new RegistroNaoEncontradoException(Usuario.class.getSimpleName(), id);
       }

        Usuario usuarioComMesmoEmail = usuarioRepository.findByEmail(usuarioDto.getEmail()).orElse(null);
        if (nonNull(usuarioComMesmoEmail) && !Objects.equals(id, usuarioComMesmoEmail.getId())) {
            throw new EmailJaCadastradoException();
        }

        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        return usuarioRepository.save(Usuario.of(id, usuarioDto));
    }

    @Override
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException(Usuario.class.getSimpleName(), id);
        }
        usuarioRepository.updateAtivoAndExcluidoEmById(id, false);
    }
}