-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Bins
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Bins
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Bins` DEFAULT CHARACTER SET utf8 ;
USE `Bins` ;

-- -----------------------------------------------------
-- Table `Bins`.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bins`.`Clientes` (
  `idClientes` INT NOT NULL,
  `Nombres` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(45) NOT NULL,
  `Direccion` VARCHAR(45) NOT NULL,
  `Correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idClientes`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bins`.`Productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bins`.`Productos` (
  `idProductos` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Categoría` VARCHAR(45) NOT NULL,
  `Stock` INT NOT NULL,
  `Precio` INT NOT NULL,
  PRIMARY KEY (`idProductos`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
