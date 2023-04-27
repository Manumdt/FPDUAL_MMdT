-- El usuario creará una base de datos que contenga una tabla.

DROP DATABASE IF EXISTS ejercicio01;
CREATE DATABASE ejercicio01 CHARSET utf8mb4;
USE ejercicio01;

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

-- El estudiante deberá hacer una consulta la cual muestre los registros que empiecen con la letra F.

SELECT *
FROM estudiantes
WHERE UPPER(Nombre) LIKE 'F%';

-- El estudiante deberá hacer otra consulta la cual muestre los registros que sean de género Masculino.

SELECT *
FROM estudiantes
WHERE UPPER(Genero) = 'MASCULINO';