package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@WithMockUser(username = "admin@gmail.com", authorities = {"ROLE_ADMIN"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    private MockMvc mockMvc;

    private ResultActions resultActions;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Nested
    class Dado_um_usuario {

        private UsuarioDto usuarioDto;

        @BeforeEach
        void setUp() {
            usuarioDto = UsuarioDto.builder()
                    .nome("Usuario Teste")
                    .email("usuario@ecommerce.com")
                    .senha("senha_usuario")
                    .build();
        }

        @Nested
        class Quando_salvar {

            @BeforeEach
            void setup() throws Exception {
                when(usuarioService.salvar(any(UsuarioDto.class))).thenReturn(Usuario.of(usuarioDto));
                resultActions = mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"usuario@ecommerce.com\", \"nome\":\"Usuario Teste\", \"senha\":\"senha_usuario\"}"));
            }

            @Test
            void Entao_deve_retornar_usuario_salvo_e_status_ok() throws Exception {
                verify(usuarioService, times(1)).salvar(any(UsuarioDto.class));
                resultActions.andExpect(status().isOk())
                        .andExpect(jsonPath("$.email").value("usuario@ecommerce.com"))
                        .andExpect(jsonPath("$.nome").value("Usuario Teste"));
            }
        }
    }

    @Nested
    class Dado_um_usuario_salvo {

        private Usuario usuario;

        @BeforeEach
        void setUp() {
            usuario = Usuario.builder()
                    .id(UUID.randomUUID().toString())
                    .nome("Usuario Teste")
                    .email("usuario@ecommerce.com")
                    .senha("senha_usuario")
                    .build();
        }

        @Nested
        class Quando_listar_usuarios {

            @BeforeEach
            void setUp() throws Exception {
                when(usuarioService.listar()).thenReturn(List.of(usuario));
                resultActions = mockMvc.perform(get("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            void Entao_deve_retornar_lista_de_usuarios_e_status_ok() throws Exception {
                verify(usuarioService, times(1)).listar();
                resultActions.andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].email").value("usuario@ecommerce.com"))
                        .andExpect(jsonPath("$[0].nome").value("Usuario Teste"));
            }
        }

        @Nested
        class Quando_buscar_por_id {

            @BeforeEach
            void setup() throws Exception {
                when(usuarioService.buscarPorId(any())).thenReturn(Optional.ofNullable(usuario));
                resultActions = mockMvc.perform(get("/usuarios/" + usuario.getId())
                        .contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            void Entao_deve_retornar_usuario_salvo_e_status_ok() throws Exception {
                resultActions.andExpect(status().isOk())
                        .andExpect(jsonPath("$.email").value("usuario@ecommerce.com"))
                        .andExpect(jsonPath("$.nome").value("Usuario Teste"));
            }
        }

        @Nested
        class Quando_atualizar {

            @BeforeEach
            void setUp() throws Exception {
                UsuarioDto usuarioDto = UsuarioDto.of(usuario);
                usuarioDto.setNome(usuarioDto.getNome() + " Atualizado");

                when(usuarioService.atualizar(usuarioDto.getId(), usuarioDto)).thenReturn(Usuario.of(usuarioDto));
                resultActions = mockMvc.perform(put("/usuarios/" + usuario.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"usuario@ecommerce.com\", \"nome\":\"Usuario Teste Atualizado\", \"senha\":\"senha_usuario\"}"));
            }

            @Test
            void Entao_deve_retornar_usuario_atualizado_e_status_ok() throws Exception {
                verify(usuarioService, times(1)).atualizar(eq(usuario.getId()), any(UsuarioDto.class));
                resultActions.andExpect(status().isOk())
                        .andExpect(jsonPath("$.nome").value("Usuario Teste Atualizado"));
            }
        }

        @Nested
        class Quando_excluir_por_id {

            @BeforeEach
            void setUp() throws Exception {
                resultActions = mockMvc.perform(delete("/usuarios/" + usuario.getId())
                        .contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            void Entao_deve_retornar_status_ok_ao_excluir_usuario() throws Exception {
                resultActions.andExpect(status().isNoContent());
                verify(usuarioService, times(1)).excluir(usuario.getId());
            }
        }
    }
}