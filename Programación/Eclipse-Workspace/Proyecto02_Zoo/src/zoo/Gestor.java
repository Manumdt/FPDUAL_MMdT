package zoo;

import java.util.Iterator;
import java.util.List;

public class Gestor {

	public static void MostrarZoo(List animales) {
		
		for (Iterator Animales = animales.iterator(); Animales.hasNext();) {
			Object object = (Object) Animales.next();
			
			System.out.println(object);
		}
		
		
				
	}
	
}
