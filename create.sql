
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!50503 SET default_storage_engine=InnoDB */;

CREATE DATABASE IF NOT EXISTS `dao_testing`
/*!40100 CHARACTER SET utf8mb4 */
/*!40100 COLLATE utf8mb4_general_ci */
/*!40100 DEFAULT CHARACTER SET utf8mb4 */
/*!40100 DEFAULT COLLATE utf8mb4_general_ci */;

USE `dao_testing`;


CREATE TABLE IF NOT EXISTS `usuario` (

  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) DEFAULT NULL,

  PRIMARY KEY (`id`)

)

;


CREATE TABLE IF NOT EXISTS `endereco` (

  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

  `logradouro` VARCHAR(100) NOT NULL,
  `numero` VARCHAR(100) NOT NULL,
  `bairro` VARCHAR(100) NOT NULL,

  `cidade` VARCHAR(100) NOT NULL,
  `uf` CHAR(2) NOT NULL,

  PRIMARY KEY (`id`)

)

;

CREATE TABLE IF NOT EXISTS `usuario_endereco` (

  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,

  `usuario_id` INT UNSIGNED NOT NULL,
  `endereco_id` INT UNSIGNED NOT NULL,

  PRIMARY KEY (`id`),

  CONSTRAINT `fk_endereco_usuario__usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,

  CONSTRAINT `fk_endereco_usuario__endereco`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `endereco` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT

)

;
