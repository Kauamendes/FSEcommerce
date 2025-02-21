package br.com.fs.ecommerce.foursalesecommerce.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto implements Serializable {

    private String token;
}