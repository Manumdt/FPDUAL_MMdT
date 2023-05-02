-- El usuario creará una base de datos que contenga dos tablas.

DROP DATABASE IF EXISTS ejercicio03;
CREATE DATABASE ejercicio03 CHARSET utf8mb4;
USE ejercicio03;

-- La primera tabla tendrá los datos de mascotas y serán los siguientes:
-- Nombre.
-- IdMascota.
-- NombreDueño.

CREATE TABLE mascotas(
	IdMascota INT UNSIGNED AUTO_INCREMENT,
    Nombre VARCHAR(60) NOT NULL,
    NombreDueño VARCHAR(60) NOT NULL,
	PRIMARY KEY(IdMascota,NombreDueño)
);

-- La segunda tabla contendrá los datos de los dueños y serán los siguientes:
-- Se deberá realcionar las dos tablas mediante una llave foranea.

CREATE TABLE dueños(
	IdMascota INT UNSIGNED,
    Nombre VARCHAR(60) NOT NULL,
    Edad INT UNSIGNED,
    PRIMARY KEY(IdMascota,Nombre),
    CONSTRAINT IdMascota_dueños_fk FOREIGN KEY (IdMascota) REFERENCES mascotas(IdMascota)
);

-- Se deberán guardar 5 registros de animales.

INSERT INTO mascotas (Nombre,NombreDueño) VALUES('Halley','Manuel'),
('Yin','Dani'),
('Hestia','Manuel'),
('Hera','Ana'),
('Bulma','José');

-- Llamar a las dos tablas unidas.

SELECT * FROM 