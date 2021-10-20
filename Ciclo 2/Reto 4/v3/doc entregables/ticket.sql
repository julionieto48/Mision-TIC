-- -----------------------------------------------------
-- Table `mydb`.`Ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Ticket` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Ticket` (
  `Numero_Ticket` VARCHAR(6) NOT NULL,
  `Pasajero_idPasajero` INT NOT NULL,
  `Ruta_Terminal_idTerminal` INT NOT NULL,
  `Ruta_Autobus_idAutobus` INT NOT NULL,
  `Ruta_idRuta` INT NOT NULL,
  PRIMARY KEY (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`),
  INDEX `fk_Autobus_has_Pasajero_Pasajero1_idx` (`Pasajero_idPasajero` ASC) VISIBLE,
  INDEX `fk_Ticket_Ruta1_idx` (`Ruta_Terminal_idTerminal` ASC, `Ruta_Autobus_idAutobus` ASC, `Ruta_idRuta` ASC) VISIBLE,
  CONSTRAINT `fk_Autobus_has_Pasajero_Pasajero1`
    FOREIGN KEY (`Pasajero_idPasajero`)
    REFERENCES `mydb`.`Pasajero` (`idPasajero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_Ruta1`
    FOREIGN KEY (`Ruta_Terminal_idTerminal` , `Ruta_Autobus_idAutobus` , `Ruta_idRuta`)
    REFERENCES `mydb`.`Ruta` (`Terminal_idTerminal` , `Autobus_idAutobus` , `idRuta_rut`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Arr323', '100', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Elv452', '101', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Fri981', '102', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Glo438', '103', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Int187', '104', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Lac375', '105', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Lov422', '106', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Mas874', '107', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Par414', '108', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('The853', '109', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Twa398', '110', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Via725', '111', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Vik782', '112', '1', '5', '1');
INSERT INTO `mydb`.`ticket` (`Numero_Ticket`, `Pasajero_idPasajero`, `Ruta_Terminal_idTerminal`, `Ruta_Autobus_idAutobus`, `Ruta_idRuta`) VALUES ('Ass500', '1001', '4', '2', '4');
