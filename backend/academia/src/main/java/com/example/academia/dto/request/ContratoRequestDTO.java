package com.example.academia.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContratoRequestDTO {

    @NotNull(message = "alunoId é obrigatório")
    private Long alunoId;

    @NotNull(message = "planoId é obrigatório")
    private Long planoId;
}