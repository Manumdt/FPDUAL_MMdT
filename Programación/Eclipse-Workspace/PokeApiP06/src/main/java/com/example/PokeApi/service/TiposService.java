package com.example.PokeApi.service;

import java.util.Optional;

import com.example.PokeApi.model.Tipos;

/**
 * Clase que implementa los servicios de la interfaz TiposService
 * @author Manuel Mateos de Torres
 *
 */
public interface TiposService {

	Optional<Tipos> getTiposByNumero_pokedex(int id_tipo);
}
