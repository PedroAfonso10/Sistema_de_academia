package com.example.academia.dto.response;

import com.example.academia.model.enums.StatusContrato;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class ContratoResponseDTO {
    private final Long id;
    private final String nomeAluno;
    private final String nomePlano;
    private final String matricula;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final BigDecimal valorContratado;
    private final StatusContrato statusContrato;
}