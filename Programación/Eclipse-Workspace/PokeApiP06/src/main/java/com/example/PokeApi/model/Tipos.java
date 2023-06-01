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
	private int id_tipo_ataque;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "tipos")
	private List<Pokemon> pokemons;
	
	public Tipos() {
		
	}

	public Tipos(int id_tipo, String nombre, int id_tipo_ataque) {
		this.id_tipo = id_tipo;
		this.nombre = nombre;
		this.id_tipo_ataque = id_tipo_ataque;
	}

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

	public int getId_tipo_ataque() {
		return id_tipo_ataque;
	}

	public void setId_tipo_ataque(int id_tipo_ataque) {
		this.id_tipo_ataque = id_tipo_ataque;
	}

	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}
	
}
