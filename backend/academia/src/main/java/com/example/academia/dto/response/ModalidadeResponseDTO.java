package com.example.academia.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModalidadeResponseDTO {
    private final Long id;
    private final String nome;
    private final String descricao;
    private final boolean ativo;
}
