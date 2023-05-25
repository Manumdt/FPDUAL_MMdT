package com.example.PokeApi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUMERO_POKEDEX")
	private int numero_pokedex;
	private String nombre;
	private float peso;
	private float altura;
	private String descripcion;
	private String url;
	
	@OneToOne(mappedBy = "p")
	@PrimaryKeyJoinColumn
	private Stats stats;
	
	public Pokemon() {
		super();
	}

	public Pokemon(int numero_pokedex, String nombre, float peso, float altura, String descripcion, String url) {
		super();
		this.numero_pokedex = numero_pokedex;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.descripcion = descripcion;
		this.url = url;
	}

	public int getNumero_pokedex() {
		return numero_pokedex;
	}

	public void setNumero_pokedex(int numero_pokedex) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
