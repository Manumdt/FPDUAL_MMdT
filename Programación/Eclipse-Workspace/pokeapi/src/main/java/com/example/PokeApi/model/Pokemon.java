package com.example.PokeApi.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue
	private int NumeroPokedex;
	private String Nombre;
	private float Peso;
	private float Altura;
	
	public Pokemon() {
		super();
	}

	public Pokemon(int NumeroPokedex, String Nombre, float Peso, float Altura) {
		super();
		this.NumeroPokedex = NumeroPokedex;
		this.Nombre = Nombre;
		this.Peso = Peso;
		this.Altura = Altura;
	}

	public int getNumeroPokedex() {
		return NumeroPokedex;
	}

	public void setNumeroPokedex(int NumeroPokedex) {
		this.NumeroPokedex = NumeroPokedex;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}

	public float getPeso() {
		return Peso;
	}

	public void setPeso(float Peso) {
		this.Peso = Peso;
	}

	public float getAltura() {
		return Altura;
	}

	public void setAltura(float Altura) {
		this.Altura = Altura;
	}

}
