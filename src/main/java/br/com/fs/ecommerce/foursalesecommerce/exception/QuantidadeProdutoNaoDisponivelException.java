package br.com.fs.ecommerce.foursalesecommerce.exception;

import br.com.fs.ecommerce.foursalesecommerce.support.MessageBundle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeProdutoNaoDisponivelException extends RuntimeException {

    public QuantidadeProdutoNaoDisponivelException(String nome, Integer quantidade) {
        super(MessageBundle.getMessage("quantidadeProdutoNaoDisponivelException", nome, quantidade));
    }
}
