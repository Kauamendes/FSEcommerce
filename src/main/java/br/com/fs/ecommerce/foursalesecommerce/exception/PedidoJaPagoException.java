package br.com.fs.ecommerce.foursalesecommerce.exception;

import br.com.fs.ecommerce.foursalesecommerce.support.MessageBundle;

public class PedidoJaPagoException extends RuntimeException {

    public PedidoJaPagoException() {
        super(MessageBundle.getMessage("pedidoJaPago"));
    }
}
