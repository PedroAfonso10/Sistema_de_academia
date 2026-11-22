package com.example.academia.model.entity;

import com.example.academia.model.enums.FormaPagamento;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false, length = 50)
    private FormaPagamento formaPagamento;

    public Pagamento(Contrato contrato, BigDecimal valor, LocalDate dataPagamento, FormaPagamento formaPagamento) {
        if (!contrato.isAtivo()) {
            throw new IllegalStateException("O pagamento exige um contrato ativo.");
        }
        this.contrato = contrato;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
    }
}
