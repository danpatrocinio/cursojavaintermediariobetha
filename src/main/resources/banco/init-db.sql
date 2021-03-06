-- MySQL v5.5.49

CREATE DATABASE IF NOT EXISTS precodb;

use precodb;

create table IF NOT EXISTS cidades (
	id INT(6) NOT NULL UNIQUE PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	uf CHAR(2)
) Engine = INNODB;

-- BANDEIRAS
create table IF NOT EXISTS bandeiras (
	id INT(6) AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	nome VARCHAR(255) NOT NULL UNIQUE,
	version BIGINT
) Engine = INNODB;

CREATE TRIGGER versioning_bandeiras_ins BEFORE INSERT ON bandeiras FOR EACH ROW SET NEW.version = (select now() + 0);
CREATE TRIGGER versioning_bandeiras_upd BEFORE UPDATE ON bandeiras FOR EACH ROW SET NEW.version = (select now() + 0);

-- COMBUSTÍVEIS
create table IF NOT EXISTS combustiveis (
	id INT(3) AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	descricao VARCHAR(25) NOT NULL,
	unidade_medida CHAR(2),
	version BIGINT
) Engine = INNODB;

CREATE TRIGGER versioning_combustiveis_ins BEFORE INSERT ON combustiveis FOR EACH ROW SET NEW.version = (select now() + 0);
CREATE TRIGGER versioning_combustiveis_upd BEFORE UPDATE ON combustiveis FOR EACH ROW SET NEW.version = (select now() + 0);

-- POSTOS
create table IF NOT EXISTS postos (
	id BIGINT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	id_bandeira INT(6),
	id_cidade INT(6),
	nome VARCHAR(255) NOT NULL,
	endereco VARCHAR(255) UNIQUE,
	complemento VARCHAR(255),
	numero VARCHAR(6),
	version BIGINT,
	FOREIGN KEY (id_bandeira) REFERENCES bandeiras(id),
	FOREIGN KEY (id_cidade) REFERENCES cidades(id)
) Engine = INNODB;

CREATE TRIGGER versioning_postos_ins BEFORE INSERT ON postos FOR EACH ROW SET NEW.version = (select now() + 0);
CREATE TRIGGER versioning_postos_upd BEFORE UPDATE ON postos FOR EACH ROW SET NEW.version = (select now() + 0);

-- POSTOS_PRECOS
create table IF NOT EXISTS postos_precos (
	id BIGINT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
	id_posto BIGINT NOT NULL,
	id_combustivel INT(3) NOT NULL,
	preco DECIMAL(4,2) NOT NULL,
	dh_data datetime,
	version BIGINT,
	FOREIGN KEY (id_posto) REFERENCES postos(id),
	FOREIGN KEY (id_combustivel) REFERENCES combustiveis(id)
) Engine = INNODB;

CREATE TRIGGER versioning_postos_precos_ins BEFORE INSERT ON postos_precos FOR EACH ROW SET NEW.version = (select now() + 0);
CREATE TRIGGER versioning_postos_precos_upd BEFORE UPDATE ON postos_precos FOR EACH ROW SET NEW.version = (select now() + 0);

-- VIEW PARA VISUALIZAÇÃO RESUMIDA
CREATE OR REPLACE VIEW vw_precos AS
SELECT id_posto, postos.nome as nome_posto, 
	   bandeiras.id as id_bandeira, IFNULL(bandeiras.nome, 'Bandeira branca') as nome_bandeira, 
	   cidades.nome as cidade, postos.endereco, 
	   combustiveis.descricao as combustivel,
	   postos_precos.preco, dh_data as data
FROM postos_precos
JOIN postos ON (postos.id = postos_precos.id_posto)
JOIN cidades ON (cidades.id = postos.id_cidade)
JOIN combustiveis ON (combustiveis.id = postos_precos.id_combustivel)
LEFT JOIN bandeiras ON (bandeiras.id = postos.id_bandeira)
WHERE dh_data = (select max(dh_data) from postos_precos pp where pp.id_posto = postos_precos.id_posto and pp.id_combustivel = postos_precos.id_combustivel)
order by postos_precos.id_posto;

CREATE OR REPLACE VIEW vw_postos AS
SELECT postos.id, postos.nome, 
	   IF(bandeiras.id is null, 'Bandeira branca', concat(bandeiras.id, ' - ', bandeiras.nome)) as bandeira, 
	   postos.endereco, postos.numero
FROM postos 
LEFT JOIN bandeiras ON (bandeiras.id = postos.id_bandeira) order by postos.id;
