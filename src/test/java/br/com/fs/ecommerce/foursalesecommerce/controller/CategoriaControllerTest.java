package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.Categoria;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.service.CategoriaService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

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
@WithMockUser
class CategoriaControllerTest extends AbstractH2Test {

    @MockitoBean
    private CategoriaService categoriaService;

    @Test
    void deve_listar_categorias_paginadas() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Eletrônicos");

        when(categoriaService.listar(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(categoria)));

        mockMvc.perform(get("/categorias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome").value("Eletrônicos"));
    }

    @Test
    void deve_buscar_categoria_por_id() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Livros");

        when(categoriaService.buscarPorId(1L)).thenReturn(Optional.of(categoria));

        mockMvc.perform(get("/categorias/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Livros"));
    }

    @Test
    void deve_retornar_404_ao_buscar_id_inexistente() throws Exception {
        when(categoriaService.buscarPorId(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/categorias/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_salvar_nova_categoria() throws Exception {
        CategoriaDto dto = new CategoriaDto(null, "Móveis", "Descrição de móveis");
        Categoria categoriaSalva = new Categoria();
        categoriaSalva.setId(10L);
        categoriaSalva.setNome("Móveis");

        when(categoriaService.salvar(any(CategoriaDto.class))).thenReturn(categoriaSalva);

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nome").value("Móveis"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_atualizar_categoria_existente() throws Exception {
        CategoriaUpdateDto updateDto = new CategoriaUpdateDto("Novo Nome", "Descrição");
        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setId(1L);
        categoriaAtualizada.setNome("Novo Nome");

        when(categoriaService.atualizar(eq(1L), any(CategoriaUpdateDto.class)))
                .thenReturn(categoriaAtualizada);

        mockMvc.perform(put("/categorias/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo Nome"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_excluir_categoria() throws Exception {
        doNothing().when(categoriaService).excluir(1L);

        mockMvc.perform(delete("/categorias/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
