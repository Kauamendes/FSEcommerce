package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.*;
import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.PedidoJaPagoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.PedidoRepository;
import br.com.fs.ecommerce.foursalesecommerce.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PedidoServiceTest extends AbstractH2Test {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    void deve_salvar_pedido_e_reservar_estoque() {
        Categoria categoria = new Categoria(1L, "test", "teste", true);
        Produto produto = produtoRepository.save(new Produto(null, "SSD", "", BigDecimal.TEN, categoria, 10, 0, null, true));

        PedidoDto dto = new PedidoDto();
        dto.setStatus(Status.PENDENTE);
        dto.setPedidoProdutos(List.of(new PedidoProdutoDto(null,
                PedidoDto.builder().build(),
                ProdutoDto.builder().id(produto.getId()).categoria(CategoriaDto.builder().id(200L).build()).build(),
                2,
                BigDecimal.TEN)));

        Pedido pedido = pedidoService.salvar(dto);

        assertNotNull(pedido.getId());
        Produto produtoAposReserva = produtoRepository.findById(produto.getId()).orElseThrow();
        assertEquals(2, produtoAposReserva.getQuantidadeReservada());
    }

    @Test
    void deve_mudar_status_para_pago_e_baixar_estoque_definitivo() {
        Pedido pedido = new Pedido();
        pedido.setStatus(Status.PENDENTE);
        pedido.setUsuario(Usuario.builder().id(100L).build());
        pedido.setSubtotal(BigDecimal.TEN);
        pedido = pedidoRepository.save(pedido);

        Pedido pago = pedidoService.pagarPedido(pedido.getId());

        assertEquals(Status.PAGO, pago.getStatus());
    }

    @Test
    void deve_lancar_excecao_ao_pagar_pedido_ja_pago() {
        Pedido pedido = new Pedido();
        pedido.setStatus(Status.PAGO);
        pedido = pedidoRepository.save(pedido);

        final Long id = pedido.getId();
        assertThrows(PedidoJaPagoException.class, () -> pedidoService.pagarPedido(id));
    }
}
