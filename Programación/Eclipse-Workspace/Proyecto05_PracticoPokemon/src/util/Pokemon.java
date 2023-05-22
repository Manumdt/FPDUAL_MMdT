package util;

public class Pokemon {
	
	private int numero_pokedex;
	private String Nombre;
	private float Peso;
	private float Altura;
	
	public Pokemon() {
		super();
	}

	public Pokemon(int numero_pokedex, String Nombre, float Peso, float Altura) {
		super();
		this.numero_pokedex = numero_pokedex;
		this.Nombre = Nombre;
		this.Peso = Peso;
		this.Altura = Altura;
	}

	public int getnumero_pokedex() {
		return numero_pokedex;
	}

	public void setnumero_pokedex(int numero_pokedex) {
		this.numero_pokedex = numero_pokedex;
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
		return "numero_pokedex= " + numero_pokedex + " Nombre= " + Nombre + " Peso= " + Peso + " Altura= " + Altura;
	}
	
}