package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.CategoriaRepository;
import br.com.fs.ecommerce.foursalesecommerce.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProdutoServiceTest extends AbstractH2Test {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void deve_listar_produtos_com_paginacao() {
        Page<Produto> resultado = produtoService.listar(PageRequest.of(0, 5));
        assertEquals(5, resultado.getSize());
        assertEquals(10, resultado.getTotalElements());
    }

    @Test
    void deve_buscar_produto_por_id() {
        Optional<Produto> encontrado = produtoService.buscarPorId(300L);

        assertTrue(encontrado.isPresent());
        assertEquals("Camiseta Básica", encontrado.get().getNome());
    }

    @Test
    void deve_salvar_novo_produto() {
        ProdutoDto dto = new ProdutoDto();
        dto.setNome("Jaqueta Couro");
        dto.setQuantidade(5);
        dto.setPreco(BigDecimal.valueOf(450.0));

        Produto salvo = produtoService.salvar(dto);

        assertNotNull(salvo.getId());
        assertEquals(DEFAULT_TENANT_ID, salvo.getTenantId());
    }

    @Test
    void deve_atualizar_produto_existente() {
        Produto produto = produtoRepository.findById(302L).orElseThrow();

        ProdutoUpdateDto updateDto = new ProdutoUpdateDto();
        updateDto.setNome("Monitor Gamer UltraWide");
        updateDto.setPreco(BigDecimal.valueOf(1200));

        Produto atualizado = produtoService.atualizar(produto.getId(), updateDto);

        assertEquals("Monitor Gamer UltraWide", atualizado.getNome());
        assertEquals(0, new BigDecimal("1200").compareTo(atualizado.getPreco()));
    }

    @Test
    void deve_realizar_exclusao_logica_desativando_o_produto() {
        Long idParaExcluir = 303L;

        produtoService.excluir(idParaExcluir);

        Produto deletadoLogicamente = produtoRepository.findById(idParaExcluir).orElseThrow();
        assertFalse(deletadoLogicamente.getAtivo());
    }

    @Test
    void deve_lancar_excecao_ao_atualizar_produto_inexistente() {
        ProdutoUpdateDto updateDto = new ProdutoUpdateDto();
        assertThrows(RegistroNaoEncontradoException.class, () -> produtoService.atualizar(999L, updateDto));
    }

    @Test
    void deve_lancar_excecao_ao_excluir_produto_inexistente() {
        assertThrows(RegistroNaoEncontradoException.class, () -> produtoService.excluir(999L));
    }
}
