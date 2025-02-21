package br.com.fs.ecommerce.foursalesecommerce.service;

import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoDto;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> listar();
    Optional<Pedido> buscarPorId(String id);
    Pedido salvar(PedidoDto pedidoDto);
    Pedido atualizar(PedidoDto pedidoDto, String id);
    void excluir(String id);
}