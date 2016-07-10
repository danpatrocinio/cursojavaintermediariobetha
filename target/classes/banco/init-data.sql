insert into bandeiras (id, nome, version) values 
(1, "Bandeira 1", (select now() + 0)),
(2, "Bandeira 2", (select now() + 0)),
(3, "Bandeira 3", (select now() + 0));

insert into postos (id, id_bandeira, codigo_cidade, nome, endereco, detalhe_endereco, numero, version) values
(1, 1, 420460, "Posto 1 de Criciuma", "Rua de Criciuma", "detalhe endereco 1", "100", (select now() + 0)),
(2, 1, 420930, "Posto 2 de Lages", "Rua de Lages", "detalhe endereco 2", "200", (select now() + 0)),
(3, 1, 420425, "Posto 3 de Cocal do Sul", "Rua de Cocal do Sul", "detalhe endereco 3", "300", (select now() + 0)),
(4, 2, 420455, "Posto 4 de Correia Pinto", "Rua de Correia Pinto", "detalhe endereco 4", "400", (select now() + 0)),
(5, 2, 420700, "Posto 5 de Içara", "Rua de Içara", "detalhe endereco 5", "500", (select now() + 0)),
(6, 2, 421650, "Posto 6 de São Joaquim", "Rua de São Joaquim", "detalhe endereco 6", "600", (select now() + 0)),
(7, 3, 430910, "Posto 7 de Gramado", "Rua de Gramado", "detalhe endereco 7", "700", (select now() + 0)),
(8, 3, 420540, "Posto 8 de Florianópolis", "Rua de Florianópolis", "detalhe endereco 8", "800", (select now() + 0)),
(9, 3, 420545, "Posto 9 de Forquilhinha", "Rua de Forquilhinha", "detalhe endereco 9", "900", (select now() + 0));

insert into precos_postos (id, id_posto, preco, dh_data, version) values
(1, 1, 3.31, now(), (select now() + 0)),
(2, 2, 3.34, now(), (select now() + 0)),
(3, 3, 3.41, now(), (select now() + 0)),
(4, 4, 3.38, now(), (select now() + 0)),
(5, 5, 3.31, now(), (select now() + 0)),
(6, 6, 3.55, now(), (select now() + 0)),
(7, 7, 3.54, now(), (select now() + 0)),
(8, 8, 3.33, now(), (select now() + 0)),
(9, 9, 3.40, now(), (select now() + 0));
SELECT SLEEP(7);
insert into precos_postos (id, id_posto, preco, dh_data, version) values
(10, 1, 3.32, now(), (select now() + 0)),
(11, 2, 3.35, now(), (select now() + 0)),
(12, 3, 3.42, now(), (select now() + 0)),
(13, 4, 3.39, now(), (select now() + 0)),
(14, 5, 3.32, now(), (select now() + 0)),
(15, 6, 3.56, now(), (select now() + 0)),
(16, 7, 3.55, now(), (select now() + 0)),
(17, 8, 3.34, now(), (select now() + 0)),
(18, 9, 3.41, now(), (select now() + 0));
