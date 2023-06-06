package com.example.PokeApi.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PokeApi.model.Pokemon;
import com.example.PokeApi.service.PokemonService;
import com.example.PokeApi.service.PokemonServiceImpl;

/**
 * Controlador de la aplicación
 * @author Manuel Mateos de Torres
 *
 */
@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class ApiPokemon {
	
	@Autowired
	PokemonService pokemonService;
	
	@Autowired
    PokemonServiceImpl pokemonServiceImpl;
	
	/**
	 * Método get que devuelve un Hola Mundo
	 * @return
	 */
	@GetMapping("/saludar")
	public String saludar() {
		return "Hola mundo desde Spring";
	}
	
	/**
	 * Método get para devolver todos los pokemons de la base de datos
	 * @return
	 */
	@GetMapping("/all")
	public ArrayList<Pokemon> getAllPokemons(){
		return pokemonService.getAllPokemons();
	}
	
	/**
	 * Método get para devolver un pokemon de la base de datos
	 * @return
	 */
	@GetMapping("/find/{numero_pokedex}")
	public Optional<Pokemon> getPokemonByNumero_Pokedex(@PathVariable("numero_pokedex") int numero_pokedex){
		return pokemonService.getPokemonByNumero_pokedex(numero_pokedex);
	}
	
	/**
	 * Método get para crear un pokemon en la base de datos
	 * @return
	 */
	@PostMapping("/save")
	public Pokemon savePokemon (@RequestBody Pokemon p) {
		return pokemonService.savePokemon(p);
	}
	
	/**
	 * Método get para borrar un pokemon de la base de datos
	 * @return
	 */
	@DeleteMapping("/delete/{numero_pokedex}")
	public String deletePokemonByNumero_Pokedex (@PathVariable("numero_pokedex") int numero_pokedex) {
		if(pokemonService.deletePokemonByNumero_Pokedex(numero_pokedex))
			return "Se ha eliminado el Pokemon";
		else
			return "No se ha eliminado el Pokemon";
	}
	
	/**
	 * Método get para modificar un pokemon de la base de datos
	 * @return
	 */	
	@PutMapping("/update/{numero_pokedex}")
	public ResponseEntity<Pokemon>updatePokemon(@PathVariable int numero_pokedex, @RequestBody Pokemon pokemon){
		
		Optional<Pokemon> pokemonForId = pokemonServiceImpl.getPokemonByNumero_pokedex(numero_pokedex);
		
		if(pokemonForId.isEmpty()) {
		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {		
			
			Pokemon p = pokemonForId.get();
			
			p.setNombre(pokemon.getNombre());
			p.setPeso(pokemon.getPeso());
			p.setAltura(pokemon.getAltura());
			p.setUrl(pokemon.getUrl());
			p.setDescripcion(pokemon.getDescripcion());
			Pokemon pokemonUpdate = pokemonServiceImpl.savePokemon(p);
			return new ResponseEntity<>(pokemonUpdate, HttpStatus.OK);
		}
	}	
}