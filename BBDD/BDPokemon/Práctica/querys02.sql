USE dbpokemon;

-- 1. Mostrar el nombre y tipo del ataque con más potencia:

SELECT
	mov.Nombre,
    tip.Nombre,
    mov.Potencia
FROM movimiento mov
JOIN tipo tip ON mov.IdTipo=tip.IdTipo
JOIN pokemon_tipo poti ON tip.IdTipo=poti.IdTipo
ORDER BY mov.Potencia DESC
LIMIT 1;

-- 2. Muestra el numero pokedex, nombre, ataque y velocidad de todos los pokemons con ataque >50 y velocidad <75 ordenados por nombre.

SELECT
	estbas.NumeroPokedex,
    pok.Nombre,
    estbas.Ataque,
    estbas.Velocidad
FROM pokemon pok
JOIN estadisticas_base estbas ON pok.NumeroPokedex=estbas.NumeroPokedex
WHERE estbas.Ataque > 50 
AND estbas.Velocidad < 75
ORDER BY 2;
    
-- 3. Mostrar el número de movimientos de cada tipo.

SELECT
	tip.Nombre AS Tipos,
	COUNT(mov.IdMovimiento) AS NumeroMovimientos
FROM movimiento mov
JOIN tipo tip ON mov.IdTipo=tip.IdTipo
GROUP BY tip.Nombre;
	
-- 4. Mostrar todos los movimientos que puedan envenenar.

SELECT
	mov.Nombre,
    mes.Probabilidad
FROM movimiento mov
JOIN movimiento_efecto_secundario mes ON mov.IdMovimiento=mes.IdMovimiento
JOIN efecto_secundario efse ON mes.IdEfectoSecundario=efse.IdEfectoSecundario
WHERE UPPER(efse.efectoSecundario)='ENVENENAMIENTO LEVE';

-- 5. Mostrar todos los movimientos que aprende pikachu.

SELECT
	mov.Nombre
FROM movimiento mov
JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento=pmf.IdMovimiento
WHERE pmf.NumeroPokedex=(SELECT NumeroPokedex
						 FROM pokemon
						WHERE UPPER(Nombre)='PIKACHU');

