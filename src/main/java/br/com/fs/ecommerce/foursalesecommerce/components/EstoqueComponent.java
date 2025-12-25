package br.com.fs.ecommerce.foursalesecommerce.components;

import br.com.fs.ecommerce.foursalesecommerce.domain.PedidoProduto;
import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.QuantidadeProdutoNaoDisponivelException;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class EstoqueComponent {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public void reservarEstoque(List<PedidoProdutoDto> pedidoProdutos) {
        List<Long> produtoIds = pedidoProdutos.stream()
                .map(p -> p.getProduto().getId())
                .toList();

        Map<Long, Produto> produtosMap = produtoRepository.findAllByIdComTenant(produtoIds).stream()
                .collect(Collectors.toMap(Produto::getId, produto -> produto));

        for (PedidoProdutoDto pedidoProduto : pedidoProdutos) {
            Produto produto = produtosMap.get(pedidoProduto.getProduto().getId());

            if (isNull(produto)) {
                throw new RegistroNaoEncontradoException(Produto.class.getSimpleName(), pedidoProduto.getProduto().getId());
            }

            int estoqueDisponivel = produto.getQuantidade() - produto.getQuantidadeReservada();
            if (estoqueDisponivel < pedidoProduto.getQuantidade()) {
                throw new QuantidadeProdutoNaoDisponivelException(produto.getNome(), pedidoProduto.getQuantidade());
            }
            produto.setQuantidadeReservada(produto.getQuantidadeReservada() + pedidoProduto.getQuantidade());
        }

        produtoRepository.saveAll(produtosMap.values());
    }

    @Transactional
    public void atualizarQuantidadeProdutoAposPagamento(List<PedidoProduto> pedidoProdutos) {
        List<Long> produtoIds = pedidoProdutos.stream()
                .map(pd -> pd.getProduto().getId())
                .toList();

        Map<Long, Produto> produtosMap = produtoRepository.findAllById(produtoIds).stream()
                .collect(Collectors.toMap(Produto::getId, produto -> produto));

        for (PedidoProduto pedidoProduto : pedidoProdutos) {
            Produto produto = produtosMap.get(pedidoProduto.getProduto().getId());

            if (isNull(produto)) {
                throw new RegistroNaoEncontradoException(Produto.class.getSimpleName(), pedidoProduto.getProduto().getId());
            }

            produto.setQuantidade(produto.getQuantidade() - pedidoProduto.getQuantidade());
            produto.setQuantidadeReservada(produto.getQuantidadeReservada() - pedidoProduto.getQuantidade());
        }

        produtoRepository.saveAll(produtosMap.values());
    }
}
