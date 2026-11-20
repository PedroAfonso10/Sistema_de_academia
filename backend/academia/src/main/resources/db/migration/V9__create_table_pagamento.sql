CREATE TABLE pagamento (
    id BIGSERIAL PRIMARY KEY,
    contrato_id BIGINT NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    data_pagamento DATE NOT NULL,
    forma_pagamento VARCHAR(50) NOT NULL CHECK ( forma_pagamento IN ('PIX', 'CARTAO', 'BOLETO', 'DINHEIRO')),
    CONSTRAINT fk_pagamento_contrato FOREIGN KEY (contrato_id) REFERENCES contrato(id)
);