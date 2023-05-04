package zoo;

public class Salvajes extends Animales{

	public Salvajes(String nombre, int edad) {
		super(nombre, edad);
	}

	@Override
	public String toString() {
		return super.toString() + " - Salvaje []";
	}

}
