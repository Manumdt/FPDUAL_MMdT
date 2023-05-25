package com.example.PokeApi.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PokeApi.model.Pokemon;

public interface PokemonService {
	
	ArrayList<Pokemon> getAllPokemons();
	Optional<Pokemon> getPokemonByNumero_pokedex(int numero_pokedex);
	Pokemon savePokemon (Pokemon p);
	boolean deletePokemonByNumero_Pokedex(int numero_pokedex);
}
