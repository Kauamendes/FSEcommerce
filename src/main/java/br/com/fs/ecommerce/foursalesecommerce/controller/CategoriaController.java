package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.dto.CategoriaDto;
import br.com.fs.ecommerce.foursalesecommerce.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Operações relacionadas a categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar categorias", description = "Retorna uma lista de todas as categorias cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de categorias retornada com sucesso")
    })
    public List<CategoriaDto> listar() {
        return categoriaService.listar().stream()
                .map(CategoriaDto::of)
                .toList();
    }

    @PostMapping
    @Operation(summary = "Criar categoria", description = "Cria uma nova categoria com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public CategoriaDto salvar(@RequestBody @Validated CategoriaDto categoriaDto) {
        return CategoriaDto.of(categoriaService.salvar(categoriaDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza uma categoria existente com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public CategoriaDto atualizar(@RequestBody CategoriaDto categoriaDto,
                                  @PathVariable("id") String id) {
        return CategoriaDto.of(categoriaService.atualizar(id, categoriaDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir categoria", description = "Exclui uma categoria com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Void> excluir(@PathVariable("id") String id) {
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}