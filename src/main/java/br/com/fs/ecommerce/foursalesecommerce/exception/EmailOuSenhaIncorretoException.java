package br.com.fs.ecommerce.foursalesecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EmailOuSenhaIncorretoException extends RuntimeException {

    public EmailOuSenhaIncorretoException(String msg) {
        super(msg);
    }
}
