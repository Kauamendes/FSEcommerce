package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.service.ProdutoService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProdutoControllerTest extends AbstractH2Test {

    @MockitoBean
    private ProdutoService produtoService;

    @Test
    @WithMockUser(authorities = "ROLE_USUARIO")
    void deve_listar_produtos_paginados() throws Exception {
        Produto produto = criarProdutoMock(1L, "Smartphone");

        when(produtoService.listar(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(produto)));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome").value("Smartphone"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_USUARIO")
    void deve_buscar_produto_por_id() throws Exception {
        Produto produto = criarProdutoMock(1L, "Teclado");

        when(produtoService.buscarPorId(any())).thenReturn(Optional.of(produto));

        mockMvc.perform(get("/produtos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Teclado"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_salvar_produto_quando_for_admin() throws Exception {
        CategoriaDto categoriaDto = new CategoriaDto(1L, "Eletronicos", "Digital");
        ProdutoDto dto = new ProdutoDto(null, "Mouse", "Gamer", new BigDecimal("150.00"), categoriaDto, 10, 1);

        Produto salvo = criarProdutoMock(10L, "Mouse");

        when(produtoService.salvar(any())).thenReturn(salvo);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    @WithMockUser(authorities = "ROLE_USUARIO")
    void deve_proibir_salvar_produto_quando_for_usuario_comum() throws Exception {
        CategoriaDto categoria = new CategoriaDto(1L, "Eletronicos", "Digital");
        ProdutoDto dto = new ProdutoDto(null, "Mouse", "Gamer", new BigDecimal("150.00"), categoria, 10, 1);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_atualizar_produto_quando_for_admin() throws Exception {
        CategoriaDto categoriaDto = new CategoriaDto(1L, "Eletronicos", "Digital");
        ProdutoUpdateDto updateDto = new ProdutoUpdateDto("Monitor", "4K", new BigDecimal("3000.00"), categoriaDto, 5, 1);

        Produto atualizado = criarProdutoMock(1L, "Monitor");

        when(produtoService.atualizar(eq(1L), any())).thenReturn(atualizado);

        mockMvc.perform(put("/produtos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Monitor"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_excluir_produto_quando_for_admin() throws Exception {
        doNothing().when(produtoService).excluir(1L);

        mockMvc.perform(delete("/produtos/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(authorities = "ROLE_USUARIO")
    void deve_proibir_excluir_produto_quando_for_usuario_comum() throws Exception {
        mockMvc.perform(delete("/produtos/{id}", 1L))
                .andExpect(status().isForbidden());
    }

    private Produto criarProdutoMock(Long id, String nome) {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Eletronicos");

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao("Descricao");
        produto.setPreco(new BigDecimal("2000.00"));
        produto.setQuantidade(10);
        produto.setAtivo(true);
        produto.setCategoria(categoria);
        return produto;
    }
}
