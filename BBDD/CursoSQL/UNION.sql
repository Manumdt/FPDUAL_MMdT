USE cursosql;

-- Ejercicio 1. Devolver en un único listado:
-- Id y nombre de productos cuyo costo sea mayor a 80 pero menor a 100.
-- Id y nombre de Categorias que no cominencen con la letra C.
-- Id y nombre de Proveedores cuya segunda letra no sea "e" ni su última letra sea "n".

SELECT
	Id,
    Nombre
FROM Productos
WHERE Costo BETWEEN 80 AND 100

UNION ALL

SELECT
	Id,
    Nombre
FROM Categorias
WHERE UPPER(Nombre) NOT LIKE 'C%'

UNION ALL

SELECT
	Id,
    Nombre
FROM Proveedores
WHERE UPPER(Nombre) NOT LIKE '_E%N';

-- Ejercicio 2. Devolver nombre de producto, apellido y nombre como Cliente, Fecha de orden
-- y texto "mayorista" para aquellas ordenes con pedidos mayores a 50
-- y texto "minorista" para aquellas ordenes con pedidos menores a 50.

SELECT 
	"Mayorista",
	prd.Nombre AS NombreProducto,
    CONCAT(cli.Apellido, ' ', cli.Nombre) AS Cliente,
    ord.Fecha
FROM Productos prd
JOIN Ordenes ord ON prd.Id=ord.IdProducto
JOIN Clientes cli ON ord.IdCliente=cli.Id
WHERE prd.Costo > 50

UNION ALL

SELECT 
	"Minorista",
	prd.Nombre AS NombreProducto,
    CONCAT(cli.Apellido, ' ', cli.Nombre) AS Cliente,
    ord.Fecha
FROM Productos prd
JOIN Ordenes ord ON prd.Id=ord.IdProducto
JOIN Clientes cli ON ord.IdCliente=cli.Id
WHERE prd.Costo < 50;

-- Ejercicio 3. Devolver un solo listado de productos, precio y nombre de categoria con precios actualizados:
-- Descuento del 10% para bebidas, aumento del 15% para carnes y agregar un impuesto fijo de 13.5 para lacteos.

SELECT
	prd.Nombre,
    prd.Precio*0.90 AS Precio,
    ctg.Nombre
FROM Productos prd
JOIN Categorias ctg ON prd.IdCategoria=ctg.Id
WHERE UPPER(ctg.Nombre)='BEBIDAS'

UNION ALL

SELECT 
	prd.Nombre,
    prd.Precio*1.15 AS Precio,
    ctg.Nombre
FROM Productos prd
JOIN Categorias ctg ON prd.IdCategoria=ctg.Id
WHERE UPPER(ctg.Nombre)='CARNES'

UNION ALL

SELECT 
	prd.Nombre,
    prd.Precio+13.5 AS Precio,
    ctg.Nombre
FROM Productos prd
JOIN Categorias ctg ON prd.IdCategoria=ctg.Id
WHERE UPPER(ctg.Nombre)='LACTEOS'