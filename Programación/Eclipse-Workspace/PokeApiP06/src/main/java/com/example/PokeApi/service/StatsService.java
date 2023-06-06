package com.example.PokeApi.service;

import java.util.Optional;

import com.example.PokeApi.model.Stats;

/**
 * Clase que implementa los servicios de la interfaz StatsService
 * @author Manuel Mateos de Torres
 *
 */
public interface StatsService {
	
	Optional<Stats> getStatsByNumero_pokedex(int numero_pokedex);
}
