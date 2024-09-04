CREATE DATABASE  IF NOT EXISTS `cuentas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `cuentas`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cuentas
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `codigo_moneda`
--

DROP TABLE IF EXISTS `codigo_moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `codigo_moneda` (
  `cod_moneda` int(11) NOT NULL,
  `pais` varchar(45) NOT NULL,
  `simbolo` varchar(45) NOT NULL,
  PRIMARY KEY (`cod_moneda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `codigo_moneda` (`cod_moneda`, `pais`, `simbolo`) VALUES
(1, 'Estados Unidos', 'USD'),
(2, 'Eurozona', 'EUR'),
(3, 'Japón', 'JPY'),
(4, 'Reino Unido', 'GBP'),
(5, 'Australia', 'AUD'),
(6, 'Argentina', 'ARS');


/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codigo_moneda`
--

LOCK TABLES `codigo_moneda` WRITE;
/*!40000 ALTER TABLE `codigo_moneda` DISABLE KEYS */;
/*!40000 ALTER TABLE `codigo_moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `numcue` varchar(44) NOT NULL,
  `persnum` int(11) NOT NULL,
  `divisa` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `saldo` decimal(10,0) NOT NULL,
  PRIMARY KEY (`numcue`),
  KEY `fk_codigomoneda` (`divisa`),
  KEY `fk_estado` (`estado`),
  CONSTRAINT `fk_codigomoneda` FOREIGN KEY (`divisa`) REFERENCES `codigo_moneda` (`cod_moneda`),
  CONSTRAINT `fk_estado` FOREIGN KEY (`estado`) REFERENCES `estado_cuenta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_cuenta`
--

DROP TABLE IF EXISTS `estado_cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_cuenta` (
  `id` int(11) NOT NULL,
  `detalle` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `estado_cuenta` (`id`, `detalle`) VALUES
(1, 'Activa'),
(2, 'Inactiva'),
(3, 'Suspendida'),
(4, 'Cancelada'),
(5, 'Bloqueada');

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_cuenta`
--

INSERT INTO `cuentas` (`numcue`, `persnum`, `divisa`, `estado`, `saldo`) VALUES
('1234567890', 1, 1, 1, 10000),
('9876543210', 2, 2, 1, 5000),
('4567890123', 3, 3, 1, 8000),
('7890123456', 4, 4, 1, 3000),
('6543210987', 5, 5, 1, 12000);

LOCK TABLES `estado_cuenta` WRITE;
/*!40000 ALTER TABLE `estado_cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_cuenta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-12 13:13:00
CREATE DATABASE  IF NOT EXISTS `personas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `personas`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: personas
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `domicilio`
--










DROP TABLE IF EXISTS `domicilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `domicilio` (
  `iddomicilio` int(11) NOT NULL AUTO_INCREMENT,
  `persnum` int(11) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `provincia` varchar(45) DEFAULT NULL,
  `localidad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iddomicilio`,`persnum`),
  KEY `fk_persona` (`persnum`),
  CONSTRAINT `fk_persona` FOREIGN KEY (`persnum`) REFERENCES `usuarios` (`persnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domicilio`
--

LOCK TABLES `domicilio` WRITE;
/*!40000 ALTER TABLE `domicilio` DISABLE KEYS */;
/*!40000 ALTER TABLE `domicilio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_usuario`
--

DROP TABLE IF EXISTS `estado_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_usuario` (
  `idestado_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idestado_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_usuario`
--

LOCK TABLES `estado_usuario` WRITE;
/*!40000 ALTER TABLE `estado_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_usuario` (
  `idtipo_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_usuario`
--

LOCK TABLES `tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `persnum` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL,
  `tipo` int(11) NOT NULL,
  PRIMARY KEY (`persnum`),
  KEY `fk_estado_usuario` (`estado`),
  KEY `fk_tipo_usuario` (`tipo`),
  CONSTRAINT `fk_estado_usuario` FOREIGN KEY (`estado`) REFERENCES `estado_usuario` (`idestado_usuario`),
  CONSTRAINT `fk_tipo_usuario` FOREIGN KEY (`tipo`) REFERENCES `tipo_usuario` (`idtipo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `estado_usuario` (`idestado_usuario`, `descripcion`) VALUES
(1, 'Activo'),
(2, 'Inactivo'),
(3, 'Suspendido'),
(4, 'Bloqueado'),
(5, 'Cancelado');

INSERT INTO `tipo_usuario` (`idtipo_usuario`, `descripcion`) VALUES
(1, 'Cliente'),
(2, 'Empleado'),
(3, 'Administrador'),
(4, 'Proveedor'),
(5, 'Invitado');


INSERT INTO `usuarios` (`persnum`, `nombre`, `apellido`, `dni`, `estado`, `tipo`) VALUES
(1, 'John', 'Doe', '12345678', 1, 1),
(2, 'Marie', 'Curie', '23456789', 1, 2),
(3, 'Ichiro', 'Suzuki', '34567890', 1, 3),
(4, 'Winston', 'Churchill', '45678901', 1, 4),
(5, 'Steve', 'Irwin', '56789012', 1, 5);


INSERT INTO `domicilio` (`iddomicilio`, `persnum`, `calle`, `numero`, `provincia`, `localidad`) VALUES
(1, 1, 'Main Street', '123', 'California', 'Los Angeles'),
(2, 2, 'Rue de la Paix', '456', 'Île-de-France', 'Paris'),
(3, 3, 'Ginza', '789', NULL, 'Tokyo'),
(4, 4, 'Downing Street', '10', 'England', 'London'),
(5, 5, 'George Street', '456', 'New South Wales', 'Sydney');


LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-12 13:13:01
CREATE DATABASE  IF NOT EXISTS `tarjetas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `tarjetas`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tarjetas
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `estado_tarjeta`
--

DROP TABLE IF EXISTS `estado_tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_tarjeta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_tarjeta`
--

LOCK TABLES `estado_tarjeta` WRITE;
/*!40000 ALTER TABLE `estado_tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resumen_emitidos`
--

DROP TABLE IF EXISTS `resumen_emitidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resumen_emitidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resumeMongoID` varchar(45) NOT NULL,
  `total_pesos` decimal(10,0) NOT NULL,
  `total_dolares` decimal(10,0) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  PRIMARY KEY (`id`,`resumeMongoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resumen_emitidos`
--

LOCK TABLES `resumen_emitidos` WRITE;
/*!40000 ALTER TABLE `resumen_emitidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `resumen_emitidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjetas`
--

DROP TABLE IF EXISTS `tarjetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjetas` (
  `numtarj` varchar(30) NOT NULL,
  `numcue` int(11) NOT NULL,
  `f_vencimiento` varchar(10) NOT NULL,
  `pin` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `f_emision` varchar(10) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  PRIMARY KEY (`numtarj`),
  KEY `fk_estado` (`estado`),
  CONSTRAINT `fk_estado` FOREIGN KEY (`estado`) REFERENCES `estado_tarjeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjetas`
--


INSERT INTO `estado_tarjeta` (`id`, `detalle`) VALUES
(1, 'Activa'),
(2, 'Inactiva'),
(3, 'Suspendida'),
(4, 'Cancelada'),
(5, 'Bloqueada');


INSERT INTO `resumen_emitidos` (`id`, `resumeMongoID`, `total_pesos`, `total_dolares`, `fecha_vencimiento`) VALUES
(1, 'ABCDE12345', 10000, 500, '2024-05-01'),
(2, 'FGHIJ67890', 7500, 300, '2024-04-28'),
(3, 'KLMNO54321', 8500, 400, '2024-05-05'),
(4, 'PQRST09876', 6000, 250, '2024-05-02'),
(5, 'UVWXY13579', 12000, 600, '2024-05-10');


INSERT INTO `tarjetas` (`numtarj`, `numcue`, `f_vencimiento`, `pin`, `estado`, `f_emision`, `tipo`) VALUES
('1111222233334444', 1, '05/28', 1234, 1, '04/24', 'C'),
('2222333344445555', 2, '06/28', 2345, 1, '04/24', 'C'),
('3333444455556666', 3, '07/28', 3456, 1, '04/24', 'C'),
('4444555566667777', 4, '08/28', 4567, 1, '04/24', 'C'),
('5555666677778888', 5, '09/28', 5678, 1, '04/24', 'C');


LOCK TABLES `tarjetas` WRITE;
/*!40000 ALTER TABLE `tarjetas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjetas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-12 13:13:01











