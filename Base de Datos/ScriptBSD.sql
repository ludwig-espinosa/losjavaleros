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
-- Table `mydb`.`Tabla Proveedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tabla Proveedores` (
  `ID_Proveedor` INT NOT NULL AUTO_INCREMENT,
  `Razon social` VARCHAR(45) NULL,
  `RUT` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `fono` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NULL,
  `Ciclo de facturacion` DATETIME NULL,
  `Estado` TINYINT NULL,
  PRIMARY KEY (`ID_Proveedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria_Articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria_Articulo` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(255) NOT NULL,
  `cod_categoria` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `mydb`.`Tabla Articulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tabla Articulos` (
  `ID_Articulo` INT NOT NULL,
  `ID_Proveedores` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `precio` INT NULL,
  `detalle` VARCHAR(45) NULL,
  `marca` VARCHAR(45) NULL,
  `cantidad` INT NULL,
  `fecha_vencimiento` DATETIME NULL,
  `categoria_id` INT NOT NULL,
  PRIMARY KEY (`ID_Articulo`),
  INDEX `categoria_id_idx` (`categoria_id` ASC) VISIBLE,
  INDEX `ID_Proveedores_idx` (`ID_Proveedores` ASC) VISIBLE,
  CONSTRAINT `ID_Proveedores`
    FOREIGN KEY (`ID_Proveedores`)
    REFERENCES `mydb`.`Tabla Proveedores` (`ID_Proveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `categoria_id`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `mydb`.`Categoria_Articulo` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tabla Pack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tabla Pack` (
  `ID_Pack` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `precio` INT NULL,
  PRIMARY KEY (`ID_Pack`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tabla clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tabla clientes` (
  `ID_Clientes` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `rut` VARCHAR(45) NOT NULL,
  `fecha nacimiento` DATETIME NOT NULL,
  `celular` VARCHAR(45) NOT NULL,
  `RRSS` VARCHAR(45) NOT NULL,
  `estado` TINYINT NULL,
  `direccion` VARCHAR(60) NULL,
  PRIMARY KEY (`ID_Clientes`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuarios` (
  `ID_Usuarios` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `RUT` VARCHAR(10) NOT NULL,
  `Contraseña` VARCHAR(45) NOT NULL,
  `Fecha de Inicio` DATETIME NOT NULL,
  `Fecha Termino` DATETIME NULL,
  `Estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_Usuarios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bancos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bancos` (
  `Nombre` VARCHAR(255) NOT NULL,
  `banco_id` INT NOT NULL AUTO_INCREMENT,
  `cod_Banco` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`banco_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Vendedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Vendedor` (
  `idVendedor` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `RUT` VARCHAR(45) NOT NULL,
  `tabla ventas_ID_Venta` INT NOT NULL,
  PRIMARY KEY (`idVendedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RRSS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RRSS` (
  `rrss_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `cod_rrss` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`rrss_id`));


-- -----------------------------------------------------
-- Table `mydb`.`comunas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comunas` (
  `id_comunas` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  `cod_comuna` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id_comunas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Estado_Pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Estado_Pago` (
  `estado_pago_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `cod_estado_pago` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`estado_pago_id`));


-- -----------------------------------------------------
-- Table `mydb`.`tabla ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tabla ventas` (
  `ID_Venta` INT NOT NULL AUTO_INCREMENT,
  `ID_Cliente` INT NOT NULL,
  `FEcha de compra` DATETIME NULL,
  `ID_Pack` INT NOT NULL,
  `Monto` INT NULL,
  `direccion` VARCHAR(45) NULL,
  `red social` INT NULL,
  `hora de compra` DATETIME NULL,
  `direccion` VARCHAR(45) NULL,
  `Nombre receptor` VARCHAR(45) NULL,
  `Contacto receptor` VARCHAR(45) NULL,
  `status venta` TINYINT NOT NULL,
  `banco_id` INT NOT NULL,
  `Codigo de Transaccion` VARCHAR(45) NOT NULL,
  `Estado de Orden` VARCHAR(45) NOT NULL,
  `Estado_de_Pago_id` INT NOT NULL,
  `id_vendedor` INT NOT NULL,
  `id_boleta` INT NOT NULL,
  `comuna_id` INT NULL,
  PRIMARY KEY (`ID_Venta`),
  INDEX `ID_Cliente_idx` (`ID_Cliente` ASC) VISIBLE,
  INDEX `ID_Pack_idx` (`ID_Pack` ASC) VISIBLE,
  INDEX `banco_id_idx` (`banco_id` ASC) VISIBLE,
  INDEX `id_vendedor_idx` (`id_vendedor` ASC) VISIBLE,
  INDEX `rrss_id_idx` (`red social` ASC) VISIBLE,
  INDEX `comuna_id_idx` (`comuna_id` ASC) VISIBLE,
  INDEX `fk_ventas_estados_idx` (`Estado_de_Pago_id` ASC) VISIBLE,
  CONSTRAINT `ID_Cliente`
    FOREIGN KEY (`ID_Cliente`)
    REFERENCES `mydb`.`tabla clientes` (`ID_Clientes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_Pack`
    FOREIGN KEY (`ID_Pack`)
    REFERENCES `mydb`.`Tabla Pack` (`ID_Pack`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `banco_id`
    FOREIGN KEY (`banco_id`)
    REFERENCES `mydb`.`Bancos` (`banco_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_vendedor`
    FOREIGN KEY (`id_vendedor`)
    REFERENCES `mydb`.`Vendedor` (`idVendedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rrss_id`
    FOREIGN KEY (`red social`)
    REFERENCES `mydb`.`RRSS` (`rrss_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comuna_id`
    FOREIGN KEY (`comuna_id`)
    REFERENCES `mydb`.`comunas` (`id_comunas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_estados`
    FOREIGN KEY (`Estado_de_Pago_id`)
    REFERENCES `mydb`.`Estado_Pago` (`estado_pago_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`detalle pack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`detalle pack` (
  `iddetalle pack` INT NOT NULL AUTO_INCREMENT,
  `ID_Pack` INT NULL,
  `ID_Articulo` INT NULL,
  `cantidad` INT NULL,
  PRIMARY KEY (`iddetalle pack`),
  INDEX `ID_Pack_idx` (`ID_Pack` ASC) VISIBLE,
  INDEX `ID_Articulo_idx` (`ID_Articulo` ASC) VISIBLE,
  CONSTRAINT `ID_Pack`
    FOREIGN KEY (`ID_Pack`)
    REFERENCES `mydb`.`Tabla Pack` (`ID_Pack`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_Articulo`
    FOREIGN KEY (`ID_Articulo`)
    REFERENCES `mydb`.`Tabla Articulos` (`ID_Articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pago` (
  `pago_id` INT NOT NULL AUTO_INCREMENT,
  `Monto` INT NOT NULL,
  `id_banco` INT NOT NULL,
  `fecha de pago` DATETIME NOT NULL,
  PRIMARY KEY (`pago_id`),
  INDEX `banco_id_idx` (`id_banco` ASC) VISIBLE,
  CONSTRAINT `banco_id`
    FOREIGN KEY (`id_banco`)
    REFERENCES `mydb`.`Bancos` (`banco_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`facturas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`facturas` (
  `id_factura` INT NOT NULL AUTO_INCREMENT,
  `numero_factura` INT NULL,
  `ID_Proveedor` INT NULL,
  `fecha_factura` DATETIME NULL,
  PRIMARY KEY (`id_factura`),
  INDEX `fk_facturas_proveedores_idx` (`ID_Proveedor` ASC) VISIBLE,
  CONSTRAINT `fk_facturas_proveedores`
    FOREIGN KEY (`ID_Proveedor`)
    REFERENCES `mydb`.`Tabla Proveedores` (`ID_Proveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`factura detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`factura detalle` (
  `id_factura_detalle` INT NOT NULL AUTO_INCREMENT,
  `id_factura` INT NULL,
  `id_articulo` INT NULL,
  `cantidad` INT NULL,
  `fecha_vencimiento` DATETIME NULL,
  `valor` INT NULL,
  PRIMARY KEY (`id_factura_detalle`),
  INDEX `fk_facturaDetalle_factura_idx` (`id_factura` ASC) VISIBLE,
  INDEX `fk_facturaDetalle_articulo_idx` (`id_articulo` ASC) VISIBLE,
  CONSTRAINT `fk_facturaDetalle_articulo`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `mydb`.`Tabla Articulos` (`ID_Articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_facturaDetalle_factura`
    FOREIGN KEY (`id_factura`)
    REFERENCES `mydb`.`facturas` (`id_factura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
