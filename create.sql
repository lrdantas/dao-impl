
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
