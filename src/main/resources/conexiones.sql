CREATE DATABASE  IF NOT EXISTS `conexiones` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `conexiones`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: conexiones
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `connectiontoken`
--

DROP TABLE IF EXISTS `connectiontoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `connectiontoken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `host` varchar(200) DEFAULT NULL COMMENT 'Corresponde a la dirección del servidor de base de\ndatos',
  `userdb` varchar(50) DEFAULT NULL COMMENT 'Corresponde al nombre del usuario de conexión a\nla base de datos',
  `pass` varchar(100) DEFAULT NULL,
  `db` varchar(50) DEFAULT NULL COMMENT 'Corresponde al nombre de la base de datos',
  `token` varchar(50) DEFAULT NULL COMMENT 'Define el token que sera utilizado para realizar la\nbusqueda de los datos de conexión a la base de datos. Este token sera único y utilizado para la\nconexión',
  `port` smallint DEFAULT NULL COMMENT 'Define el puerto utilizado para la conexión al servidor de\nbase de datos',
  `user` int DEFAULT NULL,
  `state` smallint DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT 'Define el tipo de driver a utilizar en la base de datos',
  PRIMARY KEY (`id`),
  KEY `FK_connectiontoken_typedb` (`type`),
  KEY `FK_connectiontoken_usuario` (`user`),
  CONSTRAINT `FK_connectiontoken_typedb` FOREIGN KEY (`type`) REFERENCES `typedb` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_connectiontoken_usuario` FOREIGN KEY (`user`) REFERENCES `usuario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Almacena la información del Token de Conexión a al base de datos. Este proceso se\nrealiza por cada conexión que se realice a cada base de datos para ser utilizada y enviarla al reporte.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connectiontoken`
--

LOCK TABLES `connectiontoken` WRITE;
/*!40000 ALTER TABLE `connectiontoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `connectiontoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporte`
--

DROP TABLE IF EXISTS `reporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reporte` (
  `id` int NOT NULL AUTO_INCREMENT,
  `file` varchar(100) DEFAULT NULL COMMENT 'Almacena el nombre del archivo jasper a utilizar',
  `conexion` int DEFAULT NULL,
  `datecreate` timestamp NULL DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL COMMENT 'Define el estado del reporte',
  `description` varchar(500) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_reporte_connectiontoken` (`conexion`),
  CONSTRAINT `FK_reporte_connectiontoken` FOREIGN KEY (`conexion`) REFERENCES `connectiontoken` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Almacena la información de los hosteados en el sistema';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte`
--

LOCK TABLES `reporte` WRITE;
/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL COMMENT 'Almacena la información del rol',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Almacena la información de los roles del sistema';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador'),(2,'Usuario');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguimiento`
--

DROP TABLE IF EXISTS `seguimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seguimiento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `report` int DEFAULT NULL,
  `dategenerate` timestamp NULL DEFAULT NULL,
  `state` smallint DEFAULT NULL,
  `result` varchar(500) DEFAULT NULL COMMENT 'Almacena el resultado de la consulta realizada al\nservidor',
  `detailrequest` varchar(1000) DEFAULT NULL COMMENT 'Almacena los datos de las variables\ningresadas',
  `type` varchar(10) DEFAULT NULL COMMENT 'Define el tipo de generación, si fue xls o pdf. ',
  `filegenerate` varchar(100) DEFAULT NULL COMMENT 'Define el nombre del archivo generado',
  PRIMARY KEY (`id`),
  KEY `FK_seguimiento_reporte` (`report`),
  CONSTRAINT `FK_seguimiento_reporte` FOREIGN KEY (`report`) REFERENCES `reporte` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Almacena información acerca del uso de los reportes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimiento`
--

LOCK TABLES `seguimiento` WRITE;
/*!40000 ALTER TABLE `seguimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `seguimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typedb`
--

DROP TABLE IF EXISTS `typedb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typedb` (
  `id` varchar(20) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `driver` varchar(200) DEFAULT NULL COMMENT 'Define los datos del driver',
  `aditional` varchar(500) DEFAULT NULL COMMENT 'Define los valores adicionales del Driver',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Almacena la información de los distintos tipos de conexión que soporta el sistema';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typedb`
--

LOCK TABLES `typedb` WRITE;
/*!40000 ALTER TABLE `typedb` DISABLE KEYS */;
/*!40000 ALTER TABLE `typedb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) DEFAULT NULL COMMENT 'Define el usuario utilizado en el sistema, el cual\\\\nno puede ser cambiado porque genera estructuras de datos y demas.',
  `email` varchar(100) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  `rol_id` int DEFAULT NULL,
  `state` smallint DEFAULT NULL COMMENT 'Define el estado del usuario, que puede ser sin activar,\nbloqueado u otro estado disponible.',
  PRIMARY KEY (`id`),
  KEY `FK_usuario_rol` (`rol_id`),
  CONSTRAINT `FK_usuario_rol` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Almacena la información de los usuarios del sistema';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-28 13:07:10
