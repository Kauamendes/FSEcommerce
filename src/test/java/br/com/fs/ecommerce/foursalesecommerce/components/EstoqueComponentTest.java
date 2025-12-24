package br.com.fs.ecommerce.foursalesecommerce.components;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.domain.PedidoProduto;
import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EstoqueComponentTest extends AbstractH2Test {

    @Autowired
    private EstoqueComponent estoqueComponent;

    @Autowired
    private ProdutoRepository produtoRepository;

    private Long produtoId;

    @BeforeEach
    void prepareData() {
        Produto produto = new Produto();
        produto.setNome("Monitor 144hz");
        produto.setQuantidade(10);
        produto.setQuantidadeReservada(0);
        produto.setPreco(BigDecimal.TEN);

        produto = produtoRepository.save(produto);
        produtoId = produto.getId();
    }

    @Test
    void deve_reservar_estoque_usando_tenant_padrao() {
        PedidoProdutoDto item = new PedidoProdutoDto();
        item.setQuantidade(2);
        item.setProduto(ProdutoDto.builder().id(produtoId).build());

        estoqueComponent.reservarEstoque(List.of(item));

        Produto atualizado = produtoRepository.findById(produtoId).orElseThrow();
        assertEquals(2, atualizado.getQuantidadeReservada());
    }

    @Test
    void deve_baixar_estoque_real_e_limpar_reserva_apos_pagamento() {
        Produto produto = produtoRepository.findById(produtoId).orElseThrow();
        produto.setQuantidade(10);
        produto.setQuantidadeReservada(5);
        produtoRepository.saveAndFlush(produto);

        PedidoProduto itemPago = new PedidoProduto();
        itemPago.setProduto(produto);
        itemPago.setQuantidade(5);

        estoqueComponent.atualizarQuantidadeProdutoAposPagamento(List.of(itemPago));

        Produto resultado = produtoRepository.findById(produtoId).orElseThrow();

        assertEquals(5, resultado.getQuantidade());
        assertEquals(0, resultado.getQuantidadeReservada());
    }
}