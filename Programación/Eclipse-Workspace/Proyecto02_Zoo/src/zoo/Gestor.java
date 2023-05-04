package zoo;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Gestor implements IGestor{

	// private List<Animales> animales = new ArrayList<Animales>();
	
	public static void MostrarZoo(List animales) {
		
		for (Iterator Animales = animales.iterator(); Animales.hasNext();) {
			Object object = (Object) Animales.next();
			
			System.out.println(object);
		}				
	}

	@Override
	public Animales getAnimalByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
