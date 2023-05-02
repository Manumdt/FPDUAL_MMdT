USE cursosql;

-- Ejercicio 1. Devolver solo aquellos proveedores en donde el precio promedio de sus productos supere el valor de su producto más caro dividido por dos.
-- El proveedor debe tener un producto que se haya vendido al menos una vez.

SELECT
	prv.Id,
    prv.Nombre,
    AVG(prd.Precio) AS PrecioMedio,
    MAX(prd.Precio/2) AS 'ProductoMasCaro/2'
FROM Proveedores prv
JOIN Productos prd ON prv.Id=prd.IdProveedor
JOIN Ordenes ord ON prd.Id=ord.IdProducto
GROUP BY 1,2
HAVING AVG(prd.Precio) > MAX(prd.Precio/2);

-- Ejericico 2. Mostrar el total y el promoedio consumido por idioma de cliente ordenado de mayor a menor
-- sólo para los idiomas en donde la mitad de lo consumido es mayor al promedio total consumido.

SELECT
	SUM(prd.Precio*ord.Cantidad) AS TotalConsumido,
    AVG(prd.Precio*ord.Cantidad) AS PromedioConsumido
FROM Productos prd
JOIN Ordenes ord ON prd.Id=ord.IdProducto
JOIN Clientes cli ON ord.IdCliente=cli.Id
JOIN Ciudades ciu ON cli.IdCiudad=ciu.Id
JOIN Paises pais ON ciu.CodigoPais=pais.Codigo
JOIN IdiomaPaises idi ON pais.Codigo=idi.CodigoPais
GROUP BY idi.IdiomaPais
HAVING SUM(prd.Precio*ord.Cantidad)/2 > AVG(prd.Precio*ord.Cantidad)
ORDER BY 1 DESC;
