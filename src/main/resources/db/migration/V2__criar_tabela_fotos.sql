CREATE TABLE fotos (
    id BIGSERIAL PRIMARY KEY,
    imovel_id BIGINT NOT NULL,
    url VARCHAR(255) NOT NULL,
    legenda VARCHAR(200),
    principal BOOLEAN DEFAULT FALSE
);

