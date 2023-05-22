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
	public Optional<Pokemon> getPokemonByNumeroPokedex(int NumeroPokedex) {
		return pokemonRepository.findById(NumeroPokedex);
	}

	@Override
	public Pokemon savePokemon(Pokemon p) {
		return pokemonRepository.save(p);
	}

	@Override
	public boolean deletePokemonByNumeroPokedex(int NumeroPokedex) {
		try {
			Optional<Pokemon> p = getPokemonByNumeroPokedex(NumeroPokedex);
			pokemonRepository.delete(p.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
