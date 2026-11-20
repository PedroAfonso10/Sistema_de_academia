CREATE TABLE contrato (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    plano_id BIGINT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL CHECK ( data_fim >= data_inicio ),
    valor_contratado NUMERIC(10, 2) NOT NULL,
    status_contrato VARCHAR(50) NOT NULL CHECK ( status_contrato IN ('ATIVO', 'CANCELADO', 'ENCERRADO')),
    CONSTRAINT fk_contrato_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(pessoa_id),
    CONSTRAINT fk_contrato_plano FOREIGN KEY (plano_id) REFERENCES plano(id)
);