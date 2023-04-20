USE cursosql;

-- Ejercicio 1. Devolver Clientes cuyos apellidos sean: Myers, Lewis, Kent, Case o Berger.

SELECT *
FROM Clientes
WHERE Apellido IN ('Myers', 'Lewis', 'Kent', 'Case', 'Berger');

-- Ejercicio 2. Devolver Codigo, Nombre, Continente y Region de todos los países de America.

SELECT 
	Codigo,
    Nombre,
    Continente,
    Region
FROM Paises
WHERE UPPER(Continente) IN('NORTH AMERICA', 'SOUTH AMERICA');

-- Ejercicio 3. Devolver todas las ciudades de los países hispanoparlantes.

SELECT *
FROM Ciudades
WHERE UPPER(CodigoPais) IN ('ABW', 'AND', 'ARG', 'BLZ', 'BOL', 'CAN', 'CHL', 'COL', 'CRI', 'CUB', 'DOM', 'ECU', 'ESP');