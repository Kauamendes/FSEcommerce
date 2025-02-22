package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.components.EstoqueComponent;
import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.domain.Status;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.TicketMedioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.TopCompradorDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.PedidoJaPagoException;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.PedidoRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstoqueComponent estoqueComponent;

    @Override
    public Page<Pedido> listar(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Override
    public Optional<Pedido> buscarPorId(String id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido salvar(PedidoDto pedidoDto) {
        estoqueComponent.reservarEstoque(pedidoDto.getPedidoProdutos());
        pedidoDto.calcularSubtotal();
        return pedidoRepository.save(Pedido.of(pedidoDto));
    }

    @Override
    public Pedido atualizar(String id, PedidoDto pedidoDto) {
       if (!pedidoRepository.existsById(id)) {
           throw new RegistroNaoEncontradoException(Pedido.class.getSimpleName(), id);
       }
        estoqueComponent.reservarEstoque(pedidoDto.getPedidoProdutos());
        pedidoDto.setId(id);
        pedidoDto.calcularSubtotal();
       return pedidoRepository.save(Pedido.of(pedidoDto));
    }

    @Override
    public void excluir(String id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException(Pedido.class.getSimpleName(), id);
        }
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido pagarPedido(String id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Pedido.class.getSimpleName(), id));

        if (Objects.equals(Status.PAGO, pedido.getStatus()))
            throw new PedidoJaPagoException();

        pedidoRepository.updateStatusById(pedido.getId(), Status.PAGO);
        estoqueComponent.atualizarQuantidadeProdutoAposPagamento(pedido.getPedidoProdutos());
        return pedido;
    }

    @Override
    public List<Pedido> buscarPedidosUsuario(String usuarioId) {
        return pedidoRepository.findAllByUsuarioId(usuarioId);
    }

    @Override
    public List<Pedido> buscarPedidosUsuarioAutenticado(String email) {
        return pedidoRepository.findAllByUsuarioEmail(email);
    }

    @Override
    public List<TopCompradorDto> listarTopCompradores(Pageable pageable) {
        return pedidoRepository.findTopCompradores(pageable);
    }

    @Override
    public List<TicketMedioDto> listarTicketMedioUsuarios(Pageable pageable) {
        return pedidoRepository.findTicketMedioPorUsuario(pageable);
    }

    @Override
    public BigDecimal buscarValorFaturadoPorMesEAno(String mes, String ano) {
        return pedidoRepository.findValorFaturadoPorMesEAno(mes, ano);
    }
}