package com.example.PokeApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "estadisticas_base")
public class Stats {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NUMERO_POKEDEX")
	private int numero_pokedex;
	private int ps;
	private int ataque;
	private int defensa;
	private int especial;
	private int velocidad;
	
	@JsonIgnore
	@OneToOne
	@PrimaryKeyJoinColumn
	private Pokemon p;

	public int getNumero_pokedex() {
		return numero_pokedex;
	}

	public void setNumero_pokedex(int numero_pokedex) {
		this.numero_pokedex = numero_pokedex;
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

	public Pokemon getP() {
		return p;
	}

	public void setP(Pokemon p) {
		this.p = p;
	}
	
}
