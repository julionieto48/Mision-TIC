-- -----------------------------------------------------
-- Table `mydb`.`Exposicion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Exposicion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Exposicion` (
  `idExposicion` INT NOT NULL,
  `nombre_exposicion` VARCHAR(45) NULL,
  PRIMARY KEY (`idExposicion`))
ENGINE = InnoDB;


INSERT INTO `mydb`.`exposicion` (`idExposicion`, `nombre_exposicion`) VALUES ('1004', 'abstracto');
INSERT INTO `mydb`.`exposicion` (`idExposicion`, `nombre_exposicion`) VALUES ('1005', 'aburrido');
INSERT INTO `mydb`.`exposicion` (`idExposicion`, `nombre_exposicion`) VALUES ('1006', 'monedas');
INSERT INTO `mydb`.`exposicion` (`idExposicion`, `nombre_exposicion`) VALUES ('1007', 'da vinci');
INSERT INTO `mydb`.`exposicion` (`idExposicion`, `nombre_exposicion`) VALUES ('1008', 'cubismo');
