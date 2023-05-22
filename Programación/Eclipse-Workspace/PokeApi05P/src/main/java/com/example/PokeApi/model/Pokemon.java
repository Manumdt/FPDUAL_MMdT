package com.example.PokeApi.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue
	private int numero_pokedex;
	private String nombre;
	private float peso;
	private float altura;
	
	public Pokemon() {
		super();
	}

	public Pokemon(int numero_pokedex, String nombre, float peso, float altura) {
		super();
		this.numero_pokedex = numero_pokedex;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
	}

	public int getnumero_pokedex() {
		return numero_pokedex;
	}

	public void setnumero_pokedex(int numero_pokedex) {
		this.numero_pokedex = numero_pokedex;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

}
