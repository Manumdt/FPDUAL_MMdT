package zoo;

public class Domestico extends Animales{

	private Dueno dueno;

	public Domestico(String nombre, int edad) {
		super(nombre, edad);
	}

	public Domestico(String nombre, int edad, Dueno dueno) {
		super(nombre, edad);
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
