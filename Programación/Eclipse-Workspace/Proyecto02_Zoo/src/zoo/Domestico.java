package zoo;

public class Domestico extends Animales{

	private Dueno dueno;

	public Domestico(String Nombre, int edad) {
		super(Nombre, edad);
	}

	public Domestico(String Nombre, int edad, Dueno dueno) {
		super(Nombre, edad);
		this.dueno = dueno;
	}

	public Dueno getDueno() {
		return dueno;
	}

	public void setDueno(Dueno dueno) {
		this.dueno = dueno;
	}

	@Override
	public String toString() {
		return super.toString() + " - Domestico [dueño=" + dueno + "]";
	}
}
