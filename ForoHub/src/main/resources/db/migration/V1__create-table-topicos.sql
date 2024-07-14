create table topicos(

id bigint not null auto_increment,
titulo varchar(100) not null,
mensaje varchar(700) not null unique,
autor varchar(100) not null unique,
curso varchar(100) not null,
fechaDeCreacion varchar(100) not null,

primary key(id)
);