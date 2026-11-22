package com.example.academia.dto.response;

import com.example.academia.model.enums.FormaPagamento;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class PagamentoResponseDTO {
    private final Long id;
    private final Long contratoId;
    private final FormaPagamento formaPagamento;
    private final BigDecimal valorPagamento;
    private final LocalDate dataPagamento;
}
