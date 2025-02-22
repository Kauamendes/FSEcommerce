package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.CategoriaRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public Page<Categoria> listar(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
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
    public Categoria atualizar(String id, CategoriaDto categoriaDto) {
       if (!categoriaRepository.existsById(id)) {
           throw new RegistroNaoEncontradoException(Categoria.class.getSimpleName(), id);
       }
        categoriaDto.setId(id);
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