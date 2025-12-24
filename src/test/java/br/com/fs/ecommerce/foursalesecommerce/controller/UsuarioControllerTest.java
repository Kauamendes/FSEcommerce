package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.UserRole;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
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
class UsuarioControllerTest extends AbstractH2Test {

    @MockitoBean
    private UsuarioService usuarioService;

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_listar_usuarios_quando_for_admin() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuario.setEmail("joao@test.com");

        when(usuarioService.listar(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(usuario)));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nome").value("João Silva"))
                .andExpect(jsonPath("$.content[0].email").value("joao@test.com"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_buscar_usuario_por_id() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Admin Teste");

        when(usuarioService.buscarPorId(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/usuarios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Admin Teste"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_salvar_usuario_quando_for_admin() throws Exception {
        UsuarioDto dto = new UsuarioDto(1L, "Novo Usuario", "novo@test.com", "senha123", UserRole.ROLE_USUARIO);
        Usuario salvo = new Usuario();
        salvo.setId(10L);
        salvo.setNome("Novo Usuario");

        when(usuarioService.salvar(any(UsuarioDto.class))).thenReturn(salvo);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_atualizar_usuario_quando_for_admin() throws Exception {
        UsuarioUpdateDto updateDto = new UsuarioUpdateDto("Nome Atualizado", "atualizado@test.com", "senha", UserRole.ROLE_ADMIN);
        Usuario atualizado = new Usuario();
        atualizado.setId(1L);
        atualizado.setNome("Nome Atualizado");

        when(usuarioService.atualizar(eq(1L), any(UsuarioUpdateDto.class))).thenReturn(atualizado);

        mockMvc.perform(put("/usuarios/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome Atualizado"));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_excluir_usuario_quando_for_admin() throws Exception {
        doNothing().when(usuarioService).excluir(1L);

        mockMvc.perform(delete("/usuarios/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void deve_proibir_acesso_a_usuarios_comuns() throws Exception {
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deve_retornar_404_ao_buscar_id_inexistente() throws Exception {
        when(usuarioService.buscarPorId(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/usuarios/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}