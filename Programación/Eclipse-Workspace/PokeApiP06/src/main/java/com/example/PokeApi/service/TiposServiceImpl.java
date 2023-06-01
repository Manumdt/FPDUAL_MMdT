package com.example.PokeApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PokeApi.model.Tipos;
import com.example.PokeApi.repository.TiposRepository;

@Service
public class TiposServiceImpl implements TiposService{

	@Autowired
	TiposRepository tiposRepository;
	@Override
	public Optional<Tipos> getTiposByNumero_pokedex(int id_tipo) {
		return tiposRepository.findById(id_tipo);
	}
}