package com.pokeapi.pokeapi.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokeapi.pokeapi.model.Pokemon;
import com.pokeapi.pokeapi.repository.PokemonRepository;

@Service
public class PokemonServiceImpl implements PokemonService{

	@Autowired
	PokemonRepository pokemonRepository;
	
	@Override
	public ArrayList<Pokemon> getAllPokemon() {
		return (ArrayList<Pokemon>) pokemonRepository.findAll();
	}

	@Override
	public Optional<Pokemon> getPokemonById(int NumeroPokedex) {
		return pokemonRepository.findById(NumeroPokedex);
	}

	@Override
	public Pokemon savePokemon(Pokemon p) {
		return pokemonRepository.save(p);
	}

	@Override
	public boolean deletePokemonById(int NumeroPokedex) {
		
		try {
			Optional<Pokemon> p = getPokemonById(NumeroPokedex); 
			pokemonRepository.delete(p.get());
			return true;
		}catch(Exception e) {
			System.out.println("Excepción: --------- " + e.getLocalizedMessage());
			return false;
		}
	}

}
