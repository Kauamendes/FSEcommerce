package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioResumidoDto;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResumidoDto> listar() {
        return usuarioService.listar().stream()
                .map(UsuarioResumidoDto::of)
                .toList();
    }

    @PostMapping
    public UsuarioResumidoDto salvar(@RequestBody @Validated UsuarioDto usuarioDto) {
        return UsuarioResumidoDto.of(usuarioService.salvar(usuarioDto));
    }

    @PutMapping("/{id}")
    public UsuarioResumidoDto atualizar(@RequestBody UsuarioDto usuarioDto,
                                        @PathVariable("id") String id) {
        return UsuarioResumidoDto.of(usuarioService.atualizar(usuarioDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") String id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}