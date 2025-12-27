package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CategoriaServiceTest extends AbstractH2Test {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void deve_salvar_categoria_com_sucesso() {
        CategoriaDto dto = new CategoriaDto(null, "Eletrônicos", "");
        Categoria salvo = categoriaService.salvar(dto);

        assertNotNull(salvo.getId());
        assertEquals("Eletrônicos", salvo.getNome());
        assertEquals(DEFAULT_TENANT_ID, salvo.getTenantId());
    }

    @Test
    void deve_lancar_excecao_ao_excluir_categoria_inexistente() {
        assertThrows(RegistroNaoEncontradoException.class, () -> categoriaService.excluir(99L));
    }

    @Test
    void deve_atualizar_nome_da_categoria() {
        Categoria categoria = categoriaRepository.save(new Categoria(null, "Antigo", "true", true));
        CategoriaUpdateDto updateDto = new CategoriaUpdateDto("Novo Nome", "Nova descrição");

        Categoria atualizada = categoriaService.atualizar(categoria.getId(), updateDto);

        assertEquals("Novo Nome", atualizada.getNome());
    }
}
