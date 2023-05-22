package zoo;

public abstract class SerVivo {
	 
	private String Nombre;
	private int edad;
	
	public SerVivo() {
		super();
	}

	public SerVivo(String Nombre, int edad) {
		super();
		this.Nombre = Nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "SerVivo [Nombre=" + Nombre + ", edad=" + edad + "]";
	}
}
