package br.com.fs.ecommerce.foursalesecommerce.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class AuthDto implements Serializable {

    private String token;
}