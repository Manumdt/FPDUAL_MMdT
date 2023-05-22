package com.pokeapi.pokeapi.Service;

import com.pokeapi.pokeapi.model.Pokemon;

import java.util.ArrayList;
import java.util.Optional;

public interface PokemonService {

	ArrayList<Pokemon> getAllPokemon();
	Optional<Pokemon> getPokemonById(int NumeroPokedex);
	Pokemon savePokemon(Pokemon p);
	boolean deletePokemonById(int NumeroPokedex);
	
}
