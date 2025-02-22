package br.com.fs.ecommerce.foursalesecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketMedioDto {

    private String usuarioId;
    private String usuarioNome;
    private Double ticketMedio;
}