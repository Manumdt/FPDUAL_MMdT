USE cursosql;

-- Ejercicio 1. Determinar cual es la categoria más exitosa calculando el total vendido por categoria

SELECT
	ctg.Id AS IdCategoria,
	ctg.Nombre AS Categoria,
    SUM(prd.Precio+ord.Cantidad) AS TotalVendido
FROM Categorias ctg
JOIN Productos prd ON ctg.Id=prd.IdCategoria
JOIN Ordenes ord ON prd.Id=ord.IdProducto
GROUP BY 1,2
ORDER BY 3 DESC;

-- Ejercicio 2. Determinar cual es el proveedor más costoso del sistema calculando el total por proveedor

SELECT
	prv.Id AS IdProveedor,
    prv.Nombre AS Proveedor,
    SUM(prd.Costo) AS CostoTotal
FROM Proveedores prv
JOIN Productos prd ON prv.Id=prd.IdProveedor
JOIN Ordenes ord ON prd.Id=ord.IdProducto
GROUP BY 1,2
ORDER BY 3 DESC;

-- Ejercicio 3. Mostrar el total consumido por idioma de cliente ordenado de mayor a menor.

SELECT
	idi.IdiomaPais,
    SUM(prd.Precio*ord.Cantidad) AS ConsumoTotal
FROM Idiomapaises idi
JOIN Paises pais ON idi.CodigoPais=pais.Codigo
JOIN Ciudades ciu ON pais.Codigo=ciu.CodigoPais
JOIN Clientes cli ON ciu.Id=cli.IdCiudad
JOIN Ordenes ord ON cli.Id=ord.IdCliente
JOIN Productos prd ON ord.IdProducto=prd.Id
GROUP BY 1
ORDER BY 2 DESC;