package br.com.fs.ecommerce.foursalesecommerce.domain;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USUARIO("ROLE_USUARIO");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}