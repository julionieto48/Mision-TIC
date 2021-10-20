-- -----------------------------------------------------
-- Table `mydb`.`museo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`museo` ;

CREATE TABLE IF NOT EXISTS `mydb`.`museo` (
  `idmuseo` INT NOT NULL,
  `Museo_nombre` VARCHAR(15) NULL,
  PRIMARY KEY (`idmuseo`))
ENGINE = InnoDB;

INSERT INTO `mydb`.`museo` (`idmuseo`, `Museo_nombre`) VALUES ('101', 'louvre');
INSERT INTO `mydb`.`museo` (`idmuseo`, `Museo_nombre`) VALUES ('102', 'Shangai');
INSERT INTO `mydb`.`museo` (`idmuseo`, `Museo_nombre`) VALUES ('103', 'Met');
INSERT INTO `mydb`.`museo` (`idmuseo`, `Museo_nombre`) VALUES ('104', 'Oro');
