package com.example.PokeApi.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero_pokedex;
	private String nombre;
	private float peso;
	private float altura;
	private String descripcion;
	private int ps;
	private int ataque;
	private int defensa;
	private int especial;
	private int velocidad;
	
	public Pokemon() {
		super();
	}

	public Pokemon(int numero_pokedex, String nombre, float peso, float altura, String descripcion, int ps, int ataque, int defensa, int especial, int velocidad) {
		super();
		this.numero_pokedex = numero_pokedex;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.descripcion = descripcion;
		
		this.ps = ps;
		this.ataque = ataque;
		this.defensa = defensa;
		this.especial = especial;
		this.velocidad = velocidad;
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
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getEspecial() {
		return especial;
	}

	public void setEspecial(int especial) {
		this.especial = especial;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
}
