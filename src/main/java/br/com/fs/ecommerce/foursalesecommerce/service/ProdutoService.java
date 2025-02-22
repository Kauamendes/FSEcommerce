package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProdutoService {

    Page<Produto> listar(Pageable pageable);
    Optional<Produto> buscarPorId(String id);
    Produto salvar(ProdutoDto produtoDto);
    Produto atualizar(String id, ProdutoDto produtoDto);
    void excluir(String id);
}