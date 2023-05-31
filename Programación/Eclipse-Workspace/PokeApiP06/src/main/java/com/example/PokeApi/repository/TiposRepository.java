package com.example.PokeApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.PokeApi.model.Pokemon;

@Repository
public interface TiposRepository extends CrudRepository<Pokemon, Integer> {

}
