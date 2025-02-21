package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDto> listar() {
        return usuarioService.listar().stream()
                .map(UsuarioDto::of)
                .toList();
    }

    @PostMapping
    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        return UsuarioDto.of(usuarioService.salvar(usuarioDto));
    }

    @PutMapping
    public UsuarioDto atualizar(UsuarioDto usuarioDto, String id) {
        return UsuarioDto.of(usuarioService.atualizar(usuarioDto, id));
    }

    @DeleteMapping
    public void excluir(String id) {
        usuarioService.excluir(id);
    }
}