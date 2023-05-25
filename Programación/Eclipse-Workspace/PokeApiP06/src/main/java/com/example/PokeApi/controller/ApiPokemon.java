package com.example.PokeApi.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PokeApi.model.Pokemon;
import com.example.PokeApi.service.PokemonService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class ApiPokemon {
	
	@Autowired
	PokemonService pokemonService;
	
	@GetMapping("/saludar")
	public String saludar() {
		return "Hola mundo desde Spring";
	}
	
	@GetMapping("/all")
	public ArrayList<Pokemon> getAllPokemons(){
		return pokemonService.getAllPokemons();
	}
	
	@GetMapping("/find/{numero_pokedex}")
	public Optional<Pokemon> getPokemonByNumero_Pokedex(@PathVariable("numero_pokedex") int numero_pokedex){
		return pokemonService.getPokemonByNumero_pokedex(numero_pokedex);
	}
	
	@PostMapping("/save")
	public Pokemon savePokemon (@RequestBody Pokemon p) {
		return pokemonService.savePokemon(p);
	}
	
	@DeleteMapping("/delete/{numero_pokedex}")
	public String deletePokemonByNumero_Pokedex (@PathVariable("numero_pokedex") int numero_pokedex) {
		if(pokemonService.deletePokemonByNumero_Pokedex(numero_pokedex))
			return "Se ha eliminado el Pokemon";
		else
			return "No se ha eliminado el Pokemon";
	}
	
	
	
}
