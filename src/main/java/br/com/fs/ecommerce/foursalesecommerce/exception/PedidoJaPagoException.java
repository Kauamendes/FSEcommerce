package br.com.fs.ecommerce.foursalesecommerce.exception;

import br.com.fs.ecommerce.foursalesecommerce.support.MessageBundle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoJaPagoException extends RuntimeException {

    public PedidoJaPagoException() {
        super(MessageBundle.getMessage("pedidoJaPagoException"));
    }
}
