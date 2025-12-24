package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.TopCompradorDto;
import br.com.fs.ecommerce.foursalesecommerce.service.PedidoService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WithMockUser
class PedidoControllerTest extends AbstractH2Test {

    @MockitoBean
    private PedidoService pedidoService;

    private Pedido criarPedidoMock(Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuario Teste");

        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setUsuario(usuario);
        pedido.setSubtotal(new BigDecimal("100.00"));
        pedido.setPedidoProdutos(new ArrayList<>());
        return pedido;
    }

    @Test
    void deve_listar_pedidos_paginados() throws Exception {
        Pedido pedido = criarPedidoMock(100L);

        when(pedidoService.listar(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(pedido)));

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(100L));
    }

    @Test
    void deve_listar_top_compradores() throws Exception {
        TopCompradorDto dto = new TopCompradorDto(1L, "Usuario Teste", 5L, new BigDecimal("1000.00"));

        when(pedidoService.listarTopCompradores(any(Pageable.class)))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/pedidos/top/compradores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(1L))
                .andExpect(jsonPath("$[0].usuarioNome").value("Usuario Teste"))
                .andExpect(jsonPath("$[0].totalPedidos").value(5))
                .andExpect(jsonPath("$[0].totalGasto").value(1000.00));
    }

    @Test
    @WithMockUser(username = "admin@test.com")
    void deve_buscar_pedidos_do_usuario_autenticado() throws Exception {
        Pedido pedido = criarPedidoMock(1L);

        when(pedidoService.buscarPedidosUsuarioAutenticado(any()))
                .thenReturn(List.of(pedido));

        mockMvc.perform(get("/pedidos/usuario-autenticado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void deve_pagar_pedido_com_sucesso() throws Exception {
        Pedido pedido = criarPedidoMock(1L);

        when(pedidoService.pagarPedido(1L)).thenReturn(pedido);

        mockMvc.perform(post("/pedidos/pagar/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deve_buscar_valor_faturado_por_mes_e_ano() throws Exception {
        when(pedidoService.buscarValorFaturadoPorMesEAno("12", "2025"))
                .thenReturn(new BigDecimal("5000.00"));

        mockMvc.perform(get("/pedidos/12/2025/valor-faturado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(5000.00));
    }

    @Test
    void deve_retornar_404_ao_buscar_pedido_inexistente() throws Exception {
        when(pedidoService.buscarPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/pedidos/{id}", 99L))
                .andExpect(status().isNotFound());
    }
}