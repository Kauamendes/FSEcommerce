package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {

    Page<Usuario> listar(Pageable pageable);
    Optional<Usuario> buscarPorId(Long id);
    Usuario buscarPorEmail(String email);
    Usuario salvar(UsuarioDto usuarioDto);
    Usuario atualizar(Long id, UsuarioUpdateDto usuarioDto);
    void excluir(Long id);
}