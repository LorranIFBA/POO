
#Cria a base de dados:
drop database if exists funcionarios;
create database funcionarios;
use funcionarios;

#Cria a tabela funcionario_adm
drop table if exists funcionario_adm;
create table funcionario_adm(
	id_funcionario int primary key,
    data_comeco date,
    data_fim date,
    salario int
);

#Cria a tabela funcionario_pes
drop table if exists funcionario_pes;
create table funcionario_pes(
	id_funcionario int,
    nome varchar(50),
    data_nasc date,
    documento varchar(30),
    sexo varchar(20),
    foreign key(id_funcionario) references funcionario_adm(id_funcionario)
);

#Cria a tabela funcionario_end
drop table if exists funcionario_end;
create table funcionario_end(
	id_funcionario int,
	uf char(2),
    cidade varchar(20),
    cep char(8),
    rua varchar(20),
    inf_add varchar(50) default null,
    foreign key(id_funcionario) references funcionario_adm(id_funcionario)
);
/*

seleciona tudo das tabelas

select * from funcionario_adm;
select * from funcionario_end;
select * from funcionario_pes;
*/

