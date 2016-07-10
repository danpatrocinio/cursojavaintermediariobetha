-- MySQL v5.5.49

CREATE DATABASE IF NOT EXISTS precodb;

use precodb;

create table IF NOT EXISTS cidades (
	codigo INT(6) NOT NULL UNIQUE PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	uf CHAR(2)
) Engine = INNODB;

create table IF NOT EXISTS bandeiras (
	id INT(6) AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	nome VARCHAR(255) NOT NULL UNIQUE,
	version BIGINT
) Engine = INNODB;

CREATE TRIGGER versioning_bandeiras_ins BEFORE INSERT ON bandeiras FOR EACH ROW SET NEW.version = (select now() + 0);
CREATE TRIGGER versioning_bandeiras_upd BEFORE UPDATE ON bandeiras FOR EACH ROW SET NEW.version = (select now() + 0);

-- POSTOS
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
) Engine = INNODB;

CREATE TRIGGER versioning_postos_ins BEFORE INSERT ON postos FOR EACH ROW SET NEW.version = (select now() + 0);
CREATE TRIGGER versioning_postos_upd BEFORE UPDATE ON postos FOR EACH ROW SET NEW.version = (select now() + 0);

-- POSTOS_PRECOS
create table IF NOT EXISTS postos_precos (
	id BIGINT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	id_posto BIGINT NOT NULL,
	preco DECIMAL(4,2) NOT NULL,
	dh_data datetime,
	version BIGINT,
	FOREIGN KEY (id_posto) REFERENCES postos(id)
) Engine = INNODB;

CREATE TRIGGER versioning_postos_precos_ins BEFORE INSERT ON postos_precos FOR EACH ROW SET NEW.version = (select now() + 0);
CREATE TRIGGER versioning_postos_precos_upd BEFORE UPDATE ON postos_precos FOR EACH ROW SET NEW.version = (select now() + 0);

-- VIEW PARA VISUALIZAÇÃO RESUMIDA
CREATE OR REPLACE VIEW vw_precos AS
SELECT id_posto, postos.nome as nome_posto, 
	   bandeiras.id as id_bandeira, IFNULL(bandeiras.nome, 'Sem bandeira - (bandeira branca)') as nome_bandeira, 
	   cidades.nome as cidade, postos.endereco, postos_precos.preco, dh_data as data
FROM postos_precos
JOIN postos ON (postos.id = postos_precos.id_posto)
JOIN cidades ON (cidades.codigo = postos.codigo_cidade)
LEFT JOIN bandeiras ON (bandeiras.id = postos.id_bandeira)
WHERE dh_data = (select max(dh_data) from postos_precos pp where pp.id_posto = postos_precos.id_posto)
group by postos_precos.id_posto;

