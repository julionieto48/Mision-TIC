-- MySQL Script generated by MySQL Workbench
-- Fri Sep  3 11:00:32 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`museo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`museo` ;

CREATE TABLE IF NOT EXISTS `mydb`.`museo` (
  `idmuseo` INT NOT NULL,
  `Museo_nombre` VARCHAR(15) NULL,
  PRIMARY KEY (`idmuseo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Exposicion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Exposicion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Exposicion` (
  `idExposicion` INT NOT NULL,
  `nombre_exposicion` VARCHAR(45) NULL,
  PRIMARY KEY (`idExposicion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Obra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Obra` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Obra` (
  `idObra` INT NOT NULL,
  `nombre_obra` VARCHAR(45) NULL,
  `tipo_obra` VARCHAR(45) NULL,
  `costo_obra` VARCHAR(45) NULL,
  `Exposicion_idExposicion` INT NOT NULL,
  PRIMARY KEY (`idObra`, `Exposicion_idExposicion`),
  INDEX `fk_Obra_Exposicion1_idx` (`Exposicion_idExposicion` ASC) VISIBLE,
  CONSTRAINT `fk_Obra_Exposicion1`
    FOREIGN KEY (`Exposicion_idExposicion`)
    REFERENCES `mydb`.`Exposicion` (`idExposicion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Presentacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Presentacion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Presentacion` (
  `Presentacion_id` VARCHAR(45) NOT NULL,
  `museo_idmuseo` INT NOT NULL,
  `Obra_idObra` INT NOT NULL,
  `fechainicio_presentacion` VARCHAR(45) NULL,
  PRIMARY KEY (`Presentacion_id`, `museo_idmuseo`, `Obra_idObra`),
  INDEX `fk_museo_has_Obra_Obra1_idx` (`Obra_idObra` ASC) VISIBLE,
  INDEX `fk_museo_has_Obra_museo_idx` (`museo_idmuseo` ASC) VISIBLE,
  CONSTRAINT `fk_museo_has_Obra_museo`
    FOREIGN KEY (`museo_idmuseo`)
    REFERENCES `mydb`.`museo` (`idmuseo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_museo_has_Obra_Obra1`
    FOREIGN KEY (`Obra_idObra`)
    REFERENCES `mydb`.`Obra` (`idObra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;