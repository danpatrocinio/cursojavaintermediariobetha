insert into bandeiras (id, nome) values 
(null, "BR"),
(null, "American oil"),
(null, "Shell"),
(null, "Texaco"),
(null, "Ipiranga");

insert into combustiveis(id, descricao, unidade_medida) values 
(null, 'Gasolina comum','LT'),
(null, 'Gasolina aditivada','LT'),
(null, 'Gasolina premium','LT'),
(null, 'Gasolina podium','LT'),
(null, 'Etanol','LT'),
(null, 'GNV','M3'),
(null, 'Óleo diesel','LT');

insert into postos (id, id_bandeira, id_cidade, nome, endereco, complemento, numero) values
(null, 1, 420460, "Posto 1 de Criciuma", "Rua de Criciuma", "detalhe endereco 1", "100"),
(null, 1, 420930, "Posto 2 de Lages", "Rua de Lages", "detalhe endereco 2", "200"),
(null, 1, 420425, "Posto 3 de Cocal do Sul", "Rua de Cocal do Sul", "detalhe endereco 3", "300"),
(null, 2, 420455, "Posto 4 de Correia Pinto", "Rua de Correia Pinto", "detalhe endereco 4", "400"),
(null, 2, 420700, "Posto 5 de Içara", "Rua de Içara", "detalhe endereco 5", "500"),
(null, 2, 421650, "Posto 6 de São Joaquim", "Rua de São Joaquim", "detalhe endereco 6", "600"),
(null, 3, 430910, "Posto 7 de Gramado", "Rua de Gramado", "detalhe endereco 7", "700"),
(null, 3, 420540, "Posto 8 de Florianópolis", "Rua de Florianópolis", "detalhe endereco 8", "800"),
(null, 3, 420545, "Posto 9 de Forquilhinha", "Rua de Forquilhinha", "detalhe endereco 9", "900"),
(null, null, 420460, "Posto de bandeira branca", "Rua 2 de Criciúma", "detalhe endereco 10", "999");

insert into postos_precos (id, id_posto, id_combustivel, preco, dh_data) values
(null, 1, 1, 3.31, now()),(null, 1, 5, 2.90, now()),
(null, 2, 1, 3.34, now()),(null, 2, 5, 2.87, now()),
(null, 3, 1, 3.41, now()),(null, 3, 4, 3.81, now()),
(null, 4, 1, 3.38, now()),(null, 4, 2, 3.38, now()),
(null, 5, 1, 3.31, now()),(null, 5, 5, 2.97, now()),
(null, 6, 1, 3.55, now()),(null, 6, 3, 3.95, now()),
(null, 7, 1, 3.54, now()),(null, 7, 6, 1.74, now()),
(null, 8, 1, 3.33, now()),(null, 8, 6, 1.53, now()),
(null, 9, 1, 3.40, now()),(null, 9, 7, 3.25, now());
SELECT SLEEP(7);
insert into postos_precos (id, id_posto, id_combustivel, preco, dh_data) values (null, 10, 1, 3.27, now());
insert into postos_precos (id, id_posto, id_combustivel, preco, dh_data) values
(null, 1, 1, 3.32, now()),
(null, 2, 1, 3.35, now()),
(null, 3, 1, 3.42, now()),
(null, 4, 1, 3.39, now()),
(null, 5, 1, 3.32, now()),
(null, 6, 1, 3.56, now()),
(null, 7, 1, 3.55, now()),
(null, 8, 1, 3.34, now()),
(null, 9, 1, 3.41, now());
