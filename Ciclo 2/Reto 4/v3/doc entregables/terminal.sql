-- -----------------------------------------------------
-- Table `mydb`.`Terminal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Terminal` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Terminal` (
  `idTerminal_term` INT NOT NULL,
  `Ciudad_term` VARCHAR(45) NULL,
  `Aforo_term` INT NULL,
  `Numero empleados_term` INT NULL,
  PRIMARY KEY (`idTerminal_term`))
ENGINE = InnoDB;

INSERT INTO `mydb`.`terminal` (`idTerminal_term`, `Ciudad_term`, `Aforo_term`, `Numero empleados_term`) VALUES ('1', 'Santa marta', '1200', '500');
INSERT INTO `mydb`.`terminal` (`idTerminal_term`, `Ciudad_term`, `Aforo_term`, `Numero empleados_term`) VALUES ('2', 'Cartagena', '1200', '487');
INSERT INTO `mydb`.`terminal` (`idTerminal_term`, `Ciudad_term`, `Aforo_term`, `Numero empleados_term`) VALUES ('3', 'Bogota', '5000', '840');
INSERT INTO `mydb`.`terminal` (`idTerminal_term`, `Ciudad_term`, `Aforo_term`, `Numero empleados_term`) VALUES ('4', 'Cali', '3000', '658');
INSERT INTO `mydb`.`terminal` (`idTerminal_term`, `Ciudad_term`, `Aforo_term`, `Numero empleados_term`) VALUES ('5', 'Tumaco', '500', '87');
INSERT INTO `mydb`.`terminal` (`idTerminal_term`, `Ciudad_term`, `Aforo_term`, `Numero empleados_term`) VALUES ('6', 'Medellin', '4500', '784');
INSERT INTO `mydb`.`terminal` (`idTerminal_term`, `Ciudad_term`, `Aforo_term`, `Numero empleados_term`) VALUES ('7', 'Valledupar', '120', '54');
INSERT INTO `mydb`.`terminal` (`idTerminal_term`, `Ciudad_term`, `Aforo_term`, `Numero empleados_term`) VALUES ('8', 'Barranquilla', '3000', '200');


