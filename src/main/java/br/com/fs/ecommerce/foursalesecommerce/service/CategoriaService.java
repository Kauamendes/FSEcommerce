package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> listar();
    Optional<Categoria> buscarPorId(String id);
    Categoria salvar(CategoriaDto categoriaDto);

    Categoria atualizar(String id, CategoriaDto categoriaDto);
    void excluir(String id);
}