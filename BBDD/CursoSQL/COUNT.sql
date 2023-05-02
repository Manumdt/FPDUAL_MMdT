USE cursosql;

-- Ejercicio 1. Devolver la cantidad de productos vendidos por categoria

SELECT
	COUNT(prd.IdCategoria) AS ProductosVendidos,
    ctg.Nombre
FROM Productos prd
JOIN Categorias ctg ON prd.IdCategoria=ctg.Id
GROUP BY prd.IdCategoria;

-- Ejercicio 2. Devolver la cantidad de clientes que alguna vez ordenaron algo por pais.

SELECT 
	pais.Codigo,
	pais.Nombre,
	COUNT(ord.IdCliente) AS NumeroClientes
FROM Clientes cli
JOIN Ordenes ord ON cli.Id=ord.IdCliente
JOIN Ciudades ciu ON cli.IdCiudad=ciu.Id
JOIN Paises pais ON ciu.CodigoPais=pais.Codigo
GROUP BY 1,2
ORDER BY 3 DESC;

-- Ejercicio 3. Devolver la cantidad de productos cuya ganancia está entre 5 y 20, y su proveedor no está vacío.

SELECT
	COUNT(*) AS NumeroProductos
FROM Productos prd
JOIN Proveedores prv ON prd.IdProveedor=prv.Id
WHERE  prd.Precio-prd.Costo BETWEEN 5 AND 20
AND prv.Id IS NOT NULL
ORDER BY 1 DESC;