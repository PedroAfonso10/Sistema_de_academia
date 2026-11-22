package com.example.academia.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlanoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotNull(message = "Valor mensal é obrigatório")
    @DecimalMin(value = "0.00", inclusive = false, message = "Valor mensal deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "Valor mensal deve ter no máximo 2 casas decimais")
    private BigDecimal valorMensal;

    @NotNull(message = "Duração é obrigatória")
    @Min(value = 1, message = "Duração deve ser de pelo menos 1 mês")
    private Integer duracaoMeses;

    @NotEmpty(message = "O plano deve conter ao menos uma modalidade")
    private Set<@NotNull(message = "Id de modalidade inválido") Long> modalidadeIds;
}