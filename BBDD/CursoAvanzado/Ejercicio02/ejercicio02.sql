-- El usuario creará una base de datos que contenga una tabla.

DROP DATABASE IF EXISTS ejercicio02;
CREATE DATABASE ejercicio02 CHARSET utf8mb4;
USE ejercicio02;

CREATE TABLE estudiantes(
	IdEstudiante INT UNSIGNED AUTO_INCREMENT,
    Nombre VARCHAR(60) NOT NULL,
    Edad INT UNSIGNED NOT NULL,
    Genero VARCHAR(60) NOT NULL,
    Telefono VARCHAR(60) NOT NULL,
    Direccion VARCHAR(255) NOT NULL,
	PRIMARY KEY(IdEstudiante)
);

-- Ingresará 6 registros dentro de la tabla y esta constará de los siguientes atributos:

-- Nombre.
-- Edad.
-- Género.

INSERT INTO estudiantes (Nombre, Edad, Genero, Telefono, Direccion) VALUES('Manuel',22,'Masculino','640669232','Calle Plasencia'),
('Fernando',18,'Masculino','666777888','Calle Atapulco'),
('Fátima',23,'Femenino','777888999','Calle Resonancia'),
('Efesto',28,'Masculino','111222333','Calle Herrería'),
('Faustino',32,'Masculino','222333444','Calle Melancolia'),
('Camelia',26,'Femenino','333444555','Calle Amapola');

-- El usuario deberá hacer una rutina almacenada la cual muestre todos los registros.

USE ejercicio02;

DROP PROCEDURE IF EXISTS consulta_registros;
DELIMITER $$
CREATE PROCEDURE consulta_registros()
BEGIN 
	SELECT * 
    FROM ejercicio02.estudiantes;
END$$
DELIMITER ;

 CALL ejercicio02.consulta_registros();

-- El usuario deberá hacer una rutina almacenada la cual muestre los registros de edad mayor a 30.

DROP PROCEDURE IF EXISTS consulta_mayor_30;

DELIMITER $$
CREATE PROCEDURE consulta_mayor_30()
BEGIN
	SELECT *
    FROM estudiantes
    WHERE edad>30;
END$$
DELIMITER ;

CALL ejercicio02.consulta_mayor_30();