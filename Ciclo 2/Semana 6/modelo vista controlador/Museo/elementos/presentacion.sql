-- -----------------------------------------------------
-- Table `mydb`.`Presentacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Presentacion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Presentacion` (
  `Presentacion_id` VARCHAR(45) NOT NULL,
  `museo_idmuseo` INT NOT NULL,
  `Obra_idObra` INT NOT NULL,
  `fechainicio_presentacion` VARCHAR(45) NULL,
  PRIMARY KEY (`Presentacion_id`, `museo_idmuseo`, `Obra_idObra`),
  INDEX `fk_museo_has_Obra_Obra1_idx` (`Obra_idObra` ASC) VISIBLE,
  INDEX `fk_museo_has_Obra_museo_idx` (`museo_idmuseo` ASC) VISIBLE,
  CONSTRAINT `fk_museo_has_Obra_museo`
    FOREIGN KEY (`museo_idmuseo`)
    REFERENCES `mydb`.`museo` (`idmuseo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_museo_has_Obra_Obra1`
    FOREIGN KEY (`Obra_idObra`)
    REFERENCES `mydb`.`Obra` (`idObra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


INSERT INTO `mydb`.`presentacion` (`Presentacion_id`, `museo_idmuseo`, `Obra_idObra`, `fechainicio_presentacion`) VALUES ('111', '101', '111', 'marzo');
INSERT INTO `mydb`.`presentacion` (`Presentacion_id`, `museo_idmuseo`, `Obra_idObra`, `fechainicio_presentacion`) VALUES ('112', '104', '111', 'marzo');
INSERT INTO `mydb`.`presentacion` (`Presentacion_id`, `museo_idmuseo`, `Obra_idObra`, `fechainicio_presentacion`) VALUES ('113', '101', '115', 'abril');
INSERT INTO `mydb`.`presentacion` (`Presentacion_id`, `museo_idmuseo`, `Obra_idObra`, `fechainicio_presentacion`) VALUES ('114', '104', '111', 'marzo');
INSERT INTO `mydb`.`presentacion` (`Presentacion_id`, `museo_idmuseo`, `Obra_idObra`, `fechainicio_presentacion`) VALUES ('115', '102', '111', 'abril');
INSERT INTO `mydb`.`presentacion` (`Presentacion_id`, `museo_idmuseo`, `Obra_idObra`, `fechainicio_presentacion`) VALUES ('116', '103', '111', 'marzo');
