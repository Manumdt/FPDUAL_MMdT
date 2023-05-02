USE cursosql;

-- Ejercicio 1. Devolver Id, Nombre, Costo y Precio de todos los productos relacionados con Queso, siempre y cuando su costo no sea menor a 10 ni mayor a 30

SELECT
	Id,
    Nombre,
    Costo,
    Precio
FROM Productos
WHERE UPPER(Nombre) LIKE '%QUESO%'
AND Costo BETWEEN '10' AND '30';

-- Ejercicio 2. Devolver todos los clientes cuyo nombre tenga como segunda letra, la letra "a" y termine con la letra e.

SELECT *
FROM Clientes
WHERE UPPER(Nombre) LIKE '_a%e';

-- Ejercicio 3. Devolver todos los clientes cuyo apellido no tenga la letra 'r' en la tercera posición y su penúltima posición sea la letra 'e'.

SELECT *
FROM Clientes
WHERE UPPER(Nombre) NOT LIKE '__R%'
AND UPPER(NOMBRE) LIKE '%e_';