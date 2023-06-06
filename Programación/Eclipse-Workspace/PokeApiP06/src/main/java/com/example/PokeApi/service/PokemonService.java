package com.example.PokeApi.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.PokeApi.model.Pokemon;

/**
 * Interfaz de servicios del objeto Pokemon
 * @author Manuel Mateos de Torres
 *
 */
public interface PokemonService {
	
	ArrayList<Pokemon> getAllPokemons();
	Optional<Pokemon> getPokemonByNumero_pokedex(int numero_pokedex);
	Pokemon savePokemon (Pokemon p);
	boolean deletePokemonByNumero_Pokedex(int numero_pokedex);

}
