package com.example.academia.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class AlunoResponseDTO {
    private final Long id;
    private final String nome;
    private final String email;
    private final LocalDate dataNascimento;
    private final String matricula;
    private final boolean ativo;
}
