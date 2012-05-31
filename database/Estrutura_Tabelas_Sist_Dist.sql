SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `sistdist` ;
CREATE SCHEMA IF NOT EXISTS `sistdist` DEFAULT CHARACTER SET latin1 ;
USE `sistdist` ;

-- -----------------------------------------------------
-- Table `sistdist`.`convidado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistdist`.`convidado` ;

CREATE  TABLE IF NOT EXISTS `sistdist`.`convidado` (
  `CONV_ID_CONVIDADO` INT(11) NOT NULL AUTO_INCREMENT ,
  `CONV_NOME` VARCHAR(45) NOT NULL ,
  `CONV_EMAIL` VARCHAR(45) NOT NULL ,
  `CONV_TELEFONE` VARCHAR(45) NOT NULL ,
  `CONV_ORIGEM` VARCHAR(45) NOT NULL ,
  `CONV_FUNCAO` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`CONV_ID_CONVIDADO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sistdist`.`tipo_evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistdist`.`tipo_evento` ;

CREATE  TABLE IF NOT EXISTS `sistdist`.`tipo_evento` (
  `TPEV_ID_TP_EVENTO` INT(11) NOT NULL AUTO_INCREMENT ,
  `TPEV_NOME` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`TPEV_ID_TP_EVENTO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sistdist`.`evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistdist`.`evento` ;

CREATE  TABLE IF NOT EXISTS `sistdist`.`evento` (
  `EVEN_ID_EVENTO` INT(11) NOT NULL AUTO_INCREMENT ,
  `EVEN_NOME` VARCHAR(45) NOT NULL ,
  `EVEN_LOCAL` VARCHAR(45) NOT NULL ,
  `EVEN_RESPONSAVEL` VARCHAR(45) NOT NULL ,
  `EVEN_INICIO` DATETIME NOT NULL ,
  `EVEN_FIM` DATETIME NOT NULL ,
  `EVEN_STATUS` VARCHAR(1) NOT NULL ,
  `EVEN_TP_EVENTO` INT(11) NOT NULL ,
  PRIMARY KEY (`EVEN_ID_EVENTO`) ,
  CONSTRAINT `FK_TP_EVENTO`
    FOREIGN KEY (`EVEN_TP_EVENTO` )
    REFERENCES `sistdist`.`tipo_evento` (`TPEV_ID_TP_EVENTO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `FK_TP_EVENTO` ON `sistdist`.`evento` (`EVEN_TP_EVENTO` ASC) ;


-- -----------------------------------------------------
-- Table `sistdist`.`funcao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistdist`.`funcao` ;

CREATE  TABLE IF NOT EXISTS `sistdist`.`funcao` (
  `FUNC_ID_FUNCAO` INT(11) NOT NULL AUTO_INCREMENT ,
  `FUNC_NOME` VARCHAR(100) NULL DEFAULT NULL ,
  PRIMARY KEY (`FUNC_ID_FUNCAO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `sistdist`.`evento_convidado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistdist`.`evento_convidado` ;

CREATE  TABLE IF NOT EXISTS `sistdist`.`evento_convidado` (
  `EVCO_ID_EVENTO` INT(11) NOT NULL AUTO_INCREMENT ,
  `EVCO_ID_CONVIDADO` INT(11) NOT NULL ,
  `EVCO_ID_FUNCAO` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`EVCO_ID_EVENTO`, `EVCO_ID_CONVIDADO`) ,
  CONSTRAINT `FK_CONVIDADO`
    FOREIGN KEY (`EVCO_ID_CONVIDADO` )
    REFERENCES `sistdist`.`convidado` (`CONV_ID_CONVIDADO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_EVENTO`
    FOREIGN KEY (`EVCO_ID_EVENTO` )
    REFERENCES `sistdist`.`evento` (`EVEN_ID_EVENTO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_func_evento_convidado`
    FOREIGN KEY (`EVCO_ID_FUNCAO` )
    REFERENCES `sistdist`.`funcao` (`FUNC_ID_FUNCAO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `FK_EVENTO` ON `sistdist`.`evento_convidado` (`EVCO_ID_EVENTO` ASC) ;

CREATE INDEX `FK_CONVIDADO` ON `sistdist`.`evento_convidado` (`EVCO_ID_CONVIDADO` ASC) ;

CREATE INDEX `fk_func_evento_convidado` ON `sistdist`.`evento_convidado` (`EVCO_ID_FUNCAO` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
