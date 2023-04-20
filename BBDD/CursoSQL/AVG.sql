USE cursosql;

-- Ejercicio 1. Determinar el promedio vendido por ciudad ordenados de mayor a menor.

SELECT 
	ciu.Id,
    ciu.Nombre,
    AVG(prd.Precio*ord.Cantidad) AS PromedioVendido
FROM Ciudades ciu
JOIN Clientes cli ON ciu.Id=cli.IdCiudad
JOIN Ordenes ord ON cli.Id=ord.IdCliente
JOIN Productos prd ON ord.IdProducto=prd.Id
GROUP BY 1,2
ORDER BY 3 DESC;

-- Ejercicio 2. Determinar el promedio vendido a clientes nacidos entre 1930 y 1970.

SELECT
	cli.Id,
    CONCAT(cli.Nombre, ' ', cli.Apellido) AS Cliente,
    cli.FechaNacimiento,
    AVG(prd.Precio*ord.Cantidad) AS PromedioVendido
FROM Clientes cli
JOIN Ordenes ord ON cli.Id=ord.IdCliente
JOIN Productos prd ON ord.IdProducto=prd.Id
WHERE cli.FechaNacimiento BETWEEN '1930-01-01' AND '1970-12-31'
GROUP BY 1,2
ORDER BY 4 DESC;

-- Ejercicio 3. Determinar el promedio invertido por proveedor para productos con al menos una venta

SELECT
	prv.Id,
    prv.Nombre,
    AVG(prd.Costo) AS PromedioInvertido
FROM Proveedores prv
JOIN Productos prd ON prv.Id=prd.IdProveedor
JOIN Ordenes ord ON prd.Id=ord.IdProducto
GROUP BY 1,2
ORDER BY 3 DESC;