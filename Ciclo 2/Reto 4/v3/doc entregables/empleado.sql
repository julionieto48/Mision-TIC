-- -----------------------------------------------------
-- Table `mydb`.`Empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Empleado` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Empleado` (
  `idEmpleado_emp` INT NOT NULL,
  `Tipo_emp` VARCHAR(45) NULL,
  `Turno_emp` DATE NULL,
  `Terminal_idTerminal` INT NOT NULL,
  PRIMARY KEY (`idEmpleado_emp`, `Terminal_idTerminal`),
  INDEX `fk_Empleado_Terminal1_idx` (`Terminal_idTerminal` ASC) VISIBLE,
  CONSTRAINT `fk_Empleado_Terminal1`
    FOREIGN KEY (`Terminal_idTerminal`)
    REFERENCES `mydb`.`Terminal` (`idTerminal_term`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `mydb`.`empleado` (`idEmpleado_emp`, `Tipo_emp`, `Turno_emp`, `Terminal_idTerminal`) VALUES ('1001', 'Conductor', '2021-08-24', '4');
INSERT INTO `mydb`.`empleado` (`idEmpleado_emp`, `Tipo_emp`, `Turno_emp`, `Terminal_idTerminal`) VALUES ('1002', 'Conductor', '2021-08-24', '5');
INSERT INTO `mydb`.`empleado` (`idEmpleado_emp`, `Tipo_emp`, `Turno_emp`, `Terminal_idTerminal`) VALUES ('1003', 'Conductor', '2021-08-24', '5');
INSERT INTO `mydb`.`empleado` (`idEmpleado_emp`, `Tipo_emp`, `Turno_emp`, `Terminal_idTerminal`) VALUES ('1004', 'Conductor', '2021-08-24', '1');
INSERT INTO `mydb`.`empleado` (`idEmpleado_emp`, `Tipo_emp`, `Turno_emp`, `Terminal_idTerminal`) VALUES ('1005', 'Conductor', '2021-08-24', '3');
