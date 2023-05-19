package com.pokeapi.pokeapi.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pokeapi.pokeapi.model.Pokemon;
import com.pokeapi.pokeapi.Service.PokemonService;

@RestController
@RequestMapping("api")
public class ApiPokemon {

	@Autowired
	PokemonService pokemonService;
	
	@GetMapping("/all")
	public ArrayList<Pokemon> getAllPokemon(){
		return pokemonService.getAllPokemon();
	}
	
	@GetMapping("/find/{numeroPokedex}")
	public Optional<Pokemon> getPokemonById(@PathVariable("numeroPokedex") int numeroPokedex){
		return pokemonService.getPokemonById(numeroPokedex);
	}
	
	@PostMapping("/save")
	public Pokemon savePokemon (@RequestBody Pokemon p) {
		return pokemonService.savePokemon(p);
	}
	
	@DeleteMapping("/delete/{numeroPokemon}")
	public String deletePokemonById(@PathVariable("numeroPokedex") int numeroPokedex) {
		if(pokemonService.deletePokemonById(numeroPokedex))
			return "Se ha eliminado el usuario";
		else
			return "No se ha eliminado el usuario";
	}
}
