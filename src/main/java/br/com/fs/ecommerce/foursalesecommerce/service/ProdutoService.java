package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<Produto> listar();
    Optional<Produto> buscarPorId(String id);
    Produto salvar(ProdutoDto produtoDto);

    Produto atualizar(String id, ProdutoDto produtoDto);
    void excluir(String id);
}