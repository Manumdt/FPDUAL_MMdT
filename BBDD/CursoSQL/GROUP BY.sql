USE cursosql;

-- Ejercicio 1. Devolver solo los países de la tabla Ciudades.

SELECT CodigoPais
FROM Ciudades
GROUP BY CodigoPais;