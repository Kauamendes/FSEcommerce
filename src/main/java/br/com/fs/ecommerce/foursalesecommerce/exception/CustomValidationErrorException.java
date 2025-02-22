package br.com.fs.ecommerce.foursalesecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@AllArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomValidationErrorException extends RuntimeException {

    private final List<String> errors;
}