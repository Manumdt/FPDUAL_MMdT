DROP DATABASE IF EXISTS dbDatos;
CREATE DATABASE dbDatos CHARSET utf8mb4;
USE dbDatos;

CREATE TABLE Datos_civiles(
	DNI VARCHAR(60) NOT NULL,
	Nombre VARCHAR(60) NOT NULL,
    Edad INT NOT NULL,
    Direccion VARCHAR(255) NOT NULL,
    EstadoCivil VARCHAR(60) NOT NULL DEFAULT 'Soltero',
    PRIMARY KEY(DNI)
);

CREATE TABLE Datos_sensibles(
	DNI VARCHAR(60) NOT NULL,
	Nombre VARCHAR(60) NOT NULL,
    EstadoCivil VARCHAR(60) NOT NULL,
    FechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(DNI)
);

DELIMITER //
CREATE TRIGGER DatosSensibles
AFTER INSERT ON Datos_civiles
FOR EACH ROW
BEGIN 
INSERT INTO Datos_sensibles VALUES (NEW.DNI, NEW.Nombre, NEW.EstadoCivil, NOW());
END//
DELIMITER ;

INSERT INTO Datos_civiles VALUES ('03960905Y', 'Manuel', '22', 'Calle Plasencia 8', 'No Casado'),
('11223344A', 'Nombre01', '01', 'Dirección01', DEFAULT),
('22334455B', 'Nombre02', '02', 'Dirección02', DEFAULT),
('33445566C', 'Nombre03', '03', 'Dirección03', DEFAULT),
('44556677D', 'Nombre04', '04', 'Dirección04', DEFAULT);


SELECT * FROM Datos_civiles;
SELECT * FROM Datos_sensibles;

