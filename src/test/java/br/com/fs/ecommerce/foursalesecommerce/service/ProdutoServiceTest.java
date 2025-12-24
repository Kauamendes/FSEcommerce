package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
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

    @Test
    void deve_listar_produtos_com_paginacao() {
        Categoria categoria = new Categoria(1L, "test", "teste", true);

        produtoRepository.save(new Produto(null, "Produto A", "", BigDecimal.TEN, categoria, 2, 2, null, true));
        produtoRepository.save(new Produto(null, "Produto B", "", BigDecimal.valueOf(20), categoria, 2, 2, null, true));

        Page<Produto> resultado = produtoService.listar(PageRequest.of(0, 10));

        assertEquals(2, resultado.getTotalElements());
    }

    @Test
    void deve_buscar_produto_por_id() {
        Categoria categoria = new Categoria(1L, "test", "teste", true);
        Produto produto = produtoRepository.save(new Produto(null, "Mouse", "", BigDecimal.valueOf(50), categoria, 2, 2, null, true));

        Optional<Produto> encontrado = produtoService.buscarPorId(produto.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Mouse", encontrado.get().getNome());
    }

    @Test
    void deve_salvar_novo_produto() {
        ProdutoDto dto = new ProdutoDto();
        dto.setNome("Teclado");
        dto.setQuantidade(15);
        dto.setPreco(BigDecimal.valueOf(150.0));

        Produto salvo = produtoService.salvar(dto);

        assertNotNull(salvo.getId());
        assertEquals("Teclado", salvo.getNome());
        assertEquals(DEFAULT_TENANT_ID, salvo.getTenantId());
    }

    @Test
    void deve_atualizar_produto_existente() {
        Categoria categoria = new Categoria(1L, "test", "teste", true);
        Produto produto = produtoRepository.save(new Produto(null, "Monitor", "", BigDecimal.valueOf(800), categoria, 1, 1, null, true));
        ProdutoUpdateDto updateDto = new ProdutoUpdateDto();
        updateDto.setNome("Monitor Gamer");
        updateDto.setPreco(BigDecimal.valueOf(950));

        Produto atualizado = produtoService.atualizar(produto.getId(), updateDto);

        assertEquals("Monitor Gamer", atualizado.getNome());
        assertEquals(new BigDecimal(950), atualizado.getPreco());
    }

    @Test
    void deve_lancar_excecao_ao_atualizar_produto_inexistente() {
        ProdutoUpdateDto updateDto = new ProdutoUpdateDto();
        assertThrows(RegistroNaoEncontradoException.class, () -> produtoService.atualizar(999L, updateDto));
    }

    @Test
    void deve_realizar_exclusao_logica_desativando_o_produto() {
        Categoria categoria = new Categoria(1L, "test", "teste", true);
        Produto produto = produtoRepository.save(new Produto(null, "GPU", "", BigDecimal.valueOf(3000), categoria, 2, 2, null, true));

        produtoService.excluir(produto.getId());

        Produto deletadoLogicamente = produtoRepository.findById(produto.getId()).orElseThrow();
        assertFalse(deletadoLogicamente.getAtivo());
    }

    @Test
    void deve_lancar_excecao_ao_excluir_produto_inexistente() {
        assertThrows(RegistroNaoEncontradoException.class, () -> produtoService.excluir(999L));
    }
}
