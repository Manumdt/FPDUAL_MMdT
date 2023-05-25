package com.example.PokeApi.service;

import java.util.Optional;

import com.example.PokeApi.model.Stats;

public interface StatsService {
	
	Optional<Stats> getStatsByNumero_pokedex(int numero_pokedex);
}
