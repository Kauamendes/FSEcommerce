package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();
    Optional<Usuario> buscarPorId(String id);
    Usuario buscarPorEmail(String email);
    Usuario salvar(UsuarioDto usuarioDto);
    Usuario atualizar(UsuarioDto usuarioDto, String id);
    Boolean excluir(String id);
}
