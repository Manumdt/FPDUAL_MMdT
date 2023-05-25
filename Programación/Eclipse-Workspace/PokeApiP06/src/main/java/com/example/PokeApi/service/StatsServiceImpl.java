package com.example.PokeApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PokeApi.model.Pokemon;
import com.example.PokeApi.model.Stats;
import com.example.PokeApi.repository.StatsRepository;

@Service
public class StatsServiceImpl implements StatsService{

	@Autowired
	StatsRepository statsRepository;
	@Override
	public Optional<Stats> getStatsByNumero_pokedex(int numero_pokedex) {
		return statsRepository.findById(numero_pokedex);
	}
}
