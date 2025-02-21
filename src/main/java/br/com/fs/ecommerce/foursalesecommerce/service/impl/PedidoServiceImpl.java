package br.com.fs.ecommerce.foursalesecommerce.service.impl;

import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.domain.Status;
import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.PedidoJaPagoException;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.exception.UsuarioNaoEncontradoPorEmailException;
import br.com.fs.ecommerce.foursalesecommerce.repository.PedidoRepository;
import br.com.fs.ecommerce.foursalesecommerce.repository.UsuarioRepository;
import br.com.fs.ecommerce.foursalesecommerce.service.PedidoService;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> buscarPorId(String id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido salvar(PedidoDto pedidoDto) {
        return pedidoRepository.save(Pedido.of(pedidoDto));
    }

    @Override
    public Pedido atualizar(PedidoDto pedidoDto, String id) {
       if (!pedidoRepository.existsById(id)) {
           throw new RegistroNaoEncontradoException(Pedido.class.getSimpleName(), id);
       }
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

        if (Objects.equals(Status.PAGO, pedido.getStatus())) throw new PedidoJaPagoException();

        pedido.setStatus(Status.PAGO);
        return pedidoRepository.save(pedido);
    }
}