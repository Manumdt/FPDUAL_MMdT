package com.example.PokeApi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo")
public class Tipos {

	@Id
	@Column(name = "ID_TIPO")
	private int id_tipo;
	private String nombre;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "tipos")
	private List<Pokemon> pokemons;;

	public int getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}
	
}
