CREATE TABLE medico (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  telefone varchar (100),
  crm VARCHAR(6) NOT NULL UNIQUE,
  logradouro VARCHAR(100) NOT NULL,
  especialidade_id BIGINT NOT NULL,
  bairro VARCHAR(100) NOT NULL,
  cep VARCHAR(9) NOT NULL,
  complemento VARCHAR(100),
  numero VARCHAR(20),
  uf CHAR(2) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  ativo boolean,
  CONSTRAINT fk_medicos_especialidade
    FOREIGN KEY (especialidade_id)
    REFERENCES especialidade(id)
);