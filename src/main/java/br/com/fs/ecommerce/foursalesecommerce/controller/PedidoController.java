package br.com.fs.ecommerce.foursalesecommerce.controller;

import br.com.fs.ecommerce.foursalesecommerce.domain.Pedido;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.PedidoUpdateDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.TicketMedioDto;
import br.com.fs.ecommerce.foursalesecommerce.dto.TopCompradorDto;
import br.com.fs.ecommerce.foursalesecommerce.exception.RegistroNaoEncontradoException;
import br.com.fs.ecommerce.foursalesecommerce.service.PedidoService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Operações relacionadas a pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Retorna uma lista de todos os pedidos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de pedidos retornada com sucesso")
    })
    public Page<PedidoDto> listar(@PageableDefault Pageable pageable) {
        return new PageImpl<>(pedidoService.listar(pageable).stream()
                .map(PedidoDto::of)
                .toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido", description = "Retorna um pedido pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de pedido retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public PedidoDto buscarPorId(@PathVariable("id") Long id) {
        Pedido pedido = pedidoService.buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Pedido.class.getSimpleName(), id));
        return PedidoDto.of(pedido);
    }

    @GetMapping("/top/compradores")
    @Operation(summary = "Listar top compradores", description = "Retorna uma lista dos usuários que mais realizaram compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de top compradores retornada com sucesso")
    })
    public List<TopCompradorDto> listarUsuariosQueMaisCompraram(@PageableDefault(size = 10) Pageable pageable) {
        return pedidoService.listarTopCompradores(pageable);
    }

    @GetMapping("/usuarios/ticket-medio")
    @Operation(summary = "Listar ticket médio por usuário", description = "Retorna o ticket médio de compra por usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket médio retornado com sucesso")
    })
    public List<TicketMedioDto> listarTicketMedioPorUsuario(@PageableDefault Pageable pageable) {
        return pedidoService.listarTicketMedioUsuarios(pageable);
    }

    @GetMapping("/{mes}/{ano}/valor-faturado")
    @Operation(summary = "Buscar valor faturado no mês", description = "Retorna o valor faturado em um determinado mês e ano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valor faturado retornado com sucesso")
    })
    public BigDecimal buscarValorFaturadoNoMesEAno(@PathVariable("mes") String mes,
                                                   @PathVariable("ano") String ano) {
        return pedidoService.buscarValorFaturadoPorMesEAno(mes, ano);
    }

    @GetMapping(params = "{usuarioId}")
    @Operation(summary = "Buscar pedidos do usuario", description = "Retorna os pedidos feitos pelo usuario informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos do usuário retornados com sucesso")
    })
    public List<PedidoDto> buscarPedidosUsuario(@RequestParam("usuarioId") Long usuarioId) {
        return pedidoService.buscarPedidosUsuario(usuarioId).stream()
                .map(PedidoDto::of)
                .toList();
    }

    @GetMapping("/usuario-autenticado")
    @Operation(summary = "Buscar pedidos do usuario autenticado", description = "Retorna os pedidos feitos pelo usuario autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos do usuário autenticado retornados com sucesso")
    })
    public List<PedidoDto> buscarPedidosUsuarioAutenticado() {
        String emailUsuarioAutenticado = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return pedidoService.buscarPedidosUsuarioAutenticado(emailUsuarioAutenticado).stream()
                .map(PedidoDto::of)
                .toList();
    }

    @PostMapping
    @Operation(summary = "Criar pedido", description = "Cria um novo pedido com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public PedidoDto salvar(@RequestBody @Validated PedidoDto pedidoDto) {
        return PedidoDto.of(pedidoService.salvar(pedidoDto));
    }

    @PostMapping("/pagar/{id}")
    @Operation(summary = "Pagar pedido", description = "Realiza o pagamento de um pedido com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido pago com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public PedidoDto pagarPedido(@PathVariable("id") Long id) {
        return PedidoDto.of(pedidoService.pagarPedido(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pedido", description = "Atualiza um pedido existente com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public PedidoDto atualizar(@RequestBody @Validated PedidoUpdateDto pedidoDto,
                               @PathVariable("id") Long id) {
        return PedidoDto.of(pedidoService.atualizar(id, pedidoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pedido", description = "Exclui um pedido com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}