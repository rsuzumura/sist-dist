SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';



CREATE SCHEMA IF NOT EXISTS `sistdist` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

USE `sistdist` ;



-- -----------------------------------------------------

-- Table `sistdist`.`TIPO_EVENTO`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `sistdist`.`TIPO_EVENTO` (

  `TPEV_ID_TP_EVENTO` INT NOT NULL ,

  `TPEV_NOME` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`TPEV_ID_TP_EVENTO`) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `sistdist`.`Evento`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `sistdist`.`Evento` (

  `EVEN_ID_EVENTO` INT NOT NULL ,

  `EVEN_NOME` VARCHAR(45) NOT NULL ,

  `EVEN_LOCAL` VARCHAR(45) NOT NULL ,

  `EVEN_RESPONSAVEL` VARCHAR(45) NOT NULL ,

  `EVEN_INICIO` DATETIME NOT NULL ,

  `EVEN_FIM` DATETIME NOT NULL ,

  `EVEN_STATUS` VARCHAR(1) NOT NULL ,

  `EVEN_TP_EVENTO` INT NOT NULL ,

  PRIMARY KEY (`EVEN_ID_EVENTO`) ,

  INDEX `FK_TP_EVENTO` (`EVEN_TP_EVENTO` ASC) ,

  CONSTRAINT `FK_TP_EVENTO`

    FOREIGN KEY (`EVEN_TP_EVENTO` )

    REFERENCES `sistdist`.`TIPO_EVENTO` (`TPEV_ID_TP_EVENTO` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `sistdist`.`CONVIDADO`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `sistdist`.`CONVIDADO` (

  `CONV_ID_CONVIDADO` INT NOT NULL ,

  `CONV_NOME` VARCHAR(45) NOT NULL ,

  `CONV_EMAIL` VARCHAR(45) NOT NULL ,

  `CONV_TELEFONE` VARCHAR(45) NOT NULL ,

  `CONV_ORIGEM` VARCHAR(45) NOT NULL ,

  `CONV_FUNCAO` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`CONV_ID_CONVIDADO`) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `sistdist`.`FUNCAO`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `sistdist`.`FUNCAO` (

  `TPEV_ID_TP_EVENTO` INT NOT NULL ,

  `FUNC_ID_FUNCAO` INT NOT NULL ,

  `FUNC_NOME` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`TPEV_ID_TP_EVENTO`, `FUNC_ID_FUNCAO`) ,

  INDEX `FK_TIPO_EVENTO` (`TPEV_ID_TP_EVENTO` ASC) ,

  CONSTRAINT `FK_TIPO_EVENTO`

    FOREIGN KEY (`TPEV_ID_TP_EVENTO` )

    REFERENCES `sistdist`.`TIPO_EVENTO` (`TPEV_ID_TP_EVENTO` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `sistdist`.`EVENTO_CONVIDADO`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `sistdist`.`EVENTO_CONVIDADO` (

  `EVCO_ID_EVENTO` INT NOT NULL ,

  `EVCO_ID_CONVIDADO` INT NOT NULL ,

  PRIMARY KEY (`EVCO_ID_EVENTO`, `EVCO_ID_CONVIDADO`) ,

  INDEX `FK_EVENTO` (`EVCO_ID_EVENTO` ASC) ,

  INDEX `FK_CONVIDADO` (`EVCO_ID_CONVIDADO` ASC) ,

  CONSTRAINT `FK_EVENTO`

    FOREIGN KEY (`EVCO_ID_EVENTO` )

    REFERENCES `sistdist`.`Evento` (`EVEN_ID_EVENTO` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `FK_CONVIDADO`

    FOREIGN KEY (`EVCO_ID_CONVIDADO` )

    REFERENCES `sistdist`.`CONVIDADO` (`CONV_ID_CONVIDADO` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;







SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

