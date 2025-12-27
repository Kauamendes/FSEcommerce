package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.AbstractH2Test;
import br.com.fs.ecommerce.foursalesecommerce.dto.AuthDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.LoginDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.EmailOuSenhaIncorretoException;
import br.com.fs.ecommerce.foursalesecommerce.service.impl.LoginService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthControllerTest extends AbstractH2Test {

    @MockitoBean
    private LoginService loginService;

    @Test
    void deve_retornar_ok_ao_logar_com_crendeciais_validadas() throws Exception {
        LoginDto loginDto = new LoginDto("admin@fsecommerce.com", "senha_admin");
        AuthDto authDto = new AuthDto("token-fake");

        when(loginService.logar(any(LoginDto.class))).thenReturn(authDto);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deve_retornar_nao_autorizado_ao_logar_com_crendeciais_invalidas() throws Exception {
        when(loginService.logar(any(LoginDto.class))).thenThrow(new EmailOuSenhaIncorretoException("test"));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new LoginDto("email", "senha"))))
                .andExpect(status().isUnauthorized());
    }
}
