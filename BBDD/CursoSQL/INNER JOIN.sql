USE cursosql;

-- Ejercicio 1. Devolver nombre de Proveedores, Precio y Costo de productos cuya ganancia sea mayor a 20.

SELECT 
	prv.Nombre,
    prd.Precio,
    prd.Costo
FROM Proveedores prv
JOIN Productos prd ON prv.Id=prd.IdProveedor
WHERE Precio-Costo > 20;

-- Ejercicio 2. Devolver Fecha, Apellido y Nombre de Cliente y nombre de producto de todos los clientes que hayan comprado productos entre 2016 y 2019 inclusive.
-- Ordenando primero por más reciente y segundo por apellido A-Z.

SELECT 
	cli.FechaNacimiento,
    cli.Apellido,
    cli.Nombre,
    prd.Nombre
FROM Clientes cli
JOIN Ordenes ord ON cli.Id = ord.IdCliente
JOIN Productos prd ON ord.IdProducto = prd.Id
WHERE ord.Fecha BETWEEN '2016-01-01' AND '2019-12-31'
ORDER BY ord.Fecha DESC, cli.Apellido ASC;

-- Ejercicio 3. A la consulta anterior, agregar además el país al cual pertenece cada cliente.

SELECT 
	cli.FechaNacimiento,
    cli.Apellido,
    cli.Nombre,
    prd.Nombre,
    pais.Nombre
FROM Clientes cli
JOIN Ordenes ord ON cli.Id = ord.IdCliente
JOIN Productos prd ON ord.IdProducto = prd.Id
JOIN Ciudades ciu ON cli.IdCiudad = ciu.Id
JOIN Paises pais ON ciu.CodigoPais = pais.Codigo
WHERE ord.Fecha BETWEEN '2016-01-01' AND '2019-12-31'
ORDER BY ord.Fecha DESC, cli.Apellido ASC;