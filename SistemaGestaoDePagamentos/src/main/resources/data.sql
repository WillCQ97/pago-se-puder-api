-- Este arquivo será carregado automaticamente pelo SpringBoot quando a aplicação for iniciada. --
-- O banco já deve estar configurado --

DROP TABLE IF EXISTS funcionarios;
DROP TABLE IF EXISTS pagamentos;
DROP TABLE IF EXISTS detalhes;

CREATE TABLE funcionarios (
	idFuncionario INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(250) NOT NULL,
	cargo VARCHAR(250) NOT NULL,
	idade INT,
	qtd_faltas INT
);

INSERT INTO funcionarios (nome, cargo, idade, qtd_faltas) VALUES
	('Willian Conceição', 'programador jr', 23, 5),
	('Maria Regina', 'CEO', 58, 2),
	('Isaias Fideles', 'Consultor', 68, 3); 

CREATE TABLE pagamentos (
	idPagamento INT AUTO_INCREMENT PRIMARY KEY,
	dataVencimento DATE NOT NULL,
	dataAprovacao DATE,
	descricao VARCHAR(500) NOT NULL,
	valor DOUBLE NOT NULL,
	idFuncSolicitante INT NOT NULL,
	idFuncAprovador INT,
	CONSTRAINT idFuncSolicitante FOREIGN KEY (idFuncSolicitante) REFERENCES funcionarios(idFuncionario),
	CONSTRAINT idFuncAprovador FOREIGN KEY (idFuncAprovador) REFERENCES funcionarios(idFuncionario)
);
	
INSERT INTO pagamentos (dataVencimento, dataAprovacao, descricao, valor, idFuncSolicitante, idFuncAprovador) VALUES
	('2020-05-25', '2020-05-19', 'Pagamento licença de software', '25.56', 1, 2);

INSERT INTO pagamentos (dataVencimento, descricao, valor, idFuncSolicitante) VALUES
	('2020-05-30', 'Viajem técnica para configuração do servidor.', '50.00', 1),
	('2020-06-30', 'Implementação de software no cliente.', '100.00', 1);
	
CREATE TABLE detalhes (
	idDetalhe INT AUTO_INCREMENT PRIMARY KEY,
	idPagamento INT NOT NULL,
	data DATE NOT NULL,
	descricao VARCHAR(250) NOT NULL,
	usuario VARCHAR(250) NOT NULL,
	CONSTRAINT idPagamento FOREIGN KEY (idPagamento) REFERENCES pagamentos(idPagamento)
);

INSERT INTO detalhes (idPagamento, data, descricao, usuario) VALUES
(1, '2020-05-19', 'Pagamento aprovado.', 'Maria'),
(2, '2020-05-19', 'Pagamento em análise', 'Maria');
