package com.example.PokeApi.model;

import java.util.List;

import jakarta.persistence.*;

/**
 * Clase entidad Pokemon que hace referencia a la tabla pokemon de la base de datos
 * @author Manuel Mateos de Torres
 * 
 */

@Entity
@Table(name = "pokemon")
public class Pokemon {
	/**
	 * Atributos de la clase
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUMERO_POKEDEX")
	private int numero_pokedex;
	private String nombre;
	private float peso;
	private float altura;
	private String descripcion;
	private String url;
	
	//Enlace entre la tabla pokemon y stats
	@OneToOne(mappedBy = "p")
	@PrimaryKeyJoinColumn
	private Stats stats;
	
	//Enlace entre la tabla pokemon y pokemon_tipo
	@ManyToMany
	@JoinTable(name = "pokemon_tipo", joinColumns = { @JoinColumn(name = "NUMERO_POKEDEX") }, inverseJoinColumns = {
	@JoinColumn(name = "ID_TIPO") })
	private List<Tipos> tipos;
	/**
	 * Constructor por defecto
	 */
	public Pokemon() {
		
	}
/**
 * Constructor parametrizado
 * @param numero_pokedex
 * @param nombre
 * @param peso
 * @param altura
 * @param descripcion
 * @param url
 * @param stats
 * @param tipos
 */
	public Pokemon(int numero_pokedex, String nombre, float peso, float altura, String descripcion, String url, Stats stats, List<Tipos> tipos) {
		this.numero_pokedex = numero_pokedex;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.descripcion = descripcion;
		this.url = url;
		this.stats = stats;
		this.tipos = tipos;
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

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public List<Tipos> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipos> tipos) {
		this.tipos = tipos;
	}


	

}
