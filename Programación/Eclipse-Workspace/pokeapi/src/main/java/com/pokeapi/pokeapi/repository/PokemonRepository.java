package com.pokeapi.pokeapi.repository;

import com.pokeapi.pokeapi.model.Pokemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Integer>{
}
