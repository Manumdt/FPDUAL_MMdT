package zoo;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Lanzadera {
	
	private static List<Animales> animales = new ArrayList<Animales>();
	
	public static void main(String[] args) {

		Salvajes s1 = new Salvajes("Tigre: Shere Kang", 9);
		Domestico d1 = new Domestico("Rata: Basile", 4);
		Domestico d2 = new Domestico("Perro: Halley", 3, new Dueno("Manu", 22, new Dni("Y", "03960905")));

		animales.add(s1);
		animales.add(d1);
		animales.add(d2);

		Gestor.MostrarZoo(animales);

		System.out.println("-------------------------------------------------");

		Animales animal= getAnimalNombre("Perro: Halley");  
		
		System.out.println(animal);

	}

	public static Animales getAnimalNombre(String nombre) {
		for (Animales a : animales) {
			if(a.getNombre().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
}