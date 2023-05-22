package com.pokeapi.pokeapi.model;

import jakarta.persistence.*;

@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int NumeroPokedex;
	private String Nombre;
	private float Peso;
	private float Altura;
	
	public Pokemon() {
	}

	public Pokemon(int NumeroPokedex, String Nombre, float Peso, float Altura) {
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

	@Override
	public String toString() {
		return "NumeroPokedex= " + NumeroPokedex + " Nombre= " + Nombre + " Peso= " + Peso + " Altura= " + Altura;
	}
	
}