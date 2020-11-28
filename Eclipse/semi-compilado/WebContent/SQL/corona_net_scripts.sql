CREATE TABLE IF NOT EXISTS `pacientes` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(15) NOT NULL,
  `data_de_nascimento` DATE NOT NULL,
  `endereco` VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS `prontuarios` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `data` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `estado_do_paciente` VARCHAR(255),
  `diagnostico` VARCHAR(100),
  `teste_covid` VARCHAR(100),
  `doenca_respiratoria` VARCHAR(100),
  `batimento_cardiaco` VARCHAR(100),
  `hipertensao` VARCHAR(100),
  `oximetria` VARCHAR(100),
  `radiometria_torax_normal` BOOLEAN,
  `tomografia_torax_normal` BOOLEAN,
  `ventilacao_mecanica` BOOLEAN,
  `diabetes` BOOLEAN,
  `obesidade` BOOLEAN,
  `ativo` BOOLEAN NOT NULL DEFAULT TRUE,
  `hospital_id` INT NOT NULL,
  `hospital_destino_id` INT,
  `paciente_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `exames` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100),
  `descricao` VARCHAR(100),
  `resultado` VARCHAR(255),
  `prontuario_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `municipios` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS `hospitais` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(10) NOT NULL,
  `endereco` VARCHAR(255) NOT NULL,
  `estado` VARCHAR(100) NOT NULL,
  `municipio_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `funcionarios` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL,
  `especializacao` VARCHAR(100) NOT NULL,
  `cargo` VARCHAR(100) NOT NULL,
  `hospital_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `enfermeiros` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `COFEN` VARCHAR(20) NOT NULL,
  `funcionario_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `medicos` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `CRM` VARCHAR(20) NOT NULL,
  `funcionario_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `leitos` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ocupado` BOOLEAN,
  `medico_id` INT,
  `enfermeiro_id` INT,
  `paciente_id` INT,
  `hospital_id` INT NOT NULL
 );

CREATE TABLE IF NOT EXISTS `fila_de_pacientes` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ordem` INT NOT NULL,
  `data` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `prioridade` INT NOT NULL,
  `paciente_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `relatorios` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `data` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `tipo` VARCHAR(100) NOT NULL,
  `num_casos` INT NOT NULL,
  `hospital_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `municipais` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_municipio` VARCHAR(255) NOT NULL,
  `num_hospitais` INT NOT NULL,
  `relatorio_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `hospitalares` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_hospital` VARCHAR(255) NOT NULL,
  `relatorio_id` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `estaduais` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_estado` VARCHAR(255) NOT NULL,
  `num_hospitais` INT NOT NULL,
  `num_municipios` INT NOT NULL,
  `relatorio_id` INT NOT NULL
);

ALTER TABLE `prontuarios` ADD FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`);

ALTER TABLE `prontuarios` ADD FOREIGN KEY (`hospital_id`) REFERENCES `hospitais` (`id`);

ALTER TABLE `prontuarios` ADD FOREIGN KEY (`hospital_destino_id`) REFERENCES `hospitais` (`id`);

ALTER TABLE `exames` ADD FOREIGN KEY (`prontuario_id`) REFERENCES `prontuarios` (`id`);

ALTER TABLE `hospitais` ADD FOREIGN KEY (`municipio_id`) REFERENCES `municipios` (`id`);

ALTER TABLE `funcionarios` ADD FOREIGN KEY (`hospital_id`) REFERENCES `hospitais` (`id`);

ALTER TABLE `enfermeiros` ADD FOREIGN KEY (`funcionario_id`) REFERENCES `funcionarios` (`id`);

ALTER TABLE `medicos` ADD FOREIGN KEY (`funcionario_id`) REFERENCES `funcionarios` (`id`);

ALTER TABLE `leitos` ADD FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`);

ALTER TABLE `leitos` ADD FOREIGN KEY (`hospital_id`) REFERENCES `hospitais` (`id`);

ALTER TABLE `leitos` ADD FOREIGN KEY (`medico_id`) REFERENCES `medicos` (`id`);

ALTER TABLE `leitos` ADD FOREIGN KEY (`enfermeiro_id`) REFERENCES `enfermeiros` (`id`);

ALTER TABLE `fila_de_pacientes` ADD FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`);

ALTER TABLE `relatorios` ADD FOREIGN KEY (`hospital_id`) REFERENCES `hospitais` (`id`);

ALTER TABLE `municipais` ADD FOREIGN KEY (`relatorio_id`) REFERENCES `relatorios` (`id`);

ALTER TABLE `hospitalares` ADD FOREIGN KEY (`relatorio_id`) REFERENCES `relatorios` (`id`);

ALTER TABLE `estaduais` ADD FOREIGN KEY (`relatorio_id`) REFERENCES `relatorios` (`id`);

INSERT INTO `municipios` (`id`, `nome`)
VALUES 
(1, 'Jandira'),
(2, 'Barueri'),
(3, 'Osasco'),
(4, 'São Paulo');

INSERT INTO `hospitais` (`id`, `nome`, `telefone`, `endereco`, `estado`, `municipio_id`)
VALUES
(1, 'Hospital Municipal de Jandira', '1234-1234', 'Rua do Pedro', 'SP', 1),
(2, 'Hospital Santa Maria', '1234-1234', 'Rua da Maria', 'SP', 2),
(3, 'Hospital Feliz', '1234-1234', 'Rua do Jamelao', 'SP', 2),
(4, 'Hospital Muito Bom', '1234-1234', 'Rua do Cardoso', 'SP', 3),
(5, 'Hospital Mais ou Menos', '1234-1234', 'Rua do Samba', 'SP', 3),
(6, 'Hospital Caro', '1234-1234', 'Rua da Carambola', 'SP', 4),
(7, 'Hospital Barato', '1234-1234', 'Rua do Melao', 'SP', 4),
(8, 'Hospital Santa Casa', '1234-1234', 'Rua do Japa', 'SP', 4),
(9, 'Hospital Alberto Aleonor', '1234-1234', 'Rua da Senhora', 'SP', 4);

INSERT INTO `leitos` (`ocupado`, `hospital_id`)
VALUES
(1, 1),
(0, 1),
(0, 1),
(0, 1),
(1, 1),
(1, 2),
(1, 2),
(1, 2),
(1, 2),
(1, 2),
(0, 3),
(0, 3),
(0, 3),
(0, 4),
(1, 4),
(0, 5),
(1, 5),
(0, 6),
(1, 6),
(0, 7),
(1, 7),
(0, 8),
(1, 8),
(0, 9),
(1, 9);

INSERT INTO pacientes (id, nome, cpf, data_de_nascimento, endereco)
VALUES
(1, 'Xuxa Meneguel', '1234567890', '1980-01-01', 'Rua dos baixinhos'),
(2, 'Felipe Smith', '1234567890', '1980-01-01', 'Rua Americana'),
(3, 'Boulos', '1234567890', '1980-01-01', 'Sem teto'),
(4, 'Covas', '1234567890', '1980-01-01', 'Jaburu'),
(5, 'Erundina', '1234567890', '1980-01-01', 'Rua 1'),
(6, 'Arthur do Val', '1234567890', '1980-01-01', 'Youtube');

INSERT INTO prontuarios (
		estado_do_paciente,
		diagnostico,
		teste_covid,
		doenca_respiratoria,
		batimento_cardiaco,
		hipertensao,
		oximetria,
		radiometria_torax_normal,
		tomografia_torax_normal,
		ventilacao_mecanica,
		diabetes,
		obesidade,
		ativo,
		hospital_id,
		hospital_destino_id,
		paciente_id
) 
VALUES  
('bom', 'bom', 'positivo', 'nao', 'ruim', 'nao', 'boa', 1, 1, 1, 1, 1, 1, 1, 2, 1),
('bom', 'ruim', 'negativo', 'sim', 'bom', 'sim', 'ruim', 1, 1, 1, 1, 1, 1, 3, 4, 2),
('ruim', 'bom', 'positivo', 'nao', 'bom', 'nao', 'boa', 1, 1, 1, 1, 1, 1, 5, 6, 3),
('ruim', 'ruim', 'negativo', 'nao', 'ruim', 'nao', 'boa', 1, 1, 1, 1, 1, 7, 8, 2, 4),
('bom', 'bom', 'positivo', 'sim', 'bom', 'sim', 'ruim', 1, 1, 1, 1, 1, 1, 1, 2, 5),
('bom', 'ruim', 'negativo', 'nao', 'bom', 'nao', 'boa', 1, 1, 1, 1, 1, 1, 1, 2, 6);
