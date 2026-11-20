CREATE TABLE plano_modalidade (
    modalidade_id BIGINT NOT NULL,
    plano_id BIGINT NOT NULL,
    PRIMARY KEY (plano_id, modalidade_id),
    CONSTRAINT fk_plano_modalidade_plano FOREIGN KEY (plano_id) REFERENCES plano(id),
    CONSTRAINT fk_plano_modalidade_modalidade FOREIGN KEY (modalidade_id) REFERENCES modalidade(id)
);