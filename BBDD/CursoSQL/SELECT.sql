USE cursosql;

-- Ejercicio 1. Devolver Id y Nombre de todas las categorias y renombrarlas como Numero y Categoria.

SELECT 
	Id AS Numero, 
    Nombre AS Categoria
FROM Categorias;

-- Ejercicio 2. Devolver Apellido, Nombre y Fecha de Nacimiento de todos los clientes del sistema.
-- Apellido y Nombre en una única columna llamada Nombre Completo separados sus valores por coma.

SELECT 
	CONCAT(Apellido, ' ' , Nombre) 
    AS NombreCompleto, FechaNacimiento
FROM Clientes;

-- Ejercicio 3. Devolver Nombre, Costo y Precio.
-- Costo con descuento del 30%.
-- Ganancia si el costo tiene un descuento del 30%.
-- Ganancia con costo regular.
-- Ganancia con costo regular y un 10% de descuento.

SELECT 
	Nombre, 
	Costo, 
	Precio,
    Costo*0.70 AS Costo30,
    Precio-Costo*0.70 AS Ganancia30,
    Precio-Costo AS GananciaRegular,
    (Precio-Costo)*0.90 AS Ganancia10
FROM Productos;