package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.dto.AuthDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.LoginDto;
import br.com.fs.ecommerce.foursalesecommerce.service.impl.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    public AuthDto login(@RequestBody LoginDto loginDto){
       return loginService.logar(loginDto);
    }
}