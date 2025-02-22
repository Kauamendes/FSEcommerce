package br.com.fs.ecommerce.foursalesecommerce.exception;

import br.com.fs.ecommerce.foursalesecommerce.support.MessageBundle;

public class QuantidadeProdutoNaoDisponivelException extends RuntimeException {

    public QuantidadeProdutoNaoDisponivelException(String nome, Integer quantidade) {
        super(MessageBundle.getMessage("quantidadeProdutoNaoDisponivelException", nome, quantidade));
    }
}
