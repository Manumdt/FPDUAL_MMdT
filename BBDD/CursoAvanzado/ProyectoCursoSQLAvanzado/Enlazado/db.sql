DROP DATABASE IF EXISTS pflogin;
CREATE DATABASE pflogin CHARSET utf8mb4;
USE pflogin;

CREATE TABLE usuarios(
	IdUsuario INT AUTO_INCREMENT NOT NULL,
    NombreUsuario VARCHAR(45) NULL,
    Correo VARCHAR(45) NULL,
    Contrasena VARCHAR(45) NULL,
    PRIMARY KEY (IdUsuario)
);

SELECT * from usuarios;