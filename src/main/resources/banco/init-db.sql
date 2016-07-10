-- MySQL v5.5.49

CREATE DATABASE IF NOT EXISTS precodb;

use precodb;

create table IF NOT EXISTS bandeiras (
	id INT(6) AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	nome VARCHAR(255) NOT NULL UNIQUE,
	version BIGINT
);

CREATE TABLE IF NOT EXISTS cidades (
  codigo INT(6) NOT NULL UNIQUE PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  uf CHAR(2) NOT NULL
);

create table IF NOT EXISTS postos (
	id BIGINT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	id_bandeira INT(6),
	codigo_cidade INT(6),
	nome VARCHAR(255) NOT NULL UNIQUE,
	endereco VARCHAR(255) UNIQUE,
	detalhe_endereco VARCHAR(255),
	numero VARCHAR(6),
	version BIGINT,
	FOREIGN KEY (id_bandeira) REFERENCES bandeiras(id),
	FOREIGN KEY (codigo_cidade) REFERENCES cidades(codigo)
);

create table IF NOT EXISTS precos_postos (
	id BIGINT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	id_posto BIGINT NOT NULL,
	preco DECIMAL(2,2) NOT NULL,
	dh_data datetime,
	version BIGINT,
	FOREIGN KEY (id_posto) REFERENCES postos(id)
);

CREATE OR REPLACE VIEW vw_precos AS
SELECT id_posto, postos.nome, bandeiras.id, IFNULL(bandeiras.nome, 'Sem bandeira'), precos_postos.preco, dh_data
FROM precos_postos
JOIN postos ON (postos.id = precos_postos.id_posto)
LEFT JOIN bandeiras ON (bandeiras.id = postos.id_bandeira)
WHERE dh_data = (select max(dh_data) from precos_postos pp where pp.id_posto = precos_postos.id_posto)
group by precos_postos.id_posto;

