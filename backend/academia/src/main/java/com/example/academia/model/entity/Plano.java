package com.example.academia.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "plano")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "valor_mensal", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorMensal;

    @Column(name = "duracao_meses", nullable = false)
    private Integer duracaoMeses;

    @Builder.Default
    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    public void atualizar(String nome, BigDecimal valorMensal, Integer duracaoMeses) {
        this.nome = nome;
        this.valorMensal = valorMensal;
        this.duracaoMeses = duracaoMeses;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
