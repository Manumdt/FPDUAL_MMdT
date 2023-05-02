USE dbpokemon;

-- 1. Mostrar todos los pokemon que evolucionan por piedra. 

SELECT DISTINCT
	pok.NumeroPokedex,
    pok.Nombre
FROM pokemon pok
JOIN pokemon_forma_evolucion pfe ON pok.NumeroPokedex=pfe.NumeroPokedex
JOIN forma_evolucion fev ON pfe.IdFormaEvolucion=fev.IdFormaEvolucion
WHERE UPPER(fev.IdTipoEvolucion)= (SELECT IdTipoEvolucion
									FROM tipo_evolucion
                                    WHERE UPPER(TipoEvolucion)='PIEDRA'); 

-- 2. Mostrar todos los pokemons que no evolucionan (80 Registros).

SELECT
	pok.NumeroPokedex,
    pok.Nombre
FROM pokemon pok
LEFT JOIN pokemon_forma_evolucion pfe ON pok.NumeroPokedex=pfe.NumeroPokedex
WHERE IdFormaEvolucion IS NULL;

-- 3. Mostrar la cantidad de los pokemon de cada tipo. Hacer una vista de ello.

CREATE VIEW pokemonsPorTipo AS
SELECT
	tip.Nombre,
	COUNT(pok.NumeroPokedex) AS Cantidad
FROM pokemon pok
JOIN pokemon_tipo poti ON pok.NumeroPokedex=poti.NumeroPokedex
JOIN tipo tip ON poti.IdTipo=tip.IdTipo
GROUP BY tip.Nombre;


SELECT * FROM pokemonsPorTipo;

-- 4. Mostrar todos los nombres que aprende Charmander por MO.

SELECT
	mov.Nombre
FROM movimiento mov
JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento=pmf.IdMovimiento
JOIN forma_aprendizaje foap ON pmf.IdFormaAprendizaje=foap.IdFormaAprendizaje
-- JOIN mo mo ON foap.IdFormaAprendizaje=mo.IdFormaAprendizaje
WHERE foap.IdTipoAprendizaje= (SELECT IdTipoAprendizaje
								FROM tipo_forma_aprendizaje
                                WHERE UPPER(TipoAprendizaje)='MO')
AND pmf.NumeroPokedex= (SELECT NumeroPokedex
						FROM pokemon
                        WHERE UPPER(Nombre)='CHARMANDER');
