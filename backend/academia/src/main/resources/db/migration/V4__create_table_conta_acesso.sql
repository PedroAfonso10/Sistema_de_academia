CREATE TABLE conta_acesso (
    id BIGSERIAL PRIMARY KEY,
    pessoa_id BIGINT NOT NULL UNIQUE,
    senha_hash VARCHAR(100) NOT NULL,
    CONSTRAINT fk_conta_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);