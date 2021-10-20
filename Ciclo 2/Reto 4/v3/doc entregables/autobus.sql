-- -----------------------------------------------------
-- Table `mydb`.`Autobus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Autobus` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Autobus` (
  `idAutobus` INT NOT NULL,
  `Capacidad_bus` INT NULL,
  `Marca` VARCHAR(45) NULL,
  `Modelo_bus` VARCHAR(45) NULL,
  `Compañia_bus` VARCHAR(45) NULL,
  PRIMARY KEY (`idAutobus`))
ENGINE = InnoDB;

