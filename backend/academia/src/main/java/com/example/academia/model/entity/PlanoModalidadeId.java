package com.example.academia.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class PlanoModalidadeId implements Serializable {

    @Column(name = "plano_id")
    private Long planoId;

    @Column(name = "modalidade_id")
    private Long modalidadeId;

    public PlanoModalidadeId(Long planoId, Long modalidadeId) {
        this.planoId = planoId;
        this.modalidadeId = modalidadeId;
    }
}
