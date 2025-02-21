package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> listar();
    Optional<Categoria> buscarPorId(String id);
    Categoria salvar(CategoriaDto categoriaDto);
    Categoria atualizar(CategoriaDto categoriaDto, String id);
    void excluir(String id);
}