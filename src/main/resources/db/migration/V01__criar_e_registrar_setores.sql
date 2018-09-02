CREATE TABLE setor(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO setor (descricao) values ('DESENVOLVIMENTO');
INSERT INTO setor (descricao) values ('REQUISITOS');
INSERT INTO setor (descricao) values ('SUSTENTACAO');
INSERT INTO setor (descricao) values ('RH');
INSERT INTO setor (descricao) values ('SUPORTE');