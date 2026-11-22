package com.example.academia.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class PlanoResponseDTO {
    private final Long id;
    private final String nome;
    private final BigDecimal valorMensal;
    private final Integer duracaoMeses;
    private final boolean ativo;
    private final List<ModalidadeResponseDTO> modalidades;
}
