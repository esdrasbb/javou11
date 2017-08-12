
CREATE TABLE estado (
  id bigserial PRIMARY KEY,
  sigla varchar(2) NOT NULL,
  nome varchar(20) NOT NULL
);

CREATE TABLE cidade (
  id bigserial PRIMARY KEY,
  nome varchar(50) NOT NULL,
  id_estado bigint REFERENCES estado (id)
);

