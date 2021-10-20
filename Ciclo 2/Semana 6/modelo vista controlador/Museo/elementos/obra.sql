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
INSERT INTO `mydb`.`obra` (`idObra`, `nombre_obra`, `tipo_obra`, `costo_obra`, `Exposicion_idExposicion`) VALUES ('111', 'mona lisa', 'pintura', '100', '1005');
INSERT INTO `mydb`.`obra` (`idObra`, `nombre_obra`, `tipo_obra`, `costo_obra`, `Exposicion_idExposicion`) VALUES ('112', 'ultima cena', 'pintura', '187', '1005');
INSERT INTO `mydb`.`obra` (`idObra`, `nombre_obra`, `tipo_obra`, `costo_obra`, `Exposicion_idExposicion`) VALUES ('113', 'vitrubio', 'boceto', '458', '2004');
INSERT INTO `mydb`.`obra` (`idObra`, `nombre_obra`, `tipo_obra`, `costo_obra`, `Exposicion_idExposicion`) VALUES ('114', 'david', 'escultura', '156', '1007');
INSERT INTO `mydb`.`obra` (`idObra`, `nombre_obra`, `tipo_obra`, `costo_obra`, `Exposicion_idExposicion`) VALUES ('115', 'pimpinella', 'cancion', '144', '1007');
INSERT INTO `mydb`.`obra` (`idObra`, `nombre_obra`, `tipo_obra`, `costo_obra`, `Exposicion_idExposicion`) VALUES ('116', 'la soledad', 'pintura', '44', '1451');
