#Criando a tabela estado
create table estado(
	id bigint not null auto_increment,
    nome varchar(80) not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

insert into estado (nome) select distinct nome_estado from cidade;

#Adiocionando a referencia para a tabela estado como foreign key
alter table cidade add column estado_id bigint not null;

#Atualizando a referencia para o estado id
update cidade c set c.estado_id = (select e.id from estado e where e.nome = c.nome_estado);

#Adicionando um relacionamento entre tabelas
alter table cidade add constraint fk_cidade_estado foreign key (estado_id) references estado (id);

#Apagando um campo da tabela cidade
alter table cidade drop column nome_estado;

#Alterando o nome do campo na tabela cidade
alter table cidade change nome_cidade nome varchar(80) not null;