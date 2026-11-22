package com.example.academia.dto.request;

import com.example.academia.model.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagamentoRequestDTO {

    @NotNull(message = "contratoId é obrigatório")
    private Long contratoId;

    @NotNull(message = "Forma de pagamento é obrigatória (PIX, CARTAO, BOLETO ou DINHEIRO)")
    private FormaPagamento formaPagamento;
}