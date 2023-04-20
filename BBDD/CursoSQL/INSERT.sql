USE cursosql;

-- Ejercicio 1. Insertar el cliente James Bond, de Amsterdam que haya nacido el 26 de mayo del 83.

INSERT INTO Clientes(Nombre, Apellido, FechaNacimiento, IdCiudad)
VALUES('James',
		'Bond',
        '1983-05-26',
        (SELECT Id 
			FROM Ciudades 
			WHERE UPPER(Nombre)='AMSTERDAM'));
                                    
SELECT * FROM Clientes;

-- Ejercico 2. Insertar un nuevo producto de la categoria Respostería llamado Chocolate amargo.
-- El producto pertenece a un nuevo proveedor "Fabrica de Chocolate".
-- Su costo es 6 y su precio de venta 15.

INSERT INTO Proveedores
VALUES(30, "Fabrica de Chocolate");

INSERT INTO Productos
VALUES (80, "Chocolate amargo", 30,6,15,7);