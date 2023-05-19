package com.pokeapi.pokeapi.model;

import jakarta.persistence.*;

@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numeroPokedex;
	private String nombre;
	private float peso;
	private float altura;
	
	public Pokemon() {
	}

	public Pokemon(int numeroPokedex, String nombre, float peso, float altura) {
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
	}

	public int getNumeroPokedex() {
		return numeroPokedex;
	}

	public void setNumeroPokedex(int numeroPokedex) {
		this.numeroPokedex = numeroPokedex;
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

	@Override
	public String toString() {
		return "numeroPokedex= " + numeroPokedex + " nombre= " + nombre + " peso= " + peso + " altura= " + altura;
	}
	
}