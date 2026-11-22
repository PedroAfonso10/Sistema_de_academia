package com.example.academia.model.entity;

import com.example.academia.model.enums.StatusContrato;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * EVOLUÇÃO: substituir IllegalArgumentException/IllegalStateException
 * por exceções personalizadas de domínio.
 */
@Entity
@Table(name = "contrato")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "valor_contratado", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorContratado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_contrato", nullable = false, length = 50)
    private StatusContrato statusContrato;

    public Contrato(Aluno aluno, Plano plano, LocalDate dataInicio) {
        this.aluno = aluno;
        this.plano = plano;
        this.dataInicio = dataInicio;
        calcularDataFim();
        this.valorContratado = plano.getValorMensal();
        this.statusContrato = StatusContrato.ATIVO;
    }

    private void calcularDataFim() {
        this.dataFim = this.dataInicio.plusMonths(this.plano.getDuracaoMeses());
    }

    public boolean estaVencido(LocalDate dataAtual) {
        return dataAtual.isAfter(this.dataFim);
    }

    public boolean ativar() {
        return this.statusContrato == StatusContrato.ATIVO;
    }

    public boolean encerrarSeVencido(LocalDate dataAtual) {
        if (ativar() && estaVencido(dataAtual)) {
            this.statusContrato = StatusContrato.ENCERRADO;
            return true;
        }
        return false;
    }

    public void encerrar() {
        validarContratoAtivo("encerrar");
        this.statusContrato = StatusContrato.ENCERRADO;
    }

    public void cancelar() {
        validarContratoAtivo("cancelar");
        this.statusContrato = StatusContrato.CANCELADO;
    }

    private void validarContratoAtivo(String acao) {
        if (!ativar()) {
            throw new IllegalStateException(
                    "Não é possível " + acao + " um contrato com status " + this.statusContrato + ".");
        }
    }
}