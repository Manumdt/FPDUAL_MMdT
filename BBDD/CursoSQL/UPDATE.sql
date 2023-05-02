USE cursosql;

-- Ejercicio 1. Actualizar la Dirección de James Bond a: "Una base secreta".

SET SQL_SAFE_UPDATES = 0;

UPDATE Clientes
SET Direccion= 'Una base secreta'
WHERE UPPER(Nombre)='JAMES' AND UPPER(Apellido)='BOND';

-- Ejercicio 2. Cambiar la categoria del producto Chocolate amargo de repostería a lácteos.

UPDATE Productos
SET IdCategoria=6
WHERE UPPER(Nombre)='CHOCOLATE AMARGO';