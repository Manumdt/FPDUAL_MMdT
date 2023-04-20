USE cursosql;

-- Ejercicio 1. Devolver Clientes nacidos entre 1950 y 1980 y clientes nacidos entre 1990 y 2010.

SELECT *
FROM Clientes
WHERE FechaNacimiento BETWEEN '1950-01-01' AND '1980-12-31'
OR FechaNacimiento BETWEEN '1990-01-01' AND '2010-12-31';

-- Ejercicio 2. Devolver Ordenes realizadas:
-- antes de enero del 2016
-- entre marzo y noviembre del 2017 o marzo y noviembre del 2018
-- después de enero del 2019

SELECT *
FROM Ordenes
WHERE Fecha < '2016-01-01'
	OR Fecha BETWEEN '2017-03-01' AND '2017-11-30'
    OR Fecha BETWEEN '2018-03-01' AND '2018-11-30'
    OR Fecha > '2019-01-01';
    
-- Ejercicio 3. Devolver ID, Nombre, Apellido y Direccion de los clientes con id mayores a 80 pero menores a 125 excepto por los clientes 99 y 100
-- Además, devolver los clients con Id 13, 17, 19, 28 y 56.

SELECT 
	Id,
    Nombre,
    Apellido,
    Direccion
FROM Clientes
WHERE Id BETWEEN '80' AND '125' 
	AND Id NOT IN ('99','100')
    OR Id IN ('13', '17', '19', '28', '56');
