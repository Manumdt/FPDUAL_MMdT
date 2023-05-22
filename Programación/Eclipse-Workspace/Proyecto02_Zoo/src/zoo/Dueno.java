package zoo;

public class Dueno extends SerVivo{

	private Dni dni;
	
	public Dueno(String Nombre, int edad) {
		super(Nombre,edad);
	}
	
	public Dueno(String Nombre, int edad, Dni dni) {
		super(Nombre,edad);
		this.dni= dni;
	}

	public Dni getDni() {
		return dni;
	}

	public void setDni(Dni dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return super.toString() + " - Dueno [dni=" + dni + "]";
	}
	
}
