insert into bandeiras (id, nome) values 
(1, "Bandeira 1"),
(2, "Bandeira 2"),
(3, "Bandeira 3");

insert into postos (id, id_bandeira, codigo_cidade, nome, endereco, detalhe_endereco, numero) values
(1, 1, 420460, "Posto 1 de Criciuma", "Rua de Criciuma", "detalhe endereco 1", "100"),
(2, 1, 420930, "Posto 2 de Lages", "Rua de Lages", "detalhe endereco 2", "200"),
(3, 1, 420425, "Posto 3 de Cocal do Sul", "Rua de Cocal do Sul", "detalhe endereco 3", "300"),
(4, 2, 420455, "Posto 4 de Correia Pinto", "Rua de Correia Pinto", "detalhe endereco 4", "400"),
(5, 2, 420700, "Posto 5 de Içara", "Rua de Içara", "detalhe endereco 5", "500"),
(6, 2, 421650, "Posto 6 de São Joaquim", "Rua de São Joaquim", "detalhe endereco 6", "600"),
(7, 3, 430910, "Posto 7 de Gramado", "Rua de Gramado", "detalhe endereco 7", "700"),
(8, 3, 420540, "Posto 8 de Florianópolis", "Rua de Florianópolis", "detalhe endereco 8", "800"),
(9, 3, 420545, "Posto 9 de Forquilhinha", "Rua de Forquilhinha", "detalhe endereco 9", "900");

insert into precos_postos (id, id_posto, preco, dh_data) values
(1, 1, 3.31, now()),
(2, 2, 3.34, now()),
(3, 3, 3.41, now()),
(4, 4, 3.38, now()),
(5, 5, 3.31, now()),
(6, 6, 3.55, now()),
(7, 7, 3.54, now()),
(8, 8, 3.33, now()),
(9, 9, 3.40, now());
SELECT SLEEP(7);
insert into precos_postos (id, id_posto, preco, dh_data) values
(10, 1, 3.32, now()),
(11, 2, 3.35, now()),
(12, 3, 3.42, now()),
(13, 4, 3.39, now()),
(14, 5, 3.32, now()),
(15, 6, 3.56, now()),
(16, 7, 3.55, now()),
(17, 8, 3.34, now()),
(18, 9, 3.41, now());
