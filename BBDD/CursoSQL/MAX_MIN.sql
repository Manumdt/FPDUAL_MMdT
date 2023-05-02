USE cursosql;

-- Ejercicio 1. Encontrar al cliente más joven que alguna haya realizado una compra

SELECT
	cli.Id,
    cli.Nombre,
    cli.FechaNacimiento
FROM Clientes cli
JOIN Ordenes ord ON cli.Id=ord.IdCliente
GROUP BY ord.IdCliente
ORDER BY MAX(cli.FechaNacimiento) DESC;

-- Ejercico 2. Determinar el producto de menor costo en cada cateogría que se haya vendido al menos una vez.

SELECT
	ctg.Id,
    ctg.Nombre,
    MIN(prd.Costo) AS MenorCosto
FROM Productos prd
JOIN Categorias ctg ON prd.IdCategoria=ctg.Id
GROUP BY 1,2
ORDER BY MIN(prd.Costo)ASC;

-- Ejercicio 3. Determinar cual es el país al cual se realizaron la mayor y la menor venta, ordenando los resultados.

SELECT
	pais.Codigo,
    pais.Nombre,
    MAX(prd.Precio*ord.Cantidad) AS TotalVendido
FROM Paises pais
JOIN Ciudades ciu ON pais.Codigo=ciu.CodigoPais
JOIN Clientes cli ON ciu.Id=cli.IdCiudad
JOIN Ordenes ord ON cli.Id=ord.IdCliente
JOIN Productos prd ON ord.IdProducto=prd.Id
GROUP BY 1,2
ORDER BY 3 DESC
LIMIT 1