package br.com.fs.ecommerce.foursalesecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopCompradorDto {

    private Long usuarioId;
    private String usuarioNome;
    private Long totalPedidos;
    private BigDecimal totalGasto;
}