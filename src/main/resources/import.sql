insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into forma_pagamento (id, descricao) values (1, 'Dinheiro')
insert into forma_pagamento (id, descricao) values (2, 'Credito')
insert into forma_pagamento (id, descricao) values (3, 'Debito')
insert into forma_pagamento (id, descricao) values (4, 'Pix')

insert into estado (id, nome) values (1, 'Amazonas');
insert into estado (id, nome) values (2, 'Sao Paulo');
insert into estado (id, nome) values (3, 'Rio de Janeiro');

insert into cidade (nome, estado_id) values ('Manaus', 1);
insert into cidade (nome, estado_id) values ('Sao Paulo', 2);
insert into cidade (nome, estado_id) values ('Rio de Janeiro', 3);

--insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
--insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
--insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);


--insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);