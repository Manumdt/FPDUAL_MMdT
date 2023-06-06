package com.example.PokeApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.PokeApi.model.Tipos;

/**
 * Repositorio de la clase Tipos
 * @author Manuel mateos de Torres
 *
 */
@Repository
public interface TiposRepository extends CrudRepository<Tipos, Integer> {

}
