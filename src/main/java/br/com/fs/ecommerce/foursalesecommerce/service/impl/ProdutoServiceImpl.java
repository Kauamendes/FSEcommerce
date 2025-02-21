package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.ProdutoRepository;
import br.com.fs.ecommerce.foursalesecommerce.repository.UsuarioRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.ProdutoService;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listar() {
        return produtoRepository.findAll();
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
    public Produto atualizar(ProdutoDto produtoDto, String id) {
       if (!produtoRepository.existsById(id)) {
           throw new RegistroNaoEncontradoException(Produto.class.getSimpleName(), id);
       }
       return produtoRepository.save(Produto.of(produtoDto));
    }

    @Override
    public void excluir(String id) {
        if (!produtoRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException(Produto.class.getSimpleName(), id);
        }
        produtoRepository.deleteById(id);
    }
}