package com.example.PokeApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.PokeApi.model.Pokemon;
import com.example.PokeApi.model.Stats;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {

}