package br.com.fs.ecommerce.foursalesecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String entidade) {
        super(String.format("Registro de %s não encontrado", entidade));
    }
}
