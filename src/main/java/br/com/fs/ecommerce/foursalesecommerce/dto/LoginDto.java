package br.com.fs.ecommerce.foursalesecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable {

    private String nome;
    private String email;
    private String senha;
}