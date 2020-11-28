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

CREATE TABLE IF NOT EXISTS `hospitais` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(10) NOT NULL,
  `endereco` VARCHAR(255) NOT NULL,
  `estado` VARCHAR(100) NOT NULL,
  `municipio` VARCHAR(100) NOT NULL
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

