CREATE TABLE aluno (
    pessoa_id BIGINT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    CONSTRAINT fk_aluno_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);