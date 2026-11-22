package com.example.academia.dto.response;

import com.example.academia.model.enums.Perfil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class FuncionarioResponseDTO {
    private final Long id;
    private final String nome;
    private final String email;
    private final LocalDate dataNascimento;
    private final LocalDate dataAdmissao;
    private final Perfil perfil;
    private final boolean ativo;
}
