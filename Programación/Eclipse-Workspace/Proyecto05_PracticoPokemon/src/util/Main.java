package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		int opcion;
		boolean salir=true;
		
		Connection cn = Conexion.conexion();
		
		Main mn = new Main();
		Scanner sc = new Scanner(System.in);		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
			do {
				opcion = menu(sc);				
				switch(opcion) {
					case 1:						
						mn.mostarPokemons(cn);					
						break;
						
					case 2:																									
						mn.actualizarPokemon(cn, sc);
						break;
						
					case 3:											
						mn.borrarPokemon(cn, sc);
						break;
						
					case 4:						
						mn.crearPokemon(cn, sc);
						break;
						
					case 5:						
						mn.mostrarStats(cn, sc);
						break;
						
					case 6:						
						mn.mostrarAprendizajeMovimientos(cn, sc);						
						break;
						
					case 7:										
						mn.mostrarTipoEvolucion(cn, sc);						
						break;
						
					case 8:
						mn.mostrarPokemonArrayList(cn, sc);
						break;	
					
					case 9:
						mn.mostrarMasPesados(cn, sc);
						break;	
						
					case 10:
						mn.mostrarPokemonsApi(cn, br);
						break;	
						
					case 11:
						mn.modificarNombre(cn, br);
						break;	
						
					case 12:
						cn.close();
						salir=false;
						break;	
				}	
			}while(salir==true);
	}

	private static int menu(Scanner sc) {
		int opcion;
		System.out.println("----------------------- MENÚ PRINCIPAL -----------------------");
		System.out.println("1. Mostrar todos los pokemons");
		System.out.println("2. Actualizar el nombre del pokemon por su numero de pokedex");
		System.out.println("3. Borrar pokemon");
		System.out.println("4. Insertar un nuevo pokemon");
		System.out.println("5. Mostrar estadísticas");
		System.out.println("6. Aprendizaje de movimientos");
		System.out.println("7. Tipo de evolución");
		System.out.println("8. Mostrar un solo pokemon (ArrayList)");
		System.out.println("9. Ver pokemons más pesados (ArrayList)");
		System.out.println("10. Mostrar pokemons (API)");
		System.out.println("11. Modificar el nombre de un pokemon (ArrayList)");
		System.out.println("12. SALIR");
		System.out.println("--------------------------------------------------------------");
		System.out.println("Ingrese una opcion:");
		opcion = sc.nextInt();
		return opcion;
	}
	
	private void mostarPokemons(Connection connection){
			
		try {
			String sentenciaSQL = "SELECT * FROM pokemon";
			Statement ins = connection.createStatement();
			ResultSet rs = ins.executeQuery(sentenciaSQL);
				
			System.out.println("Se van a mostar a continuación todos los pokemons");
			System.out.format("%5s %10s", "NumeroPokedex", "Nombre");
			System.out.println();
				
			while(rs.next()) {
				System.out.format("%5s %20s",rs.getInt("NumeroPokedex"), rs.getString("Nombre"));
				System.out.println();
			}	
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}
	}
	
	private void actualizarPokemon (Connection connection, Scanner sc){
		
		int numeroPokedex;
		String nombre;		
		
		try {
			System.out.println("Has elegido modificar el nombre de un pokemon");
			System.out.println("Introduce el numero de pokedex del pokemon a modificar:");
			numeroPokedex = sc.nextInt();
			System.out.println("Introduce el nuevo nombre:");
			nombre = sc.next();
			
			String sentenciaSQL = "UPDATE pokemon SET Nombre=? WHERE NumeroPokedex=?";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			ps.setString(1, nombre);
			ps.setInt(2,  numeroPokedex);
			
			System.out.println("Nombre modificado correctamente");
			
			ps.executeUpdate();		
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}				
	}
	
	private void borrarPokemon (Connection connection, Scanner sc){
		
		int numeroPokedex;
		
		try {
			System.out.println("Has elegido eliminar un pokemon");
			System.out.println("Introduce el numero de pokedex del pokemon a eliminar:");
			numeroPokedex = sc.nextInt();
			
			String sentenciaSQL = "DELETE FROM pokemon WHERE NumeroPokedex=?";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			ps.setInt(1,  numeroPokedex);
			
			System.out.println("Se ha eliminado el pokemon " + numeroPokedex + " correctamente");
			
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}				
	}
	
	private void crearPokemon (Connection connection,Scanner sc){
		
		int numeroPokedex;
		float peso, altura;
		String nombre;
		
		try {
			System.out.println("Has elegido insertar un pokemon");
			System.out.println("Introduce el nombre");
			nombre = sc.next();
			System.out.println("Introduce el peso");
			peso = sc.nextFloat();
			System.out.println("Introduce la altura");
			altura = sc.nextFloat();
			
			String sentenciaSQL1 = "SELECT NumeroPokedex FROM pokemon ORDER BY NumeroPokedex DESC LIMIT 1;";
			Statement ins = connection.createStatement();
			ResultSet rs = ins.executeQuery(sentenciaSQL1);
			
			rs.next();
			numeroPokedex= rs.getInt("NumeroPokedex")+1;
			
			String sentenciaSQL2 = "INSERT INTO pokemon (NumeroPokedex, Nombre, Peso, Altura) VALUES (?, ?, ?, ?);";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL2);
			ps.setInt(1, numeroPokedex);
			ps.setString(2, nombre);
			ps.setFloat(3, peso);
			ps.setFloat(4, altura);
			
			System.out.println("Pokemon insertado correctamente");
			
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}		
	}
	
	private void mostrarStats(Connection connection, Scanner sc){
		
		int numeroPokedex;
		
		try {
			System.out.println("Has elegido mostrar estadísticas");
			System.out.println("Introduce el numero de pokedex del pokemon deseado:");
			numeroPokedex = sc.nextInt();
			
			String sentenciaSQL = "SELECT pok.Nombre, eb.* FROM pokemon pok JOIN estadisticas_base eb ON pok.NumeroPokedex = eb.NumeroPokedex WHERE pok.NumeroPokedex = ?";
			
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			ps.setInt(1, numeroPokedex);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			System.out.format("%5s %10s %10s %10s %10s %10s", "Nombre", "Ps", "Ataque", "Defensa", "Especial", "Velocidad");
			System.out.println();
			System.out.format("%5s %8s %6s %9s %9s %9s", rs.getString("pok.Nombre"), rs.getInt("eb.Ps"), rs.getInt("eb.Ataque"), 
							  rs.getInt("Defensa"), rs.getInt("Especial"),  rs.getInt("Velocidad"));
			System.out.println();
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}			
	}
	
	private void mostrarAprendizajeMovimientos(Connection connection, Scanner sc){
		
		int numeroPokedex, formaAprendizaje;
		
		try {
			System.out.println("Has elegido aprendizaje de movimientos");
			System.out.println("Introduce el numero de pokedex del pokemon deseado:");
			numeroPokedex = sc.nextInt();
			System.out.println("Que tipo de aprendizaje quieres comprobar");
			System.out.println("1. Mt");
			System.out.println("2. Mo");
			System.out.println("3. Nivel");
			formaAprendizaje=sc.nextInt();
			
			if(formaAprendizaje == 1) {
				String sentenciaSQL = "SELECT CONCAT(mt.Mt,  ' ',  mov.Nombre) AS MT FROM movimiento mov JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento = pmf.IdMovimiento JOIN forma_aprendizaje foap ON pmf.IdFormaAprendizaje = foap.IdFormaAprendizaje JOIN mt ON foap.IdFormaAprendizaje = mt.IdFormaAprendizaje WHERE foap.IdTipoAprendizaje = ? AND pmf.NumeroPokedex=?;";
				PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
				ps.setInt(1, formaAprendizaje);
				ps.setInt(2, numeroPokedex);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("MT"));	
				}				
			}else if(formaAprendizaje == 2) {
				String sentenciaSQL = "SELECT CONCAT(mo.Mo,  ' ',  mov.Nombre) AS MO FROM movimiento mov JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento = pmf.IdMovimiento JOIN forma_aprendizaje foap ON pmf.IdFormaAprendizaje = foap.IdFormaAprendizaje JOIN mo ON foap.IdFormaAprendizaje = mo.IdFormaAprendizaje WHERE foap.IdTipoAprendizaje = ? AND pmf.NumeroPokedex=?;";
				PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
				ps.setInt(1, formaAprendizaje);
				ps.setInt(2, numeroPokedex);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("MO"));	
				}		
			}else if(formaAprendizaje == 3){
				String sentenciaSQL = "SELECT mov.Nombre, niap.Nivel  FROM movimiento mov JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento = pmf.IdMovimiento JOIN forma_aprendizaje foap ON pmf.IdFormaAprendizaje = foap.IdFormaAprendizaje JOIN nivel_aprendizaje niap ON foap.IdFormaAprendizaje = niap.IdFormaAprendizaje WHERE foap.IdTipoAprendizaje = ? AND pmf.NumeroPokedex=? ORDER BY 2;";
				PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
				ps.setInt(1, formaAprendizaje);
				ps.setInt(2, numeroPokedex);
				
				ResultSet rs = ps.executeQuery();
				
				System.out.format("%15s %10s", "Movimiento", "Nivel");
				System.out.println();
				
				while(rs.next()) {	
					System.out.format("%15s %10s", rs.getString("mov.Nombre"), rs.getString("niap.Nivel"));	
					System.out.println();
				}				
			}	
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}				
	}
	
	private void mostrarTipoEvolucion(Connection connection, Scanner sc){
		
		int numeroPokedex, tipoEvolucion;
		
		System.out.println("Has elegido tipo de evolución");
		System.out.println("Introduce el numero de pokedex del pokemon deseado:");
		numeroPokedex = sc.nextInt();
		
		try {					
			String sentenciaSQL = "SELECT tiev.IdTipoEvolucion FROM tipo_evolucion tiev JOIN forma_evolucion foel ON tiev.IdTipoEvolucion = foel.IdTipoEvolucion JOIN pokemon_forma_evolucion pfe ON foel.IdFormaEvolucion = pfe.IdFormaEvolucion WHERE pfe.NumeroPokedex=?";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			ps.setInt(1, numeroPokedex);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()==true) {
				tipoEvolucion = rs.getInt("tiev.IdTipoEvolucion");
			}else {
				tipoEvolucion = 0;
			}		
			
			if(tipoEvolucion==1) {
				String sentenciaNivel= "SELECT pok.Nombre, tiev.TipoEvolucion, niev.nivel FROM tipo_evolucion tiev JOIN forma_evolucion foel ON tiev.IdTipoEvolucion = foel.IdTipoEvolucion JOIN nivel_evolucion niev ON foel.IdFormaEvolucion = niev.IdFormaEvolucion JOIN pokemon_forma_evolucion pfe ON foel.IdFormaEvolucion = pfe.IdFormaEvolucion JOIN pokemon pok ON pfe.NumeroPokedex = pok.NumeroPokedex WHERE pfe.NumeroPokedex= ?;";
				PreparedStatement psNivel = connection.prepareStatement(sentenciaNivel);
				psNivel.setInt(1, numeroPokedex);
				
				rs = psNivel.executeQuery();
				rs.next();
				
				System.out.format("%10s %20s %30s", "Nombre", "Tipo de Evolución", "Nivel al que evoluciona");
				System.out.println();
				System.out.format("%10s %10s %20s", rs.getString("pok.Nombre"), rs.getString("tiev.TipoEvolucion"), rs.getInt("niev.Nivel"));
				System.out.println();
				
			}else if (tipoEvolucion==2) {
				String sentenciaPiedra = "SELECT pok.Nombre, tiev.TipoEvolucion, tipi.NombrePiedra FROM tipo_evolucion tiev JOIN forma_evolucion foel ON tiev.IdTipoEvolucion = foel.IdTipoEvolucion JOIN piedra pie ON foel.IdFormaEvolucion = pie.IdFormaEvolucion JOIN tipo_piedra tipi ON pie.IdTipoPiedra = tipi.IdTipoPiedra JOIN pokemon_forma_evolucion pfe ON foel.IdFormaEvolucion = pfe.IdFormaEvolucion JOIN pokemon pok ON pfe.NumeroPokedex = pok.NumeroPokedex WHERE pfe.NumeroPokedex=?;";
				PreparedStatement psPiedra = connection.prepareStatement(sentenciaPiedra);
				psPiedra.setInt(1, numeroPokedex);
				
				rs = psPiedra.executeQuery();
				
				System.out.format("%10s %20s %20s", "Nombre", "Tipo de Evolución", "Piedra necesaria");
				System.out.println();
				
				while(rs.next()) {
								
					System.out.format("%10s %10s %27s", rs.getString("pok.Nombre"), rs.getString("tiev.TipoEvolucion"), rs.getString("tipi.NombrePiedra"));
					System.out.println();
				}
				
			}else if (tipoEvolucion==3) {
				String sentenciaIntercambio = "SELECT pok.Nombre, tiev.TipoEvolucion FROM tipo_evolucion tiev JOIN forma_evolucion foel ON tiev.IdTipoEvolucion = foel.IdTipoEvolucion JOIN pokemon_forma_evolucion pfe ON foel.IdFormaEvolucion = pfe.IdFormaEvolucion JOIN pokemon pok ON pfe.NumeroPokedex = pok.NumeroPokedex WHERE pfe.NumeroPokedex=?;";
				
				PreparedStatement psIntercambio = connection.prepareStatement(sentenciaIntercambio);
				psIntercambio.setInt(1, numeroPokedex);
				
				rs = psIntercambio.executeQuery();
				rs.next();
				
				System.out.format("%10s %20s", "Nombre", "Tipo de Evolución");
				System.out.println();
				System.out.format("%10s %20s", rs.getString("pok.Nombre"), rs.getString("tiev.TipoEvolucion"));
				System.out.println();
				
			}else {
				String sentenciaNoEvolucion = "SELECT pok.Nombre FROM pokemon pok WHERE pok.NumeroPokedex=?";
				
				PreparedStatement psNoEvolucion = connection.prepareStatement(sentenciaNoEvolucion);
				psNoEvolucion.setInt(1, numeroPokedex);
				
				rs = psNoEvolucion.executeQuery();
				rs.next();
				
				System.out.format("%10s %20s", "Nombre", "Tipo de Evolución");
				System.out.println();
				System.out.format("%10s %20s", rs.getString("pok.Nombre"), "No evoluciona");
				System.out.println();
				
			}				
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}				
	}
	
	private void mostrarPokemonArrayList (Connection connection, Scanner sc){
		
			int numeroPokedex;
			ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
			
			System.out.println("Introduce el número del pokemon del que quieres obtener los datos");
			numeroPokedex = sc.nextInt();
			
			try {
				String sentenciaSQL = "SELECT * FROM pokemon";
				Statement ins = connection.createStatement();
				ResultSet rs = ins.executeQuery(sentenciaSQL);
					
				while(rs.next()) {
					pokemons.add(new Pokemon (rs.getInt("NumeroPokedex"), rs.getString("Nombre"), rs.getFloat("Peso"), rs.getFloat("Altura")));
				}	
				
				System.out.println(pokemons.get(numeroPokedex-1));	
			}catch(SQLException e) {
				System.out.println("Excepción: ------- " + e.getLocalizedMessage());
			}								
	}
	
	private void mostrarMasPesados (Connection connection, Scanner sc){
		
			int i;
			ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
			
			System.out.println("Se van a mostrar los 10 pokemons más pesados");
		
			try {
				String sentenciaSQL = "SELECT * FROM pokemon";
				Statement ins = connection.createStatement();
				ResultSet rs = ins.executeQuery(sentenciaSQL);
					
				while(rs.next()) {
					pokemons.add(new Pokemon (rs.getInt("NumeroPokedex"), rs.getString("Nombre"), rs.getFloat("Peso"), rs.getFloat("Altura")));
				}
				
				Collections.sort(pokemons, new Comparator<Pokemon>(){
					
					@Override
					public int compare(Pokemon p1, Pokemon p2) {
						
						return Float.compare(p2.getPeso(), p1.getPeso());
					}							
				});	
				
				for(i=0; i<10; i++) {
					System.out.println(pokemons.get(i));
				}		
			}catch(SQLException e) {
				System.out.println("Excepción: ------- " + e.getLocalizedMessage());
			}
	}
	
	private void mostrarPokemonsApi (Connection connection, BufferedReader br){
		
		int responseCode;
		String linea;
	
		try {
			StringBuilder resultado = new StringBuilder();
			URL url = new URL ("https://pokeapi.co/api/v2/pokemon/?limit=151");

			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");
			responseCode = conexion.getResponseCode();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			
			if(responseCode != 200) {
				System.out.println("Error " + responseCode);
			}else {
				System.out.println("Conexión correcta");
				while((linea = rd.readLine()) != null) {
					resultado.append(linea);
				}
				
				rd.close();
				
				Gson gson = new Gson();
				JsonObject json = gson.fromJson(resultado.toString(), JsonObject.class);
				JsonArray lista = (JsonArray) json.get("results");
				
				for(int i=0; i < lista.size(); i++) {
					JsonObject aux = (JsonObject) lista.get(i);
					System.out.print((i+1) + " " + aux.get("name").getAsString().toUpperCase().charAt(0) + aux.get("name").getAsString().substring(1));
					
					StringBuilder resultadoAll = new StringBuilder();
					url = new URL (aux.get("url").getAsString());
						
					conexion = (HttpURLConnection) url.openConnection();
					conexion.setRequestMethod("GET");
					responseCode = conexion.getResponseCode();
					BufferedReader rdAll = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

					if(responseCode != 200) {
						System.out.println("Error " + responseCode);
						break;
					}else {
						while((linea = rdAll.readLine()) != null) {
							resultadoAll.append(linea);
						}										
					}
					
					rdAll.close();

					json = gson.fromJson(resultadoAll.toString(), JsonObject.class);
					JsonElement peso = (JsonElement) json.get("weight");				
					JsonElement altura = (JsonElement) json.get("height");
					System.out.print(" Peso: " + peso.getAsFloat()/10 + "Kg Altura: " + altura.getAsFloat()/10 + "m\n");
				}
			}
		}catch(IOException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}		
	}
	
	private void modificarNombre(Connection connection, BufferedReader br){
		
		int numeroPokedex=0;
		String nombreOG, nombreNEW;
		
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();		
	
		try {
			String sentenciaSQL = "SELECT * FROM pokemon";
			Statement ins = connection.createStatement();
			ResultSet rs = ins.executeQuery(sentenciaSQL);
				
			while(rs.next()) {
				pokemons.add(new Pokemon (rs.getInt("NumeroPokedex"), rs.getString("Nombre"), rs.getFloat("Peso"), rs.getFloat("Altura")));
			}	
			
			do {
				System.out.println("Introduce el nombre del pokemon a modificar");
				nombreOG = br.readLine();
				
				for (Pokemon pokemon : pokemons) {
					if(pokemon.getNombre().toUpperCase().equals(nombreOG.toUpperCase())) {
						numeroPokedex=pokemon.getNumeroPokedex();
						System.out.println("El pokemon " + nombreOG + " se encuentra en la posición " + numeroPokedex + " de la pokedex");
						break;				
					}else {
						numeroPokedex = -1;
					}
				}
				
				if(numeroPokedex == -1) {
					System.out.println("El pokemon introducido no existe");
					break;
				}
				
			}while(numeroPokedex == -1);
			
			System.out.println("Introduce el nuevo nombre del pokemon");
			nombreNEW = br.readLine();
			
			pokemons.get(numeroPokedex-1).setNombre(nombreNEW);		
			
			System.out.println("Se ha cambiado el nombre de " + nombreOG + " a " + pokemons.get(numeroPokedex-1).getNombre());
			System.out.println(pokemons.get(numeroPokedex-1));
		
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}catch(IOException ioe) {
			System.out.println("Excepción: ------- " + ioe.getLocalizedMessage());
		}
	}
} 