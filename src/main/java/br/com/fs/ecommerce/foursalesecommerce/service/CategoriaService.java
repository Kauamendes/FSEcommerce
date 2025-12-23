package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoriaService {

    Page<Categoria> listar(Pageable pageable);
    Optional<Categoria> buscarPorId(Long id);
    Categoria salvar(CategoriaDto categoriaDto);
    Categoria atualizar(Long id, CategoriaUpdateDto categoriaDto);
    void excluir(Long id);
}