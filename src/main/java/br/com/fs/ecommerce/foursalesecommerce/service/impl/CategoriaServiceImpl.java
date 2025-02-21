package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.exception.UsuarioNaoEncontradoPorEmailException;
import br.com.fs.ecommerce.foursalesecommerce.repository.CategoriaRepository;
import br.com.fs.ecommerce.foursalesecommerce.repository.UsuarioRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.CategoriaService;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(String id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria salvar(CategoriaDto categoriaDto) {
        return categoriaRepository.save(Categoria.of(categoriaDto));
    }

    @Override
    public Categoria atualizar(CategoriaDto categoriaDto, String id) {
       if (!categoriaRepository.existsById(id)) {
           throw new RegistroNaoEncontradoException(Categoria.class.getSimpleName(), id);
       }
       return categoriaRepository.save(Categoria.of(categoriaDto));
    }

    @Override
    public void excluir(String id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException(Categoria.class.getSimpleName(), id);
        }
        categoriaRepository.deleteById(id);
    }
}