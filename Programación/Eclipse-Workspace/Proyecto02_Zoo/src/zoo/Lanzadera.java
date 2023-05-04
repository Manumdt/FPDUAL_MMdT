package zoo;

import java.util.List;
import java.util.ArrayList;

public class Lanzadera {

	public static void main(String[] args) {
		
		List<Animales> animales= new ArrayList<Animales>();
		
		Salvajes s1 = new Salvajes ("Tigre: Shere Kang", 9);
		Domestico d1 = new Domestico ("Rata: Basile", 4);
		Domestico d2 = new Domestico ("Perro: Halley", 3, new Dueno("Manu", 22, new Dni("Y", 3960905L)));
		
		animales.add(s1);
		animales.add(d1);
		animales.add(d2);
		
		Gestor.MostrarZoo(animales);
	}
}