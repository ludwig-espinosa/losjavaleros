-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dg
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dg
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dg` DEFAULT CHARACTER SET utf8 ;
USE `dg` ;

-- -----------------------------------------------------
-- Table `dg`.`categoria_articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`categoria_articulo` (
  `idCatArt` INT(11) NOT NULL AUTO_INCREMENT,
  `CATEGORIA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCatArt`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`categoria_pack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`categoria_pack` (
  `IDCATEGORIA` INT(11) NOT NULL AUTO_INCREMENT,
  `CATEGORIA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IDCATEGORIA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`bancos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`bancos` (
  `BAN_ID_BANCO` INT(11) NOT NULL AUTO_INCREMENT,
  `BAN_DESCRIPCION` VARCHAR(45) NOT NULL,
  `CODIGO_BANCO` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`BAN_ID_BANCO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`cliente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `RUT` INT(11) NOT NULL,
  `CLI_NOMBRE` VARCHAR(45) NOT NULL,
  `CLI_APELLIDO` VARCHAR(45) NOT NULL,
  `CLI_DIRECCION` VARCHAR(45) NOT NULL,
  `CLI_TELEFONO` INT(11) NOT NULL,
  `CLI_CORREO` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `RUT`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`comunas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`comunas` (
  `idComuna` VARCHAR(45) NOT NULL,
  `COM_DESCRIPCION` VARCHAR(45) NOT NULL,
  `CODIGO_COMUNAS` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idComuna`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`estados_venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`estados_venta` (
  `idEstVta` VARCHAR(45) NOT NULL,
  `EST_DESCRIPCION` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEstVta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`proveedor` (
  `PRO_RUT_PROVEEDOR` VARCHAR(10) NOT NULL,
  `PRO_NOMBRE` VARCHAR(45) NOT NULL,
  `PRO_TELEFONO` INT(11) NOT NULL,
  `PRO_CORREO` VARCHAR(45) NOT NULL,
  `PRO_DIRECCION` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PRO_RUT_PROVEEDOR`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`rrss`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`rrss` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `RRS_NOMBRE` VARCHAR(45) NOT NULL,
  `CodioRS` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`usuarios` (
  `USU_ID_USUARIO` INT(11) NOT NULL,
  `USU_NOMBRE` VARCHAR(45) NOT NULL,
  `USU_CLAVE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USU_ID_USUARIO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`articulo` (
  `ART_ID_ARTICULO` INT(11) NOT NULL AUTO_INCREMENT,
  `CAT_ID_CATEGORIA` INT(11) NOT NULL,
  `ART_DESCRIPCION` VARCHAR(45) NOT NULL,
  `ART_STOCK` INT(11) NOT NULL,
  `ART_FECHA_VENCIMIENTO` DATE NOT NULL,
  PRIMARY KEY (`ART_ID_ARTICULO`),
  INDEX `FKCatArt_idx` (`CAT_ID_CATEGORIA` ASC),
  CONSTRAINT `FKCatArt`
    FOREIGN KEY (`CAT_ID_CATEGORIA`)
    REFERENCES `dg`.`categoria_articulo` (`idCatArt`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`pack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`pack` (
  `ID_PACK` INT(11) NOT NULL,
  `ID_CATEGORIA` INT(11) NOT NULL,
  `PCK_NOMBRE` VARCHAR(45) NOT NULL,
  `PCK_COSTO` VARCHAR(45) NOT NULL,
  `PCK_STOCK` INT(11) NOT NULL,
  PRIMARY KEY (`ID_PACK`),
  INDEX `FKCATPACK_idx` (`ID_CATEGORIA` ASC),
  CONSTRAINT `FKCATPACK`
    FOREIGN KEY (`ID_CATEGORIA`)
    REFERENCES `dg`.`categoria_pack` (`IDCATEGORIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dg`.`articulo_has_pack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`articulo_has_pack` (
  `idDetalle` INT(11) NOT NULL AUTO_INCREMENT,
  `ID_ARTICULO` INT(11) NOT NULL,
  `ID_PACK_FK` INT(11) NOT NULL,
  `CANTIDAD` INT(11) NOT NULL,
  PRIMARY KEY (`idDetalle`),
  INDEX `FKPACK_idx` (`ID_PACK_FK` ASC) ,
  INDEX `fkarticulo_idx` (`ID_ARTICULO` ASC),
  CONSTRAINT `fkarticulo`
    FOREIGN KEY (`ID_ARTICULO`)
    REFERENCES `dg`.`articulo` (`ART_ID_ARTICULO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `packfk`
    FOREIGN KEY (`ID_PACK_FK`)
    REFERENCES `dg`.`pack` (`ID_PACK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`factura` (
  `FAC_ID_FACTURA` INT(11) NOT NULL,
  `PRO_fk_PROVEEDOR` VARCHAR(10) NOT NULL,
  `FAC_NUMERO` INT(11) NOT NULL,
  `FAC_FECHA_FACTURA` DATE NOT NULL,
  PRIMARY KEY (`FAC_ID_FACTURA`),
  INDEX `fkProveedor_idx` (`PRO_fk_PROVEEDOR` ASC) ,
  CONSTRAINT `fkProveedor`
    FOREIGN KEY (`PRO_fk_PROVEEDOR`)
    REFERENCES `dg`.`proveedor` (`PRO_RUT_PROVEEDOR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`facturadetalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`facturadetalle` (
  `DET_ID_DETALLE` INT(11) NOT NULL,
  `FAC_ID_FACTURA` INT(11) NOT NULL,
  `ARTICULO_ART_ID_ARTICULO` INT(11) NOT NULL,
  `DET_CANTIDAD` INT(11) NOT NULL,
  `DET_VALOR` INT(11) NOT NULL,
  `DET_FECHA_VENCIMIENTO` DATE NOT NULL,
  PRIMARY KEY (`DET_ID_DETALLE`),
  INDEX `fkart_idx` (`ARTICULO_ART_ID_ARTICULO` ASC) ,
  INDEX `fkFactura_idx` (`FAC_ID_FACTURA` ASC) ,
  CONSTRAINT `fkFactura`
    FOREIGN KEY (`FAC_ID_FACTURA`)
    REFERENCES `dg`.`factura` (`FAC_ID_FACTURA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkart`
    FOREIGN KEY (`ARTICULO_ART_ID_ARTICULO`)
    REFERENCES `dg`.`articulo` (`ART_ID_ARTICULO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `dg`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dg`.`venta` (
  `ID_VENTA` INT(11) NOT NULL,
  `RUT_CLIENTE_PK` INT(11) NOT NULL,
  `ID_PACK_PK` INT(11) NOT NULL,
  `ID_BANCO_PK` INT(11) NOT NULL,
  `ID_RRSS_PK` INT(11) NOT NULL,
  `idEstVta_PK` VARCHAR(45) NOT NULL,
  `idComuna_PK` VARCHAR(45) NOT NULL,
  `VTA_TOTAL` INT(11) NOT NULL,
  `VTA_FECHA_VENTA` DATE NOT NULL,
  `VTA_FECHA_TRANSFERENCIA` DATE NOT NULL,
  `VTA_CODIGO_TRANSFERENCIA` INT(11) NOT NULL,
  `ST_ID_ESTADO` INT(11) NOT NULL,
  `VTA_NOMBRE_DESTINATARIO` VARCHAR(45) NOT NULL,
  `VTA_DIRECCION_DESTINATARIO` VARCHAR(45) NOT NULL,
  `COM_ID_COMUNA` INT(11) NOT NULL,
  `VTA_TELEFONO` INT(11) NOT NULL,
  `VTA_CORREO` VARCHAR(45) NOT NULL,
  `VTA_FECHA_ENTREGA` DATE NOT NULL,
  `VTA_HORA_ENTREGA_INICIAL` VARCHAR(4) NOT NULL,
  `VTA_HORA_ENTREGA_FINAL` VARCHAR(4) NOT NULL,
  `VTA_SALUDO` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`ID_VENTA`),
  INDEX `RUT_CLIENTE_PK_idx` (`RUT_CLIENTE_PK` ASC),
  INDEX `fk_idx` (`ID_BANCO_PK` ASC) VISIBLE,
  INDEX `fkrrss_idx` (`ID_RRSS_PK` ASC) VISIBLE,
  INDEX `fkpack_idx` (`ID_PACK_PK` ASC) VISIBLE,
  INDEX `fkEstVta_idx` (`idEstVta_PK` ASC) VISIBLE,
  INDEX `fkcomuna_idx` (`idComuna_PK` ASC) VISIBLE,
  CONSTRAINT `fkEstVta`
    FOREIGN KEY (`idEstVta_PK`)
    REFERENCES `dg`.`estados_venta` (`idEstVta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkbanco`
    FOREIGN KEY (`ID_BANCO_PK`)
    REFERENCES `dg`.`bancos` (`BAN_ID_BANCO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkcliente`
    FOREIGN KEY (`RUT_CLIENTE_PK`)
    REFERENCES `dg`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkcomuna`
    FOREIGN KEY (`idComuna_PK`)
    REFERENCES `dg`.`comunas` (`idComuna`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkpack`
    FOREIGN KEY (`ID_PACK_PK`)
    REFERENCES `dg`.`pack` (`ID_PACK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkrrss`
    FOREIGN KEY (`ID_RRSS_PK`)
    REFERENCES `dg`.`rrss` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
