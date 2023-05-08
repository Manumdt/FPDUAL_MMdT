package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class gestor {

	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		
		int opcion;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		conexion con = new conexion();
		
		System.out.println("Elige una opción");
		System.out.println("1.- Mostar Pokemons");
		System.out.println("2.- Crear Pokemon");
		System.out.println("3.- ");
		System.out.println("4.- ");
		opcion=Integer.parseInt(br.readLine());
		
		switch(opcion) {
			case 1:
				con.mostrarPokemons();
				
		}
	}

}
