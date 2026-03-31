-- GadoBoi initial schema
-- Migrated from Google Datastore entities (legacy project)

CREATE TABLE endereco (
    id         BIGSERIAL PRIMARY KEY,
    bairro     VARCHAR(100),
    numero     VARCHAR(20),
    complemento VARCHAR(100),
    cidade     VARCHAR(100) NOT NULL,
    estado     VARCHAR(2)   NOT NULL
);

CREATE TABLE cliente (
    id         BIGSERIAL PRIMARY KEY,
    nome       VARCHAR(100) NOT NULL,
    sobrenome  VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL UNIQUE,
    cpf        VARCHAR(14)  NOT NULL UNIQUE,
    rg         VARCHAR(20),
    telefone   VARCHAR(20),
    celular    VARCHAR(20),
    sexo       VARCHAR(1),
    data_nasc  DATE,
    senha_hash VARCHAR(255) NOT NULL,
    endereco_id BIGINT REFERENCES endereco(id)
);

CREATE TABLE gado (
    id         BIGSERIAL PRIMARY KEY,
    nome_gado  VARCHAR(150) NOT NULL,
    cliente_id BIGINT NOT NULL REFERENCES cliente(id)
);

CREATE TABLE insumo (
    id          BIGSERIAL PRIMARY KEY,
    nome_insumo VARCHAR(150) NOT NULL,
    cliente_id  BIGINT NOT NULL REFERENCES cliente(id)
);

CREATE TABLE manejo (
    id          BIGSERIAL PRIMARY KEY,
    nome_manejo VARCHAR(150) NOT NULL,
    cliente_id  BIGINT NOT NULL REFERENCES cliente(id)
);

CREATE TABLE regiao (
    id          BIGSERIAL PRIMARY KEY,
    nome_regiao VARCHAR(150) NOT NULL,
    cliente_id  BIGINT NOT NULL REFERENCES cliente(id)
);

CREATE TABLE racao (
    id                    BIGSERIAL PRIMARY KEY,
    nome_racao            VARCHAR(200) NOT NULL,
    taxa_rendimento       NUMERIC(10,4),
    data                  DATE,
    horario               TIME,
    status                VARCHAR(1),     -- 'V' = for sale, NULL = internal
    qtd_gado              INTEGER,
    qtd_tratamento_dia    INTEGER,
    peso_inicial          NUMERIC(10,2),
    gado_id               BIGINT REFERENCES gado(id),
    regiao_id             BIGINT REFERENCES regiao(id),
    manejo_id             BIGINT REFERENCES manejo(id),
    cliente_id            BIGINT NOT NULL REFERENCES cliente(id)
);

CREATE TABLE racao_insumo (
    id         BIGSERIAL PRIMARY KEY,
    racao_id   BIGINT NOT NULL REFERENCES racao(id) ON DELETE CASCADE,
    insumo_id  BIGINT NOT NULL REFERENCES insumo(id),
    qtd_insumo NUMERIC(10,4) NOT NULL
);

CREATE TABLE metodo (
    id          BIGSERIAL PRIMARY KEY,
    nome_metodo VARCHAR(200) NOT NULL,
    racao_id    BIGINT NOT NULL REFERENCES racao(id) ON DELETE CASCADE
);

CREATE TABLE rendimento (
    id                          BIGSERIAL PRIMARY KEY,
    cliente_id                  BIGINT REFERENCES cliente(id),
    qtd_animais                 INTEGER      NOT NULL,
    periodo_de_tratamento       INTEGER      NOT NULL,
    tamanho_da_pastagem         NUMERIC(12,2) NOT NULL,
    peso_inicial                NUMERIC(10,2) NOT NULL,
    ganho_de_peso_esperado      NUMERIC(10,4) NOT NULL,
    rendimento_carcaca          NUMERIC(5,4)  NOT NULL,
    preco_arroba                NUMERIC(10,2) NOT NULL,
    preco_por_quilo_con         NUMERIC(10,4) NOT NULL,
    -- calculated fields
    taxa_lotacao                NUMERIC(10,4),
    qtd_concentrado_diariamente NUMERIC(10,4),
    qtd_concentrado_total       NUMERIC(12,4),
    preco_concentrado_dia_animal NUMERIC(10,4),
    ganho_por_cabeca_dia        NUMERIC(10,4),
    custo_total                 NUMERIC(14,2),
    ganho_total                 NUMERIC(14,2),
    resultado                   NUMERIC(14,2),
    calculado_em                TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
