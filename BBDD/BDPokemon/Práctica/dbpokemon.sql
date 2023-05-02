DROP DATABASE IF EXISTS dbpokemon;
CREATE DATABASE dbpokemon CHARSET utf8mb4;
USE dbpokemon;

CREATE TABLE pokemon(
	NumeroPokedex INT UNSIGNED,
    Nombre VARCHAR(15),
    Peso FLOAT (6.3),
    Altura FLOAT (6.3),
    PRIMARY KEY(NumeroPokedex)
);

CREATE TABLE tipo_ataque(
	IdTipoAtaque INT UNSIGNED,
    Tipo VARCHAR(8),
    PRIMARY KEY(IdTipoAtaque)
);

CREATE TABLE tipo(
	IdTipo INT UNSIGNED,
    Nombre VARCHAR(10),
    IdTipoAtaque INT UNSIGNED,
    PRIMARY KEY(IdTipo),
    CONSTRAINT IdTipoAtaque_pok_tipo_fk FOREIGN KEY (IdTipoAtaque) REFERENCES pok_tipo_ataque(IdTipoAtaque)
);

CREATE TABLE movimiento(
	IdMovimiento INT UNSIGNED,
    Nombre VARCHAR(20),
    Potencia INT,
    PrecisionMov INT,
    Descripcion VARCHAR(500),
    PP INT,
    IdTipo INT UNSIGNED,
    Prioridad INT,
    PRIMARY KEY(IdMovimiento),
    CONSTRAINT IdTipo_pok_movimiento_fk FOREIGN KEY (IdTipo) REFERENCES pok_tipo(IdTipo)
);

CREATE TABLE pokemon_tipo(
	NumeroPokedex INT UNSIGNED AUTO_INCREMENT,
    IdTipo INT UNSIGNED,
    PRIMARY KEY(NumeroPokedex,IdTipo),
    CONSTRAINT NumeroPokedex_pok_pokemon_tipo_fk FOREIGN KEY (NumeroPokedex) REFERENCES pok_pokemon(NumeroPokedex),
    CONSTRAINT IdTipo_fk_pok_pokemon_tipo FOREIGN KEY (IdTipo) REFERENCES pok_tipo(IdTIpo)
);