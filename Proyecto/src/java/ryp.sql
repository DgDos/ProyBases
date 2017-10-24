-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.20-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ryp
DROP DATABASE IF EXISTS ryp;
CREATE DATABASE `ryp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ryp`;

-- Dumping structure for table ryp.empresa
CREATE TABLE IF NOT EXISTS `empresa` (
  `NIT` int(10) NOT NULL,
  `nombreEmpresa` varchar(30) NOT NULL,
  `usuarioE` varchar(30) NOT NULL,
  `passwordE` varchar(30) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  PRIMARY KEY (`NIT`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ryp.funciones
CREATE TABLE IF NOT EXISTS `funciones` (
  `idUsuario` int(5) NOT NULL,
  `idServicio` int(3) NOT NULL,
  `valoracion` int(2) NOT NULL,
  KEY `idUsuario` (`idUsuario`),
  KEY `idServicio` (`idServicio`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ryp.horario
CREATE TABLE IF NOT EXISTS `horario` (
  `idHorario` int(5) NOT NULL AUTO_INCREMENT,
  `idTaR` int(3) NOT NULL,
  `idTrabajador` int(5) NOT NULL,
  `horaInicio` int(4) NOT NULL,
  `horaFinal` int(4) NOT NULL,
  `Fecha` date NOT NULL,
  `estado` int(1) NOT NULL,
  PRIMARY KEY (`idHorario`),
  KEY `idTaR` (`idTaR`),
  KEY `idTrabajador` (`idTrabajador`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ryp.mensaje
CREATE TABLE IF NOT EXISTS `mensaje` (
  `idU1` int(5) NOT NULL,
  `idU2` int(5) NOT NULL,
  `Asunto` varchar(30) NOT NULL,
  `Texto` varchar(100) NOT NULL,
  KEY `idU1` (`idU1`),
  KEY `idU2` (`idU2`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ryp.notificacion
CREATE TABLE IF NOT EXISTS `notificacion` (
  `idHorario` int(5) NOT NULL,
  `fechaT` date NOT NULL,
  `supervisor` int(5) NOT NULL,
  KEY `idHorario` (`idHorario`),
  KEY `supervisor` (`supervisor`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ryp.servicio
CREATE TABLE IF NOT EXISTS `servicio` (
  `idServicio` int(3) NOT NULL AUTO_INCREMENT,
  `nombreS` varchar(30) NOT NULL,
  PRIMARY KEY (`idServicio`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ryp.trabajador
CREATE TABLE IF NOT EXISTS `trabajador` (
  `idUsuario` int(5) NOT NULL AUTO_INCREMENT,
  `usuarioT` varchar(30) NOT NULL,
  `passwordT` varchar(30) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `cargo` varchar(30) NOT NULL,
  `supervisor` int(5) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table ryp.trabajoarealizar
CREATE TABLE IF NOT EXISTS `trabajoarealizar` (
  `idTrabajo` int(3) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(10) NOT NULL,
  `idServicio` int(3) NOT NULL,
  `Urgencia` int(2) NOT NULL,
  `Detalles` varchar(50) NOT NULL,
  PRIMARY KEY (`idTrabajo`),
  KEY `idEmpresa` (`idEmpresa`),
  KEY `idServicio` (`idServicio`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
