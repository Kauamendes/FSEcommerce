package br.com.fs.ecommerce.foursalesecommerce.exception;

import br.com.fs.ecommerce.foursalesecommerce.support.MessageBundle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontradoPorEmailException extends RuntimeException {

    public UsuarioNaoEncontradoPorEmailException(String email) {
        super(MessageBundle.getMessage("usuarioNaoEncontradoPorEmailException", email));
    }
}
