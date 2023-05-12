package util;

public class Pokemon {
	
	private int numeroPokedex;
	private String nombre;
	private float peso;
	private float altura;
	
	public Pokemon() {
		super();
	}

	public Pokemon(int numeroPokedex, String nombre, float peso, float altura) {
		super();
		this.numeroPokedex = numeroPokedex;
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