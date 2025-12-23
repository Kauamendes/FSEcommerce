package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.domain.Produto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.ProdutoUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.service.ProdutoService;
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
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "Operações relacionadas a produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Retorna uma lista de todos os produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de produtos retornada com sucesso")
    })
    public Page<ProdutoDto> listar(@PageableDefault Pageable pageable) {
        return new PageImpl<>(produtoService.listar(pageable).stream()
                .map(ProdutoDto::of)
                .toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto", description = "Retorna um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de produto retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ProdutoDto buscarPorId(@PathVariable("id") Long id) {
        Produto produto = produtoService.buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Produto.class.getSimpleName(), id));
        return ProdutoDto.of(produto);
    }

    @PostMapping
    @Operation(summary = "Criar produto", description = "Cria um novo produto com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ProdutoDto salvar(@RequestBody @Validated ProdutoDto produtoDto) {
        return ProdutoDto.of(produtoService.salvar(produtoDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza um produto existente com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ProdutoDto atualizar(@RequestBody @Validated ProdutoUpdateDto produtoDto,
                                @PathVariable("id") Long id) {
        return ProdutoDto.of(produtoService.atualizar(id, produtoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir produto", description = "Exclui um produto com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}