USE dbpokemon;

-- 1. Mostrar el nombre de todos los pokemon. 151 Registros.

SELECT
	NumeroPokedex,
	Nombre
FROM pokemon
ORDER BY NumeroPokedex ASC;

-- 2. Mostrar los pokemon que pesen menos de 10kg.

SELECT
	NumeroPokedex,
    Nombre,
    Peso
FROM pokemon
WHERE Peso<10;

-- 3. Contar todos los pokemons que miden mayor o igual a 0.5.

SELECT
	COUNT(NumeroPokedex) AS TotalPokemon
FROM pokemon
WHERE Altura >=0.5;

-- 4. Muestra el nombre de los movimientos cuya precision es mayor que 75 y menor o igual que 100 ordenalo por preciosión de mayor a menor.

SELECT
	*
FROM movimiento
WHERE PrecisionMov BETWEEN 76 AND 100
ORDER BY 2 DESC;

-- 5. Mostrar el Pokemon más pesado:

SELECT
	NumeroPokedex,
    Nombre,
    Peso
FROM pokemon
ORDER BY Peso DESC
LIMIT 1;

-- 6. Mostrar todos los pokemon del tipo fuego

SELECT
	pok.NumeroPokedex,
	pok.Nombre
FROM pokemon pok
JOIN pokemon_tipo tip ON pok.NumeroPokedex=tip.NumeroPokedex
WHERE tip.IdTipo=(SELECT IdTipo
				  FROM tipo
				  WHERE UPPER(Nombre)='FUEGO');

-- 7. Cuenta los dragones cuyo tipo es 'Dragón'

SELECT
	COUNT(pok.NumeroPokedex) AS Dragones
FROM pokemon pok
JOIN pokemon_tipo tip ON pok.NumeroPokedex=tip.NumeroPokedex
WHERE tip.IdTipo=(SELECT IdTipo
				  FROM tipo
				  WHERE UPPER(Nombre)='DRAGON');

-- 8. Muestra todos los movimientos donde la descripción comienza con Causa

SELECT
	IdMovimiento,
    Nombre,
    Descripcion
FROM Movimiento
WHERE UPPER(Descripcion) LIKE 'CAUSA%';

-- 9. Muestra los pokemons cuyo tipo de ataque es 'Especial'.

SELECT
	pok.NumeroPokedex,
    pok.Nombre
FROM Pokemon pok
JOIN pokemon_tipo poti ON pok.NumeroPokedex=poti.NumeroPokedex
JOIN tipo tip ON poti.IdTipo=tip.IdTipo
WHERE IdTipoAtaque=(SELECT IdTipoAtaque
					FROM tipo_ataque
					WHERE UPPER(Tipo)='ESPECIAL');

-- 10. Mostrar los pokemon que son de tipo fuego y volador.

SELECT
	pok.NumeroPokedex,
    pok.Nombre
FROM Pokemon pok
WHERE pok.NumeroPokedex IN (SELECT NumeroPokedex
					FROM pokemon_tipo poti
                    JOIN tipo tip ON poti.IdTipo=tip.IdTipo
                    WHERE UPPER(tip.Nombre)='FUEGO')
AND pok.NumeroPokedex IN (SELECT NumeroPokedex
					FROM pokemon_tipo poti
                    JOIN tipo tip ON poti.IdTipo=tip.IdTipo
                    WHERE UPPER(tip.Nombre)='VOLADOR');