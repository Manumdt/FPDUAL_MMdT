package com.example.PokeApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.PokeApi.model.Tipos;

@Repository
public interface TiposRepository extends CrudRepository<Tipos, Integer> {

}
