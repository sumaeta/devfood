insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into forma_pagamento (id, descricao) values (1, 'Dinheiro')
insert into forma_pagamento (id, descricao) values (2, 'Credito')
insert into forma_pagamento (id, descricao) values (3, 'Debito')
insert into forma_pagamento (id, descricao) values (4, 'Pix')

INSERT into restaurante(nome, taxa_frete, cozinha_id, forma_pagamento_id) values ('Gourmet', 10, 1, 1);
INSERT into restaurante(nome, taxa_frete, cozinha_id, forma_pagamento_id) values ('Thailandesa', 10, 1, 1);
INSERT into restaurante(nome, taxa_frete, cozinha_id, forma_pagamento_id) values ('Komida', 3, 2, 1);

insert into estado (id, nome) values (1, 'Amazonas');
insert into estado (id, nome) values (2, 'Sao Paulo');
insert into estado (id, nome) values (3, 'Rio de Janeiro');

insert into cidade (nome, estado_id) values ('Manaus', 1);
insert into cidade (nome, estado_id) values ('Sao Paulo', 2);
insert into cidade (nome, estado_id) values ('Rio de Janeiro', 3);