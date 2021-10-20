-- -----------------------------------------------------
-- Table `mydb`.`Ruta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Ruta` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Ruta` (
  `Terminal_idTerminal` INT NOT NULL,
  `Autobus_idAutobus` INT NOT NULL,
  `Sillas_disponibles_rut` INT NULL,
  `Fecha_de_salida_rut` DATE NULL,
  `Precio_rut` INT NULL,
  `idRuta_rut` INT NOT NULL,
  `Destino_Ruta` VARCHAR(45) NULL,
  PRIMARY KEY (`Terminal_idTerminal`, `Autobus_idAutobus`, `idRuta_rut`),
  INDEX `fk_Terminal_has_Autobus_Autobus1_idx` (`Autobus_idAutobus` ASC) VISIBLE,
  INDEX `fk_Terminal_has_Autobus_Terminal1_idx` (`Terminal_idTerminal` ASC) VISIBLE,
  CONSTRAINT `fk_Terminal_has_Autobus_Terminal1`
    FOREIGN KEY (`Terminal_idTerminal`)
    REFERENCES `mydb`.`Terminal` (`idTerminal_term`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Terminal_has_Autobus_Autobus1`
    FOREIGN KEY (`Autobus_idAutobus`)
    REFERENCES `mydb`.`Autobus` (`idAutobus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `mydb`.`ruta` (`Terminal_idTerminal`, `Autobus_idAutobus`, `Sillas_disponibles_rut`, `Fecha_de_salida_rut`, `Precio_rut`, `idRuta_rut`, `Destino_Ruta`) VALUES ('1', '5', '60', '2021-8-28', '50000', '1', '2');
INSERT INTO `mydb`.`ruta` (`Terminal_idTerminal`, `Autobus_idAutobus`, `Sillas_disponibles_rut`, `Fecha_de_salida_rut`, `Precio_rut`, `idRuta_rut`, `Destino_Ruta`) VALUES ('1', '4', '20', '2021-08-29', '48000', '2', '2');
INSERT INTO `mydb`.`ruta` (`Terminal_idTerminal`, `Autobus_idAutobus`, `Fecha_de_salida_rut`, `Precio_rut`, `idRuta_rut`, `Destino_Ruta`) VALUES ('3', '2', '2021-08-20', '555000', '3', '4');
INSERT INTO `mydb`.`ruta` (`Terminal_idTerminal`, `Autobus_idAutobus`, `Sillas_disponibles_rut`, `Fecha_de_salida_rut`, `Precio_rut`, `idRuta_rut`, `Destino_Ruta`) VALUES ('4', '2', '30', '2021-08-20', '555000', '4', '5');
UPDATE `mydb`.`ruta` SET `Sillas_disponibles_rut` = '50' WHERE (`Terminal_idTerminal` = '3') and (`Autobus_idAutobus` = '2') and (`idRuta_rut` = '3');
INSERT INTO `mydb`.`ruta` (`Terminal_idTerminal`, `Autobus_idAutobus`, `Sillas_disponibles_rut`, `Fecha_de_salida_rut`, `Precio_rut`, `idRuta_rut`, `Destino_Ruta`) VALUES ('4', '1', '50', '2021-08-20', '45000', '5', '6');
INSERT INTO `mydb`.`ruta` (`Terminal_idTerminal`, `Autobus_idAutobus`, `Sillas_disponibles_rut`, `Fecha_de_salida_rut`, `Precio_rut`, `idRuta_rut`, `Destino_Ruta`) VALUES ('3', '5', '60', '2021-08-29', '58741', '6', '4');
INSERT INTO `mydb`.`ruta` (`Terminal_idTerminal`, `Autobus_idAutobus`, `Sillas_disponibles_rut`, `Fecha_de_salida_rut`, `Precio_rut`, `idRuta_rut`, `Destino_Ruta`) VALUES ('3', '7', '45', '2021-08-29', '48987', '7', '4');
INSERT INTO `mydb`.`ruta` (`Terminal_idTerminal`, `Autobus_idAutobus`, `Sillas_disponibles_rut`, `Fecha_de_salida_rut`, `Precio_rut`, `idRuta_rut`, `Destino_Ruta`) VALUES ('7', '2', '44', '2021-08-28', '48745', '8', '8');
