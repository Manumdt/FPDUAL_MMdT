package com.example.PokeApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.PokeApi.model.Stats;

/**
 * Repositorio de la clase Stats
 * @author Manuel mateos de Torres
 *
 */
@Repository
public interface StatsRepository extends CrudRepository<Stats, Integer>{

}
