
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `dao_testing`
/*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `dao_testing`;

CREATE TABLE IF NOT EXISTS `usuario` (

  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,

  PRIMARY KEY (`id`)

)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
;
