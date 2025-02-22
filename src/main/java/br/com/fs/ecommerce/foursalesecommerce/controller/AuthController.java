package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.dto.AuthDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.LoginDto;
import br.com.fs.ecommerce.foursalesecommerce.service.impl.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Operações relacionadas à autenticação JWT")
public class AuthController {

    private final LoginService loginService;

    @Autowired
    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Realiza a autenticação do usuário e retorna um token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public HttpEntity<AuthDto> login(@RequestBody LoginDto loginDto) throws AuthException {
       AuthDto authDto = loginService.logar(loginDto);
        return ResponseEntity.ok(authDto);
    }
}