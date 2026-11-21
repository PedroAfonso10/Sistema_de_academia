package com.example.academia.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plano_modalidade")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class PlanoModalidade {

    @EmbeddedId
    private PlanoModalidadeId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("planoId")
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("modalidadeId")
    @JoinColumn(name = "modalidade_id", nullable = false)
    private Modalidade modalidade;

    public static PlanoModalidade vincular(Plano plano, Modalidade modalidade) {
        PlanoModalidade vinculo = new PlanoModalidade();
        vinculo.plano = plano;
        vinculo.modalidade = modalidade;
        vinculo.id = new PlanoModalidadeId(plano.getId(), modalidade.getId());
        return vinculo;
    }
}
