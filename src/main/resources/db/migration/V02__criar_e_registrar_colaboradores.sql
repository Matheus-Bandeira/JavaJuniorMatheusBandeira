CREATE TABLE colaborador (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cpf VARCHAR(15) NOT NULL UNIQUE,
	nome VARCHAR(80),
	telefone VARCHAR(20),
	email VARCHAR(80) NOT NULL UNIQUE,
	setor BIGINT(20),
	foreign key(setor) references setor(id)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

INSERT INTO colaborador(cpf,nome,telefone,email,setor) values ('11111111111','Davi','22182422','Davi@gmail.com',5);
INSERT INTO colaborador(cpf,nome,telefone,email,setor) values ('22222222222','Maria','32786428','maria@gmail.com',3);
INSERT INTO colaborador(cpf,nome,telefone,email,setor) values ('33333333333','João','997907619','jojo@gmail.com',3);
INSERT INTO colaborador(cpf,nome,telefone,email,setor) values ('44444444444','Bernardo','31786543','ber@gmail.com',1);