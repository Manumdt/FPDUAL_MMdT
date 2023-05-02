USE cursosql;

-- Ejercicio 1. Devolver ClienteId y Fecha de todas las Ordenes realizadas hasta Octubre del año 2017.

SELECT
	IdCliente,
    Fecha
FROM Ordenes
WHERE Fecha < '2017-10-31';

-- Ejercicio 2. Devolver Nombre, Codigo y NombreLocal renombrados como "Pais", "Abreviatura" y "Nomenclatura Local"
-- de todos los paises de la region "Polynesia".

SELECT
	Nombre AS Pais,
    Codigo AS Abreviatura,
    NombreLocal AS 'Nomenclatura Local'
FROM Paises
WHERE UPPER(Region)='POLYNESIA';

-- Ejercicio 3. Devolver en una sola columna Nombre, Apellido, y Direccion de todos los clientes de la ciudad de Bari.
-- El formato debe ser: "Nombre: nombre, Apellido: apellido, Dirección: Direccion".

SELECT CONCAT('Nombre: ', cli.Nombre, 'Apellido: ', cli.Apellido, 'Dirección: ', cli.Direccion) AS Cliente
FROM Clientes cli
JOIN Ciudades ciu ON cli.IdCiudad=ciu.Id
WHERE UPPER(ciu.Nombre) LIKE 'BARI%';