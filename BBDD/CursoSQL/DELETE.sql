USE cursosql;

-- Ejercicio 1. Eliminar todos los elementos creados en los ejercicios anteriores.

DELETE FROM Clientes
WHERE UPPER(Nombre)='JAMES' AND UPPER(Apellido)='BOND';

DELETE FROM Productos
WHERE UPPER(Nombre)='CHOCOLATE AMARGO';