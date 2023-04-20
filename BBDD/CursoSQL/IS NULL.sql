USE cursosql;

-- Ejercicio 1. Devolver todos los productos que tengan proveedor

SELECT *
FROM Productos
WHERE IdProveedor IS NOT NULL;

-- Ejercicio 2. Devolver todos los clientes que no tengan teléfono.

SELECT *
FROM Clientes
WHERE Telefono IS NULL;