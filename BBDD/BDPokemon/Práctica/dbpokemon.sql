DROP DATABASE IF EXISTS dbpokemon;
CREATE DATABASE dbpokemon CHARSET utf8mb4;
USE dbpokemon;

-- Parte01

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
    CONSTRAINT IdTipoAtaque_tipo_fk FOREIGN KEY (IdTipoAtaque) REFERENCES tipo_ataque(IdTipoAtaque)
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
    CONSTRAINT IdTipo_movimiento_fk FOREIGN KEY (IdTipo) REFERENCES tipo(IdTipo)
);

CREATE TABLE pokemon_tipo(
	NumeroPokedex INT UNSIGNED AUTO_INCREMENT,
    IdTipo INT UNSIGNED,
    PRIMARY KEY(NumeroPokedex,IdTipo),
    CONSTRAINT NumeroPokedex_pokemon_tipo_fk FOREIGN KEY (NumeroPokedex) REFERENCES pokemon(NumeroPokedex)ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT IdTipo_fk_pokemon_tipo FOREIGN KEY (IdTipo) REFERENCES tipo(IdTIpo)
);

-- Parte02

CREATE TABLE estadisticas_base(
	NumeroPokedex INT UNSIGNED,
    Ps INT UNSIGNED,
    Ataque INT,
    Defensa INT,
    Especial INT,
    Velocidad INT,
    PRIMARY KEY(NumeroPokedex),
    CONSTRAINT NumeroPokedex_estadisticas_base_fk FOREIGN KEY (NumeroPokedex) REFERENCES pokemon(NumeroPokedex) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE efecto_secundario(
	IdEfectoSecundario INT UNSIGNED,
    EfectoSecundario VARCHAR(30),
    PRIMARY KEY (IdEfectoSecundario)
);

CREATE TABLE movimiento_efecto_secundario(
	IdMovimiento INT UNSIGNED,
    IdEfectoSecundario INT UNSIGNED,
    Probabilidad FLOAT(6.3),
    PRIMARY KEY (IdMovimiento, IdEfectoSecundario),
    CONSTRAINT IdMovimiento_movimiento_fk FOREIGN KEY (IdMovimiento) REFERENCES movimiento(IdMovimiento),
    CONSTRAINT IdEfectoSecundario_movimiento_efecto_secundario_fk FOREIGN KEY (IdEfectoSecundario) REFERENCES efecto_secundario(IdEfectoSecundario)
);

CREATE TABLE tipo_forma_aprendizaje(
	IdTipoAprendizaje INT UNSIGNED,
    TipoAprendizaje VARCHAR(20),
    PRIMARY KEY (IdTipoAprendizaje)
);

CREATE TABLE forma_aprendizaje(
	IdFormaAprendizaje INT UNSIGNED,
    IdTipoAprendizaje INT UNSIGNED,
    PRIMARY KEY (IdFormaAprendizaje),
    CONSTRAINT IdTipoAprendizaje_forma_aprendizaje_fk FOREIGN KEY (IdTipoAprendizaje) REFERENCES tipo_forma_aprendizaje(IdTipoAprendizaje)
);

CREATE TABLE pokemon_movimiento_forma(
	NumeroPokedex INT UNSIGNED,
    IdMovimiento INT UNSIGNED,
    IdFormaAprendizaje INT UNSIGNED,
    PRIMARY KEY (NumeroPokedex, IdMovimiento, IdFormaAprendizaje),
    CONSTRAINT NumeroPokedex_pokemon_movimiento_forma_fk FOREIGN KEY (NumeroPokedex) REFERENCES pokemon(NumeroPokedex) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT IdMovimiento_pokemon_movimiento_forma_fk FOREIGN KEY (IdMovimiento) REFERENCES movimiento(IdMovimiento),
    CONSTRAINT IdFormaAprendizaje_pokemon_movimiento_forma_fk FOREIGN KEY (IdFormaAprendizaje) REFERENCES forma_aprendizaje(IdFormaAprendizaje)
);

-- Parte03

CREATE TABLE evoluciona_de(
	PokemonEvolucionado INT UNSIGNED,
    PokemonOrigen INT UNSIGNED,
    PRIMARY KEY (PokemonEvolucionado,PokemonOrigen),
    CONSTRAINT PokemonEvolucionado_evoluciona_de_fk FOREIGN KEY (PokemonEvolucionado) REFERENCES pokemon(NumeroPokedex) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT PokemonOrigen_evoluciona_de_fk FOREIGN KEY (PokemonOrigen) REFERENCES pokemon(NumeroPokedex) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pokemon_forma_evolucion(
	NumeroPokedex INT UNSIGNED,
    IdFormaEvolucion INT UNSIGNED,
    PRIMARY KEY (NumeroPokedex,IdFormaEvolucion),
    CONSTRAINT NumeroPokedex_pokemon_forma_evolucion FOREIGN KEY (NumeroPokedex) REFERENCES pokemon(NumeroPokedex) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tipo_evolucion(
	IdTipoEvolucion INT UNSIGNED,
    TipoEvolucion VARCHAR(15),
    PRIMARY KEY (IdTipoEvolucion)
);

CREATE TABLE forma_evolucion(
	IdFormaEvolucion INT UNSIGNED,
    IdTipoEvolucion INT UNSIGNED,
    PRIMARY KEY (IdFormaEvolucion),
    CONSTRAINT IdTipoEvolucion_forma_evolucion_fk FOREIGN KEY (IdTipoEvolucion) REFERENCES tipo_evolucion(IdTipoEvolucion)
);

CREATE TABLE nivel_evolucion(
	IdFormaEvolucion INT UNSIGNED,
    Nivel INT UNSIGNED,
    PRIMARY KEY (IdFormaEvolucion),
    CONSTRAINT IdFormaEvolucion_nivel_evolucion_fk FOREIGN KEY (IdFormaEvolucion) REFERENCES forma_evolucion(IdFormaEvolucion)
);

CREATE TABLE tipo_piedra(
	IdTipoPiedra INT UNSIGNED,
    NombrePiedra VARCHAR(20),
    PRIMARY KEY (IdTipoPiedra)
);

CREATE TABLE piedra(
	IdFormaEvolucion INT UNSIGNED,
    IdTipoPiedra INT UNSIGNED,
    PRIMARY KEY (IdFormaEvolucion),
    CONSTRAINT IdTipoPiedra_piedra_fk FOREIGN KEY (IdTipoPiedra) REFERENCES tipo_piedra(IdTipoPiedra),
    CONSTRAINT IdFormaEvolucion_piedra_fk FOREIGN KEY (IdFormaEvolucion) REFERENCES forma_evolucion(IdFormaEvolucion)
);

CREATE TABLE nivel_aprendizaje(
	IdFormaAprendizaje INT UNSIGNED,
    Nivel INT UNSIGNED,
    PRIMARY KEY (IdFormaAprendizaje),
    CONSTRAINT IdFormaAprendizaje_nivel_aprendizaje_fk FOREIGN KEY (IdFormaAprendizaje) REFERENCES forma_aprendizaje(IdFormaAprendizaje)
);

CREATE TABLE mo(
	IdFormaAprendizaje INT UNSIGNED,
    Mo VARCHAR(5),
    PRIMARY KEY (IdFormaAprendizaje),
    CONSTRAINT IdFormaAprendizaje_mo_fk FOREIGN KEY (IdFormaAprendizaje) REFERENCES forma_aprendizaje(IdFormaAprendizaje)
);

CREATE TABLE mt(
	IdFormaAprendizaje INT UNSIGNED,
    Mt VARCHAR(5),
    PRIMARY KEY (IdFormaAprendizaje),
    CONSTRAINT IdFormaAprendizaje_mt_fk FOREIGN KEY (IdFormaAprendizaje) REFERENCES forma_aprendizaje(IdFormaAprendizaje)
);

-- Trigger

CREATE TABLE pokemon_historico(
	NumeroPokedex INT UNSIGNED,
    Nombre VARCHAR(15),
    Peso FLOAT (6.3),
    Altura FLOAT (6.3),
    FechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(NumeroPokedex)
);

DELIMITER $$
CREATE TRIGGER historial AFTER INSERT ON pokemon
FOR EACH ROW
BEGIN
INSERT INTO pokemon_historico VALUES(NEW.NumeroPokedex, NEW.Nombre, NEW.Peso, NEW.Altura, NOW());
END$$
DELIMITER ;