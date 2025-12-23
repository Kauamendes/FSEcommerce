package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProdutoService {

    Page<Produto> listar(Pageable pageable);
    Optional<Produto> buscarPorId(Long id);
    Produto salvar(ProdutoDto produtoDto);
    Produto atualizar(Long id, ProdutoUpdateDto produtoDto);
    void excluir(Long id);
}