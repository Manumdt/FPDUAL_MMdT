DROP DATABASE IF EXISTS comercio;
CREATE DATABASE comercio CHARSET utf8mb4;
USE comercio;

CREATE TABLE usuarios(
	Id INT UNSIGNED AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Edad INT,
    Ciudad VARCHAR(50),
    Pais VARCHAR(50),
    PRIMARY KEY (Id)
);

CREATE TABLE compras(
	Id INT UNSIGNED AUTO_INCREMENT,
    IdUsuario INT UNSIGNED,
    Producto VARCHAR(50),
    Precio FLOAT(6,2),
    Fecha DATE,
    PRIMARY KEY (Id),
    CONSTRAINT IdUsuario_compras_fk FOREIGN KEY (IdUsuario) REFERENCES usuarios(Id)
);

-- 2. Insertar datos en la tabla usuario

INSERT INTO usuarios (Nombre, Edad, Ciudad, Pais) VALUES
('Juan',25,'Madrid','España'),
('Sofía',22,'Santiago','Chile'),
('Juan',25,'Cáceres','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Cáceres','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Madrid','España'),
('Sofía',22,'Santiago','Chile'),
('Juan',25,'Cáceres','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Cáceres','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Madrid','España'),
('Sofía',22,'Santiago','Chile'),
('Juan',25,'Cáceres','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Cáceres','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Madrid','España'),
('Sofía',22,'Santiago','Chile'),
('Juan',25,'Cáceres','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Madrid','España'),
('Juan',25,'Cáceres','España'),
('Juan',25,'Madrid','España');

-- 3. Actualizar ciudad a Barcelona al Id 4.

UPDATE usuarios
SET ciudad='Barcelona'
WHERE Id=4;

SELECT * FROM usuarios;

-- 4. Siendo Juan el Id 5 como lo eliminarias de la tabla usuarios.

DELETE FROM usuarios
WHERE Id=5;

SELECT * FROM usuarios;

-- 5. Muestra todos los registros cuyo país sea España

SELECT * FROM usuarios
WHERE UPPER(Pais)='ESPAÑA';

-- 6. Cuenta todos los registros cuya ciudad sea Cáceres.

SELECT COUNT(*) AS TotalCáceres
FROM usuarios
WHERE UPPER(Ciudad)='CÁCERES';

-- 7.Obtén la suma de todos los productos que ha comprado Juan.alter

INSERT INTO compras (IdUsuario ,Producto, Precio, Fecha) VALUES
('1','Ordenador', 305.33, '2023-02-03'),
('1','Ratón', 79.99, '2023-02-03'),
('1','Auriculares', 95.77, '2023-02-03'),
('1','Teclado', 55.18, '2023-02-03'),
('1','Monitor', 230.25, '2023-02-03');

SELECT SUM(c.Precio) AS TotalGastado
FROM compras c
JOIN usuarios u ON c.IdUsuario = u.Id
WHERE UPPER(u.Nombre)='JUAN';

-- 8. Muestra los datos que ha realizado Juan ordenadas por precio siendo el Id 25.

INSERT INTO compras (IdUsuario ,Producto, Precio, Fecha) VALUES
('25','Ordenador', 305.33, '2023-02-03'),
('25','Ratón', 79.99, '2023-02-03'),
('25','Auriculares', 95.77, '2023-02-03'),
('25','Teclado', 55.18, '2023-02-03'),
('25','Monitor', 230.25, '2023-02-03');

SELECT u.Nombre, c.*
FROM usuarios u
JOIN compras c ON u.Id = c.IdUsuario
WHERE u.Id=25
ORDER BY c.Precio DESC;

-- 9. Muestra todas las compras comprendidas entre el 2023-01-01 al 2023-05-01 mostrando también el nombre.alter

INSERT INTO compras (IdUsuario ,Producto, Precio, Fecha) VALUES
('2','Ordenador', 305.33, '2023-02-03'),
('2','Ratón', 79.99, '2023-02-03'),
('2','Auriculares', 95.77, '2023-02-03'),
('2','Teclado', 55.18, '2023-02-03'),
('2','Monitor', 230.25, '2023-02-03');

SELECT u.Nombre, c.*
FROM usuarios u
JOIN compras c ON u.Id = c.IdUsuario
WHERE c.Fecha BETWEEN '2023-01-01' AND '2023-05-01';