package com.example.PokeApi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PokeApi.model.Pokemon;
import com.example.PokeApi.repository.PokemonRepository;

@Service
public class PokemonServiceImpl implements PokemonService {
	
	@Autowired
	PokemonRepository pokemonRepository;
	@Override
	public ArrayList<Pokemon> getAllPokemons() {
		return (ArrayList<Pokemon>) pokemonRepository.findAll();
	}

	@Override
	public Optional<Pokemon> getPokemonByNumero_pokedex(int numero_pokedex) {
		return pokemonRepository.findById(numero_pokedex);
	}

	@Override
	public Pokemon savePokemon(Pokemon p) {
		return pokemonRepository.save(p);
	}

	@Override
	public boolean deletePokemonByNumero_Pokedex(int numero_pokedex) {
		try {
			Optional<Pokemon> p = getPokemonByNumero_pokedex(numero_pokedex);
			pokemonRepository.delete(p.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
