create database UserDB;
use UserDB;
grant all on UserDB.* to 'admin'@'localhost' identified by 'test'; 

CREATE TABLE UserDB.`cliente` (
    `clienteId` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(45) NOT NULL,
    `apellido` varchar(45) NOT NULL,
    `fechaCreacionCliente` date NOT NULL,
    `email` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`clienteId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


CREATE TABLE UserDB.`cuenta` (
    `numeroDeCuenta` int(11) NOT NULL AUTO_INCREMENT,
    `saldo` double(10, 2) NOT NULL,
    `fechaCreacionCuenta` date NOT NULL,
    `clienteCuentaId` int(11) NOT NULL,
    PRIMARY KEY (`numeroDeCuenta`),
    FOREIGN KEY (clienteCuentaId) REFERENCES cliente(clienteId)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
