USE cursosql;

-- Ejercicio 1. Devolver Nombre, Apellido y Dirección de todos los clientes ordenados por Fecha de Nacimiento desde el más reciente al más antiguo.

SELECT 
	Nombre, 
    Apellido, 
    Direccion
FROM Clientes
ORDER BY FechaNacimiento DESC;

-- Ejercicio 2. Devolver Nombre, IdProveedor y Ganancia de todos los productos ordenados de mayor a menor, con el producto de mayor ganancia primero en la lista.

SELECT
	Nombre,
    IdProveedor,
    Precio-Costo AS Ganancia
FROM Productos
ORDER BY Ganancia DESC;

-- Ejercicio 3. Devolver todos los clientes y ordenarlos por nombre A-Z.
-- Si el nombre coincide, ordenar por apellido Z-A.
-- Si ambos coinciden, elegir primero el cliente de mayor edad.

SELECT *
FROM Clientes
ORDER BY Nombre ASC, Apellido DESC, FechaNacimiento ASC;