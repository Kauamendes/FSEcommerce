package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.TicketMedioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.TopCompradorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Page<Pedido> listar(Pageable pageable);
    Optional<Pedido> buscarPorId(String id);
    Pedido salvar(PedidoDto pedidoDto);
    Pedido atualizar(String id, PedidoUpdateDto pedidoDto);
    void excluir(String id);
    Pedido pagarPedido(String id);

    List<Pedido> buscarPedidosUsuario(String usuarioId);
    List<Pedido> buscarPedidosUsuarioAutenticado(String emailUsuarioAutenticado);

    List<TopCompradorDto> listarTopCompradores(Pageable pageable);
    List<TicketMedioDto> listarTicketMedioUsuarios(Pageable pageable);
    BigDecimal buscarValorFaturadoPorMesEAno(String mes, String ano);
}