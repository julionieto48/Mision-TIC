-- -----------------------------------------------------
-- Table `mydb`.`Equipaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Equipaje` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Equipaje` (
  `idEquipaje_eq` VARCHAR(6) NOT NULL,
  `Peso_eq` INT NULL,
  `Sobredimensionado_eq` VARCHAR(2) NULL,
  `Valor_eq` FLOAT NULL,
  PRIMARY KEY (`idEquipaje_eq`))
ENGINE = InnoDB;

INSERT INTO `mydb`.`equipaje` (`idEquipaje_eq`, `Peso_eq`, `Sobredimensionado_eq`, `Valor_eq`) VALUES ('R51v6', '5', 'No', '500');
