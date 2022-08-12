/*CREATE DATABASE SISTEMA;*/
USE SISTEMA;
 /* TABLE PROVEEDOR */
CREATE TABLE PROVEEDOR (
	rucPRV varchar (10) not null primary key,
    nomPRV varchar (15) not null,
    apelliPRV varchar (15) not null,
    telfPRV numeric (10) not null
);

/* TABLE CATEGORIAS */
CREATE TABLE CATEGORIA (
	codCTG varchar (10) not null primary key,
    descCTG varchar (25) not null
);

 /*TABLE PRODUCTOS */
 CREATE TABLE PRODUCTO (
	codPRO varchar (10) not null primary key,
    descPRO varchar (50) not null, 
    stockPRO numeric (4) not null,
    pvpPRO numeric (4,2) not null,
    codCTG_FK varchar (10) not null,
    rucPRV_FK varchar (10) not null,
	FOREIGN KEY (codCTG_FK) REFERENCES CATEGORIA (codCTG)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (rucPRV_FK) REFERENCES PROVEEDOR (rucPRV)
    ON DELETE CASCADE
    ON UPDATE CASCADE
 
 );
 
 /*TABLE USUARIO*/
 
 CREATE TABLE usuarios(
	passwordUSR varchar (10) not null primary key,
    nomUSR varchar (25) not null
 );
 
 
 INSERT INTO usuarios
 VALUE ('admin', 'admin');