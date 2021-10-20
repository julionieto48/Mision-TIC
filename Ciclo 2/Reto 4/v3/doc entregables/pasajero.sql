-- -----------------------------------------------------
-- Table `mydb`.`Pasajero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Pasajero` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Pasajero` (
  `idPasajero` INT NOT NULL,
  `Cedula_pas` INT NOT NULL,
  `Nombre_pas` VARCHAR(45) NULL,
  `Sexo_pas` VARCHAR(45) NULL,
  `Direccion_pas` VARCHAR(45) NULL,
  `Telefono_pas` INT NULL,
  `VIP_pas` VARCHAR(45) NULL,
  `Edad_pas` VARCHAR(45) NULL,
  `Equipaje_idEquipaje` VARCHAR(6) NULL,
  PRIMARY KEY (`idPasajero`, `Cedula_pas`),
  INDEX `fk_Pasajero_Equipaje1_idx` (`Equipaje_idEquipaje` ASC) VISIBLE,
  CONSTRAINT `fk_Pasajero_Equipaje1`
    FOREIGN KEY (`Equipaje_idEquipaje`)
    REFERENCES `mydb`.`Equipaje` (`idEquipaje_eq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `mydb`.`pasajero` (`idPasajero`, `Cedula_pas`, `Nombre_pas`, `Sexo_pas`, `Direccion_pas`, `Telefono_pas`, `VIP_pas`, `Edad_pas`, `Equipaje_idEquipaje`) VALUES ('1001', '123753798', 'Jaime', 'M', '7894', '789456', 'No', '70', 'R51v6');
INSERT INTO `mydb`.`pasajero` (`idPasajero`, `Cedula_pas`, `Nombre_pas`, `Sexo_pas`, `Direccion_pas`, `Telefono_pas`, `VIP_pas`, `Edad_pas`) VALUES ('1002', '456789123', 'Arles Rodriguez', 'M', '7894', '357842', 'Si', '45');
INSERT INTO `mydb`.`pasajero` (`idPasajero`, `Cedula_pas`, `Nombre_pas`, `Sexo_pas`, `Direccion_pas`, `Telefono_pas`, `VIP_pas`, `Edad_pas`, `Equipaje_idEquipaje`) VALUES ('1003', '147789654', 'Camilo Cubides', 'F', '3548', '259879', 'No', '12', '100z');
INSERT INTO `mydb`.`pasajero` (`idPasajero`, `Cedula_pas`, `Nombre_pas`, `Sexo_pas`, `Direccion_pas`, `Telefono_pas`, `VIP_pas`, `Edad_pas`, `Equipaje_idEquipaje`) VALUES ('1004', '357456159', 'Elizabeth Leon', 'F', '2975', '687453', 'No', '34', '14zz');
INSERT INTO `mydb`.`pasajero` (`idPasajero`, `Cedula_pas`, `Nombre_pas`, `Sexo_pas`, `Direccion_pas`, `Telefono_pas`, `VIP_pas`, `Edad_pas`, `Equipaje_idEquipaje`) VALUES ('1005', '157867423', 'Jonatan Gomez', 'M', '4577', '125987', 'No', '78', '478z');
