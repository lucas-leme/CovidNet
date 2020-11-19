CREATE TABLE IF NOT EXISTS `prontuarios` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_exame` VARCHAR(100),
  `descricao_exame` VARCHAR(100),
  `data` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `resultado` VARCHAR(255),
  `paciente_id` INT
);

CREATE TABLE IF NOT EXISTS `pacientes` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100),
  `data_de_entrada` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `cpf` VARCHAR(15),
  `data_de_nascimento` DATE,
  `funcionario_id` INT
);

CREATE TABLE IF NOT EXISTS `leitos2` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `ocupa` boolean,
  `medico_id` INT,
  `enfermeiro_id` INT,
  `paciente_id` INT,
  `hospital_id` INT
  );
 
CREATE TABLE IF NOT EXISTS `funcionarios` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100),
  `data_de_nascimento` DATE,
  `especializacao` VARCHAR(100),
  `ala` VARCHAR(100),
  `cargo` VARCHAR(100),
  `hospital_id` INT
);

CREATE TABLE IF NOT EXISTS `enfermeiros` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `COFEN` VARCHAR(20),
  `funcionario_id` INT
);


CREATE TABLE IF NOT EXISTS `medicos` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `CRM` VARCHAR(20),
  `funcionario_id` INT
);

CREATE TABLE IF NOT EXISTS `hospitais` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100),
  `telefone` VARCHAR(10),
  `endereco` VARCHAR(255),
  `estado` VARCHAR(100),
  `municipio` VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS `relatorios` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `data` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `tipo` VARCHAR(100),
  `num_casos` INT,
  `hospital_id` INT
);

CREATE TABLE IF NOT EXISTS `municipais` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_municipio` VARCHAR(255),
  `num_hospitais` INT,
  `relatorio_id` INT
);

CREATE TABLE IF NOT EXISTS `hospitalares` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_hospital` VARCHAR(255),
  `relatorio_id` INT
);

CREATE TABLE IF NOT EXISTS `estaduais` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome_estado` VARCHAR(255),
  `num_hospitais` INT,
  `num_municipios` INT,
  `relatorio_id` INT
);

ALTER TABLE `funcionarios` ADD FOREIGN KEY (`hospital_id`) REFERENCES `hospitais` (`id`);

ALTER TABLE `pacientes` ADD FOREIGN KEY (`funcionario_id`) REFERENCES `funcionarios` (`id`);

ALTER TABLE `enfermeiros` ADD FOREIGN KEY (`funcionario_id`) REFERENCES `funcionarios` (`id`);

ALTER TABLE `medicos` ADD FOREIGN KEY (`funcionario_id`) REFERENCES `funcionarios` (`id`);

ALTER TABLE `relatorios` ADD FOREIGN KEY (`hospital_id`) REFERENCES `hospitais` (`id`);

ALTER TABLE `municipais` ADD FOREIGN KEY (`relatorio_id`) REFERENCES `relatorios` (`id`);

ALTER TABLE `hospitalares` ADD FOREIGN KEY (`relatorio_id`) REFERENCES `relatorios` (`id`);

ALTER TABLE `estaduais` ADD FOREIGN KEY (`relatorio_id`) REFERENCES `relatorios` (`id`);

ALTER TABLE `leitos2` ADD FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`);

ALTER TABLE `leitos2` ADD FOREIGN KEY (`hospital_id`) REFERENCES `hospitais` (`id`);

ALTER TABLE `leitos2` ADD FOREIGN KEY (`medico_id`) REFERENCES `medicos` (`id`);

ALTER TABLE `leitos2` ADD FOREIGN KEY (`enfermeiro_id`) REFERENCES `enfermeiros` (`id`);

ALTER TABLE `prontuarios` ADD FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`);

INSERT INTO `hospitais` (`id`, `nome`, `telefone`, `endereco`, `estado`, `municipio`) VALUES
(1, 'Incor', 1234-4567, 'Rua Bires', 'SP','Jandira'),
(2, 'Hospital das Clinicas', 1234-4567, 'Rua do Padre', 'SP','Sao Paulo');

INSERT INTO `funcionarios` (`id`, `nome`, `data_de_nascimento`, `especializacao`, `ala`, `cargo`, `hospital_id`) VALUES
(1, 'Felipe Smith', '1990-02-10', 'oftalmologista', 'emergencia', 'responsavel', 1),
(2, 'Pablo Escobar', '1970-03-21', 'cardiologista', 'emergencia', 'chefe', 1),
(3, 'Xuxa Meneguel', '1990-02-10', 'rainha dos baixinhos', 'emergencia', 'responsavel', 2),
(4, 'Dilma Rousseff', '1990-02-10', 'presidenta', 'emergencia', 'chefe', 1);

INSERT INTO `medicos` (`id`, `CRM`, `funcionario_id`) VALUES
(1, '1234', 1),
(2, '2345', 2);

INSERT INTO `enfermeiros` (`id`, `COFEN`, `funcionario_id`) VALUES
(1, '1234', 3),
(2, '2345', 4);

INSERT INTO `pacientes` (`id`, `nome`, `cpf`, `data_de_nascimento`, `funcionario_id`) VALUES
(1, 'Pompeo da Silva', '111.111.111-11', '1990-11-07', 1),
(2, 'Salamandra', '222.222.222-22', '1985-02-26', 1);

INSERT INTO `prontuarios` (`id`, `nome_exame`, `descricao_exame`, `resultado`, `paciente_id`) VALUES
(1, 'Exame de sangue', 'tira o sangue', 'ta bom', 1),
(2, 'Exame de fezes', 'cagar no pote', 'ta bom', 1);

INSERT INTO `leitos2` (`id`, `ocupa`, `medico_id`, `enfermeiro_id`, `paciente_id`, `hospital_id`) VALUES
(1, 1, 1, 1, 1, 1),
(2, 1, 2, 2, 2, 2);

INSERT INTO `relatorios` (`id`, `tipo`, `num_casos`, `hospital_id`) VALUES
(1, 'rela', 120, 1),
(2, 'rela2', 230, 1),
(3, 'Sei la', 300, 1);

INSERT INTO `municipais` (`id`, `nome_municipio`, `num_hospitais`, `relatorio_id`) VALUES
(1, 'Jandira', 1, 1);

INSERT INTO `hospitalares` (`id`, `nome_hospital`, `relatorio_id`) VALUES
(1, 'Hospital de Jandira', 2);

INSERT INTO `estaduais` (`id`, `nome_estado`, `num_hospitais`, `num_municipios`, `relatorio_id`) VALUES
(1, 'SP', 20, 4, 3);

