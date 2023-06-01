package com.example.PokeApi.service;

import java.util.Optional;

import com.example.PokeApi.model.Tipos;

public interface TiposService {

	Optional<Tipos> getTiposByNumero_pokedex(int id_tipo);
}
