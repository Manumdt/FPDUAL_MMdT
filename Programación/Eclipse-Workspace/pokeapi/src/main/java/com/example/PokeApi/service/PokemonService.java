package com.example.PokeApi.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PokeApi.model.Pokemon;

public interface PokemonService {
	
	ArrayList<Pokemon> getAllPokemons();
	Optional<Pokemon> getPokemonByNumeroPokedex(int NumeroPokedex);
	Pokemon savePokemon (Pokemon p);
	boolean deletePokemonByNumeroPokedex(int NumeroPokedex);
}
