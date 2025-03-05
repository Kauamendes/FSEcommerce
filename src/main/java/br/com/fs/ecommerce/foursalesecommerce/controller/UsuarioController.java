package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioResumidoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.UsuarioUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de usuários retornada com sucesso")
    })
    public Page<UsuarioResumidoDto> listar(@PageableDefault Pageable pageable) {
        return new PageImpl<>(usuarioService.listar(pageable).stream()
                .map(UsuarioResumidoDto::of)
                .toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário", description = "Retorna um usuário pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de usuário retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public UsuarioResumidoDto buscarPorId(@PathVariable("id") String id) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Usuario.class.getSimpleName(), id));
        return UsuarioResumidoDto.of(usuario);
    }

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public UsuarioResumidoDto salvar(@RequestBody @Validated UsuarioDto usuarioDto) {
        return UsuarioResumidoDto.of(usuarioService.salvar(usuarioDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza um usuário existente com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public UsuarioResumidoDto atualizar(@RequestBody @Validated UsuarioUpdateDto usuarioDto,
                                        @PathVariable("id") String id) {
        return UsuarioResumidoDto.of(usuarioService.atualizar(id, usuarioDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário", description = "Exclui um usuário com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> excluir(@PathVariable("id") String id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}