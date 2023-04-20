USE cursosql;

-- Ejercicio 1. Devolver todos los Porductos cuyo precio sea mayor que el costo incrementado en un 40% o la ganancia sea de al menos 25.

SELECT *
FROM Productos
WHERE Precio>(Costo*1.40) OR Precio-Costo>=25;

-- Ejercicio 2. Devolver todas las ciudades argentinas y todas las ciudades brasileñas excepto aquellas que pertenezcan a los distritos de Buenos Aires y Recife.

SELECT *
FROM Ciudades
WHERE 
	UPPER(CodigoPais)='ARG' AND UPPER(Distrito)!='BUENOS AIRES' 
    OR UPPER(CodigoPais)='BRA' AND UPPER(Distrito)!='RECIFE';
    
-- Ejercicio 3. Devolver solo Ordenes realizadas antes de febrero de 2018 que hayan vendido más de 100 productos u ordenes realizadas después de julio de 2018 que no hayan vendido más de 50 productos.

SELECT *
FROM Ordenes
WHERE Fecha<'2018-02-28' AND Cantidad>100 
	OR Fecha>'2018-07-31' AND Cantidad < 50;