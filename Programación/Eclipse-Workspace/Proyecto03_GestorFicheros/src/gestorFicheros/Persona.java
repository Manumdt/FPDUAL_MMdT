package gestorFicheros;

public class Persona {

	private String Nombre;
	private int edad;
	private String sexo;
	
	public Persona() {
		super();
	}

	public Persona(String Nombre, int edad, String sexo) {
		super();
		this.Nombre = Nombre;
		this.edad = edad;
		this.sexo = sexo;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
