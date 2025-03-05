package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.ProdutoRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Page<Produto> listar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public Optional<Produto> buscarPorId(String id) {
        return produtoRepository.findById(id);
    }

    @Override
    public Produto salvar(ProdutoDto produtoDto) {
        return produtoRepository.save(Produto.of(produtoDto));
    }

    @Override
    public Produto atualizar(String id, ProdutoUpdateDto produtoDto) {
       if (!produtoRepository.existsById(id)) {
           throw new RegistroNaoEncontradoException(Produto.class.getSimpleName(), id);
       }

        return produtoRepository.save(Produto.of(id, produtoDto));
    }

    @Override
    public void excluir(String id) {
        if (!produtoRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException(Produto.class.getSimpleName(), id);
        }
        produtoRepository.deleteById(id);
    }
}