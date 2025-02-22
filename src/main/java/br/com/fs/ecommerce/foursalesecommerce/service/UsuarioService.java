package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {

    Page<Usuario> listar(Pageable pageable);
    Optional<Usuario> buscarPorId(String id);
    Usuario buscarPorEmail(String email);
    Usuario salvar(UsuarioDto usuarioDto);
    Usuario atualizar(String id, UsuarioDto usuarioDto);
    void excluir(String id);
}