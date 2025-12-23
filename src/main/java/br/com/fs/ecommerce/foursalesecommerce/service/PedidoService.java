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
    Optional<Pedido> buscarPorId(Long id);
    Pedido salvar(PedidoDto pedidoDto);
    Pedido atualizar(Long id, PedidoUpdateDto pedidoDto);
    void excluir(Long id);
    Pedido pagarPedido(Long id);

    List<Pedido> buscarPedidosUsuario(Long usuarioId);
    List<Pedido> buscarPedidosUsuarioAutenticado(String emailUsuarioAutenticado);

    List<TopCompradorDto> listarTopCompradores(Pageable pageable);
    List<TicketMedioDto> listarTicketMedioUsuarios(Pageable pageable);
    BigDecimal buscarValorFaturadoPorMesEAno(String mes, String ano);
}