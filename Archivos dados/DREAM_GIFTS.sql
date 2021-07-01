-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`RRSS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RRSS` (
  `RRS_ID_RRSS` INT NOT NULL,
  `RRS_NOMBRE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RRS_ID_RRSS`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `CLI_ID_CLIENTE` INT NOT NULL,
  `CLI_NOMBRE` VARCHAR(45) NOT NULL,
  `CLI_APELLIDO` VARCHAR(45) NOT NULL,
  `CLI_DIRECCION` VARCHAR(45) NOT NULL,
  `CLI_TELEFONO` INT NOT NULL,
  `CLI_CORREO` VARCHAR(45) NOT NULL,
  `RRS_ID_RRSS` INT NOT NULL,
  PRIMARY KEY (`CLI_ID_CLIENTE`),
  INDEX `fk_cliente_RRSS1_idx` (`RRS_ID_RRSS` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_RRSS1`
    FOREIGN KEY (`RRS_ID_RRSS`)
    REFERENCES `mydb`.`RRSS` (`RRS_ID_RRSS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pack` (
  `PCK_ID_PACK` INT NOT NULL,
  `PCK_NOMBRE` VARCHAR(45) NOT NULL,
  `PCK_COSTO` VARCHAR(45) NOT NULL,
  `CAT_ID_CATEGORIA` INT NOT NULL,
  `PCK_STOCK` INT NOT NULL,
  PRIMARY KEY (`PCK_ID_PACK`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bancos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bancos` (
  `BAN_ID_BANCO` INT NOT NULL,
  `BAN_DESCRIPCION` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`BAN_ID_BANCO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`venta` (
  `VTA_ID_VENTA` INT NOT NULL,
  `CLI_ID_CLIENTE` INT NOT NULL,
  `PCK_ID_PACK` INT NOT NULL,
  `BAN_ID_BANCO` INT NOT NULL,
  `VTA_TOTAL` INT NOT NULL,
  `VTA_FECHA_VENTA` DATE NOT NULL,
  `VTA_FECHA_TRANSFERENCIA` DATE NOT NULL,
  `VTA_CODIGO_TRANSFERENCIA` INT NOT NULL,
  `EST_ID_ESTADO` INT NOT NULL,
  `VTA_NOMBRE_DESTINATARIO` VARCHAR(45) NOT NULL,
  `VTA_DIRECCION_DESTINATARIO` VARCHAR(45) NOT NULL,
  `COM_ID_COMUNA` INT NOT NULL,
  `VTA_TELEFONO` INT NOT NULL,
  `VTA_CORREO` VARCHAR(45) NOT NULL,
  `VTA_FECHA_ENTREGA` DATE NOT NULL,
  `VTA_HORA_ENTREGA_INICIAL` VARCHAR(4) NOT NULL,
  `VTA_HORA_ENTREGA_FINAL` VARCHAR(4) NOT NULL,
  `VTA_SALUDO` VARCHAR(250) NOT NULL,
  `RRS_ID_RRSS` INT NOT NULL,
  PRIMARY KEY (`VTA_ID_VENTA`),
  INDEX `fk_venta_cliente_idx` (`CLI_ID_CLIENTE` ASC) VISIBLE,
  INDEX `fk_venta_pack1_idx` (`PCK_ID_PACK` ASC) VISIBLE,
  INDEX `fk_venta_Bancos1_idx` (`BAN_ID_BANCO` ASC) VISIBLE,
  INDEX `RRS_ID_RRSS_idx` (`RRS_ID_RRSS` ASC) VISIBLE,
  CONSTRAINT `fk_venta_cliente`
    FOREIGN KEY (`CLI_ID_CLIENTE`)
    REFERENCES `mydb`.`cliente` (`CLI_ID_CLIENTE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_pack1`
    FOREIGN KEY (`PCK_ID_PACK`)
    REFERENCES `mydb`.`Pack` (`PCK_ID_PACK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_Bancos1`
    FOREIGN KEY (`BAN_ID_BANCO`)
    REFERENCES `mydb`.`Bancos` (`BAN_ID_BANCO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `RRS_ID_RRSS`
    FOREIGN KEY (`RRS_ID_RRSS`)
    REFERENCES `mydb`.`RRSS` (`RRS_ID_RRSS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`proveedor` (
  `PRO_ID_PROVEEDOR` INT NOT NULL,
  `PRO_NOMBRE` VARCHAR(45) NOT NULL,
  `PRO_TELEFONO` INT NOT NULL,
  `PRO_CORREO` VARCHAR(45) NOT NULL,
  `PRO_DIRECCION` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PRO_ID_PROVEEDOR`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Factura` (
  `FAC_ID_FACTURA` VARCHAR(45) NOT NULL,
  `FAC_NUMERO` INT NOT NULL,
  `PRO_ID_PROVEEDOR` INT NOT NULL,
  `FAC_FECHA_FACTURA` DATE NOT NULL,
  INDEX `fk_articulo_has_proveedor_proveedor1_idx` (`PRO_ID_PROVEEDOR` ASC) VISIBLE,
  PRIMARY KEY (`FAC_ID_FACTURA`),
  CONSTRAINT `fk_articulo_has_proveedor_proveedor1`
    FOREIGN KEY (`PRO_ID_PROVEEDOR`)
    REFERENCES `mydb`.`proveedor` (`PRO_ID_PROVEEDOR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`FacturaDetalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`FacturaDetalle` (
  `DET_ID_DETALLE` INT NOT NULL,
  `FAC_ID_FACTURA` INT NOT NULL,
  `ART_ID_ARTICULO` VARCHAR(25) NOT NULL,
  `DET_FECHA_VENCIMIENTO` DATE NOT NULL,
  `DET_CANTIDAD` INT NOT NULL,
  `DET_VALOR` INT NOT NULL,
  PRIMARY KEY (`DET_ID_DETALLE`),
  INDEX `fk_articulo_has_Factura_Factura1_idx` (`FAC_ID_FACTURA` ASC) VISIBLE,
  CONSTRAINT `fk_articulo_has_Factura_Factura1`
    FOREIGN KEY (`FAC_ID_FACTURA`)
    REFERENCES `mydb`.`Factura` (`FAC_ID_FACTURA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ARTICULO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ARTICULO` (
  `ART_ID_ARTICULO` VARCHAR(25) NOT NULL,
  `CAT_ID_CATEGORIA` INT NULL,
  `ART_DESCRIPCION` VARCHAR(45) NOT NULL,
  `ART_STOCK` INT NOT NULL,
  `ART_FECHA_VENCIMIENTO` DATE NOT NULL,
  PRIMARY KEY (`ART_ID_ARTICULO`),
  CONSTRAINT `ART_ID_ARTICULO`
    FOREIGN KEY ()
    REFERENCES `mydb`.`FacturaDetalle` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pack_has_articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pack_has_articulo` (
  `PCK_ID_PACK` INT NOT NULL,
  `ART_ID_ARTICULO` INT NOT NULL,
  `CANTIDAD` INT NOT NULL,
  PRIMARY KEY (`PCK_ID_PACK`, `ART_ID_ARTICULO`),
  INDEX `fk_pack_has_articulo_articulo1_idx` (`ART_ID_ARTICULO` ASC) VISIBLE,
  INDEX `fk_pack_has_articulo_pack1_idx` (`PCK_ID_PACK` ASC) VISIBLE,
  CONSTRAINT `fk_pack_has_articulo_pack1`
    FOREIGN KEY (`PCK_ID_PACK`)
    REFERENCES `mydb`.`Pack` (`PCK_ID_PACK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pack_has_articulo_articulo1`
    FOREIGN KEY (`ART_ID_ARTICULO`)
    REFERENCES `mydb`.`ARTICULO` (`ART_ID_ARTICULO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuarios` (
  `USU_ID_USUARIO` INT NULL,
  `USU_NOMBRE` VARCHAR(45) NOT NULL,
  `USU_CLAVE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USU_ID_USUARIO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Comunas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Comunas` (
  `COM_ID_COMUNA` INT NOT NULL,
  `COM_DESCRIPCION` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`COM_ID_COMUNA`),
  CONSTRAINT `fk_Comunas_venta1`
    FOREIGN KEY (`COM_ID_COMUNA`)
    REFERENCES `mydb`.`venta` (`COM_ID_COMUNA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ESTADOS_VENTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ESTADOS_VENTA` (
  `EST_ID_ESTADO` INT NOT NULL,
  `EST_DESCRIPCION` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`EST_ID_ESTADO`),
  CONSTRAINT `fk_Comunas_venta10`
    FOREIGN KEY (`EST_ID_ESTADO`)
    REFERENCES `mydb`.`venta` (`COM_ID_COMUNA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CATEGORIA_ARTICULO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CATEGORIA_ARTICULO` (
  `CAT_ID_CATEGORIA` INT NULL,
  `CAT_DESCRIPCION` VARCHAR(45) NOT NULL,
  INDEX `fk_CATEGORIA_ARTICULO_articulo1_idx` (`CAT_ID_CATEGORIA` ASC) VISIBLE,
  CONSTRAINT `fk_CATEGORIA_ARTICULO_articulo1`
    FOREIGN KEY (`CAT_ID_CATEGORIA`)
    REFERENCES `mydb`.`ARTICULO` (`CAT_ID_CATEGORIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
