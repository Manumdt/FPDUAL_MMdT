USE cursosql;

-- Ejercico 1. Devolver Nombre y Apellido de Clientes que:
-- 1. No hayan realizado ninguna orden.
-- 2. Hayan realizado alguna orden fuera del año 2018.

SELECT 
	cli.Nombre,
    cli.Apellido
FROM Clientes cli
LEFT JOIN Ordenes ord ON cli.Id=ord.IdCliente
WHERE ord.Id IS NULL
OR ord.Fecha NOT BETWEEN '2018-01-01' AND '2018-12-31';

-- Ejercicio 2. Devolver Id y Nombre de todos los productos que nunca se han vendido

SELECT
	prd.Id,
	prd.Nombre
FROM Productos prd
LEFT JOIN Ordenes ord ON prd.Id=ord.IdProducto
WHERE ord.Id IS NULL;

-- Ejercicio 3. Devolver Nombre y Codigo de países que tengan clientes pero nunca hayan participado en una transacción.alter

SELECT
	pais.Nombre,
    pais.Codigo
FROM Paises pais
JOIN Ciudades ciu ON pais.Codigo=ciu.CodigoPais
JOIN Clientes cli ON ciu.Id=cli.IdCiudad
JOIN Ordenes ord ON cli.Id=ord.IdCliente
WHERE ord.Id IS NULL;