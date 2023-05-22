package gestorFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

public class GestorFicheros {

	public void borrarFicheros(String rutaBorrado) {
		
		int i;
		File f = new File(rutaBorrado);
		File contenido[] = f.listFiles();
		
		for(i=0;i<contenido.length;i++) {
			if(contenido[i].isFile()) {
				contenido[i].delete();
			}else {
				borrarFicheros(contenido[i].toString());
				contenido[i].delete();
			}
		}
		
		System.out.println("\nContenido eliminado correctamente");
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		int opcion;
		boolean salir=false;
		String ruta="C:" + File.separator + "Workspace" + File.separator + "Programación" + File.separator + "Pruebas" + File.separator ;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Persona per = new Persona();
		GestorFicheros gf = new GestorFicheros();

		while(salir!=true) {
				
			System.out.println("Elige una opción");
			System.out.println("1.- Crear fichero");
			System.out.println("2.- Leer fichero");
			System.out.println("3.- Borrar fichero");
			System.out.println("4.- Salir");
			opcion=Integer.parseInt(br.readLine());
			
			switch(opcion) {
				case 1:
					
					String NombreFichero;
					String rutaEscritura;
					String Nombre,sexo;
					int edad;
					
					BufferedWriter bw;
					
					System.out.println("\nHas elegido 'Crear fichero'");
					System.out.println("¿Como quieres llamar al fichero?");
					NombreFichero=br.readLine();
					rutaEscritura=ruta+NombreFichero;
					
					System.out.println("Introduce el Nombre:");
					Nombre=br.readLine();
					per.setNombre(Nombre);
					
					System.out.println("Introduce la edad:");
					edad=Integer.parseInt(br.readLine());
					per.setEdad(edad);
					
					System.out.println("Introduce el sexo:");
					sexo=br.readLine();
					per.setSexo(sexo);
					
					FileWriter fw = new FileWriter(rutaEscritura);
					bw = new BufferedWriter(fw);
					
					bw.write(per.getNombre());
					bw.newLine();
					bw.write(Integer.toString(per.getEdad()));
					bw.newLine();
					bw.write(per.getSexo());
					bw.newLine();
					bw.flush();
					bw.close();
					
					break;
				
				case 2:
					
					int i, indice;
					String rutaLectura;
					File fLectura = new File(ruta);
					File contenidoLeer[] = fLectura.listFiles();

					System.out.println("\nHas elegido 'Leer fichero'");
					System.out.println("¿Qué fichero quieres leer?");
					
					for(i=0;i<contenidoLeer.length;i++) {
						System.out.println((i+1) + ". "  + contenidoLeer[i].getName());
					}
					
					indice=Integer.parseInt(br.readLine());
					NombreFichero=contenidoLeer[indice-1].getName();
					rutaLectura= ruta + NombreFichero;
				
					
					try {
						
						FileReader fr = new FileReader(rutaLectura); 
						BufferedReader br1 = new BufferedReader(fr); 
						
						Nombre=br1.readLine();
						edad=Integer.parseInt(br1.readLine());
						sexo=br1.readLine();
						br1.close();
						
						
						System.out.println("\nNombre: " + Nombre + "\nEdad: " + edad + "\nSexo: " + sexo + "\n");
					
					}catch(IOException e) {
						System.out.println("Error: " + e.toString());
					}
					
					break;
					
				case 3:
					
					String rutaBorrado;
					File fBorrado = new File(ruta);
					File contenidoBorrado[] = fBorrado.listFiles();
					
					System.out.println("\nHas elegido 'Borrar fichero'");
					System.out.println("¿Qué deseas hacer?");
					System.out.println("1.- Borrar un archivo");
					System.out.println("2.- Borrar todos los archivos");
					opcion=Integer.parseInt(br.readLine());
					
					if(opcion==1) {
						System.out.println("¿Qué archivo deseas borrar?");
						
						for(i=0;i<contenidoBorrado.length;i++) {
							System.out.println((i+1) + ". " + contenidoBorrado[i].getName());
						}
						
						indice=Integer.parseInt(br.readLine());
						NombreFichero= contenidoBorrado[indice-1].getName();
						contenidoBorrado[indice-1].delete();
						
						System.out.println("Se ha eliminado el archivo " + NombreFichero);
						
					}else if(opcion==2) {
						System.out.println("Se va a eliminar el contenido de toda la carpeta");
						
						gf.borrarFicheros(ruta);
						
						}
						
					break;
					
				case 4:
					
					System.exit(0);
					break;
					
				default:
					
					System.out.println("\nNúmero inválido, pruebe a introducir un número válido\n");
			}
		
		}
		
	}

}
