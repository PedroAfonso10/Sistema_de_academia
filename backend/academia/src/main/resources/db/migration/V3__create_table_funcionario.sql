CREATE TABLE funcionario (
    pessoa_id BIGINT PRIMARY KEY,
    perfil VARCHAR(30) NOT NULL CHECK (perfil IN ('FUNCIONARIO', 'ADMIN')),
    data_admissao DATE NOT NULL,
    CONSTRAINT fk_funcionario_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);