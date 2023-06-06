package com.example.PokeApi.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PokeApi.model.Pokemon;

public interface PokemonService {
	
	ArrayList<Pokemon> getAllPokemons();
	Optional<Pokemon> getPokemonBynumero_pokedex(int numero_pokedex);
	Pokemon savePokemon (Pokemon p);
	boolean deletePokemonBynumero_pokedex(int numero_pokedex);
}
