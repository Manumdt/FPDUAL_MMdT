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
						// Se cierra la conexión
						cn.close();
						salir=false;
						break;
				}
			}while(salir==true);
	}

	private static int menu(Scanner sc) {
		
		int opcion;
		
		do {
			System.out.println("----------------------- MENÚ PRINCIPAL -----------------------");
			System.out.println("1. Mostrar todos los pokemons");
			System.out.println("2. Actualizar el Nombre del pokemon por su numero de pokedex");
			System.out.println("3. Borrar pokemon");
			System.out.println("4. Insertar un nuevo pokemon");
			System.out.println("5. Mostrar estadísticas");
			System.out.println("6. Aprendizaje de movimientos");
			System.out.println("7. Tipo de evolución");
			System.out.println("8. Mostrar un solo pokemon (ArrayList)");
			System.out.println("9. Ver pokemons más pesados (ArrayList)");
			System.out.println("10. Mostrar pokemons (API)");
			System.out.println("11. Modificar el Nombre de un pokemon (ArrayList)");
			System.out.println("12. SALIR");
			System.out.println("--------------------------------------------------------------");
			System.out.println("Ingrese una opcion:");
			opcion = sc.nextInt();
			
			if(opcion < 1 || opcion > 12) {
				System.out.println("El valor introducido no está contemplado");
			}
		}while(opcion < 1 || opcion > 12);
		return opcion;
	}
	
	protected static boolean comprobarPokemons(Connection connection, int numero_pokedex) {
		
		int numero_pokedexMin, numero_pokedexMax;
		boolean noExiste = true;
		
		try {
			
			String sentenciaSQL = "SELECT numero_pokedex FROM pokemon ORDER BY numero_pokedex LIMIT 1;";
			Statement ins = connection.createStatement();
			ResultSet rs = ins.executeQuery(sentenciaSQL);
			
			rs.next();
			numero_pokedexMin= rs.getInt("numero_pokedex");
			
			sentenciaSQL = "SELECT numero_pokedex FROM pokemon ORDER BY numero_pokedex DESC LIMIT 1;";
			ins = connection.createStatement();
			rs = ins.executeQuery(sentenciaSQL);
			
			rs.next();
			numero_pokedexMax= rs.getInt("numero_pokedex");
			
			if(numero_pokedex < numero_pokedexMin || numero_pokedex > numero_pokedexMax) {
				System.out.println("El pokemon introducido no existe en la pokedex");
				noExiste = false;
			}
			
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}
		return noExiste;
	}
	
	private void mostarPokemons(Connection connection){
			
		try {
			String sentenciaSQL = "SELECT * FROM pokemon";
			Statement ins = connection.createStatement();
			ResultSet rs = ins.executeQuery(sentenciaSQL);
				
			System.out.println("Se van a mostar a continuación todos los pokemons");
			System.out.format("%5s %12s", "numero_pokedex", "Nombre");
			System.out.println();

			while(rs.next()) {
				System.out.format("%5s %20s",rs.getInt("numero_pokedex"), rs.getString("Nombre"));
				System.out.println();
			}
			
			System.out.println();
			System.out.format("%5s %12s", "numero_pokedex", "Nombre");
			System.out.println();
			
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}
	}
	
	private void actualizarPokemon (Connection connection, Scanner sc){
		
		int numero_pokedex;
		String Nombre;
		boolean comprobar;

		try {
			System.out.println("Has elegido modificar el Nombre de un pokemon");
			
			do {
				System.out.println("Introduce el numero de pokedex del pokemon a modificar:");
				numero_pokedex = sc.nextInt();
				comprobar=Main.comprobarPokemons(connection, numero_pokedex);
		
			}while(comprobar==false);
			
			System.out.println("Introduce el nuevo Nombre:");
			Nombre = sc.next();
			
			String sentenciaSQL = "UPDATE pokemon SET Nombre=? WHERE numero_pokedex=?";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			ps.setString(1, Nombre);
			ps.setInt(2,  numero_pokedex);
			
			System.out.println("Nombre modificado correctamente");
			
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}
	}
	
	private void borrarPokemon (Connection connection, Scanner sc){
		
		int numero_pokedex;
		boolean comprobar;
		
		try {
			System.out.println("Has elegido eliminar un pokemon");
			
			do {
				System.out.println("Introduce el numero de pokedex del pokemon a eliminar:");
				numero_pokedex = sc.nextInt();
				comprobar=Main.comprobarPokemons(connection, numero_pokedex);
				
			}while(comprobar==false);
			
			String sentenciaSQL = "DELETE FROM pokemon WHERE numero_pokedex=?";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			ps.setInt(1,  numero_pokedex);
			
			System.out.println("Se ha eliminado el pokemon " + numero_pokedex + " correctamente");
			
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}
	}
	
	private void crearPokemon (Connection connection,Scanner sc){
		
		int numero_pokedex;
		float Peso, Altura;
		String Nombre;
		
		try {
			System.out.println("Has elegido insertar un pokemon");
			System.out.println("Introduce el Nombre");
			Nombre = sc.next();
			System.out.println("Introduce el Peso");
			Peso = sc.nextFloat();
			System.out.println("Introduce la Altura");
			Altura = sc.nextFloat();
			
			String sentenciaSQL1 = "SELECT numero_pokedex FROM pokemon ORDER BY numero_pokedex DESC LIMIT 1;";
			Statement ins = connection.createStatement();
			ResultSet rs = ins.executeQuery(sentenciaSQL1);
			
			rs.next();
			numero_pokedex= rs.getInt("numero_pokedex")+1;
			
			String sentenciaSQL2 = "INSERT INTO pokemon (numero_pokedex, Nombre, Peso, Altura) VALUES (?, ?, ?, ?);";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL2);
			ps.setInt(1, numero_pokedex);
			ps.setString(2, Nombre);
			ps.setFloat(3, Peso);
			ps.setFloat(4, Altura);
			
			System.out.println("Pokemon insertado correctamente");
			
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}
	}
	
	private void mostrarStats(Connection connection, Scanner sc){
		
		int numero_pokedex;
		boolean comprobar;
		
		try {
			System.out.println("Has elegido mostrar estadísticas");
			
			do {
				System.out.println("Introduce el numero de pokedex del pokemon deseado:");
				numero_pokedex = sc.nextInt();
				comprobar=Main.comprobarPokemons(connection, numero_pokedex);
				
			}while(comprobar==false);
			
			String sentenciaSQL = "SELECT pok.Nombre, eb.* FROM pokemon pok JOIN estadisticas_base eb ON pok.numero_pokedex = eb.numero_pokedex WHERE pok.numero_pokedex = ?";
			
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			ps.setInt(1, numero_pokedex);
			
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
		
		int numero_pokedex, formaAprendizaje;
		boolean comprobar;
		
		try {
			System.out.println("Has elegido aprendizaje de movimientos");
			do {
				System.out.println("Introduce el numero de pokedex del pokemon deseado:");
				numero_pokedex = sc.nextInt();
				comprobar=Main.comprobarPokemons(connection, numero_pokedex);
		
			}while(comprobar==false);
			
			System.out.println("Que tipo de aprendizaje quieres comprobar");
			System.out.println("1. Mt");
			System.out.println("2. Mo");
			System.out.println("3. Nivel");
			formaAprendizaje=sc.nextInt();
			
			// Según el tipo de aprendizaje se tiene que realizar una consulta distinta de SQL
			if(formaAprendizaje == 1) {
				String sentenciaSQL = "SELECT CONCAT(mt.Mt,  ' ',  mov.Nombre) AS MT FROM movimiento mov JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento = pmf.IdMovimiento JOIN forma_aprendizaje foap ON pmf.IdFormaAprendizaje = foap.IdFormaAprendizaje JOIN mt ON foap.IdFormaAprendizaje = mt.IdFormaAprendizaje WHERE foap.IdTipoAprendizaje = ? AND pmf.numero_pokedex=?;";
				PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
				ps.setInt(1, formaAprendizaje);
				ps.setInt(2, numero_pokedex);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("MT"));	
				}				
			}else if(formaAprendizaje == 2) {
				String sentenciaSQL = "SELECT CONCAT(mo.Mo,  ' ',  mov.Nombre) AS MO FROM movimiento mov JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento = pmf.IdMovimiento JOIN forma_aprendizaje foap ON pmf.IdFormaAprendizaje = foap.IdFormaAprendizaje JOIN mo ON foap.IdFormaAprendizaje = mo.IdFormaAprendizaje WHERE foap.IdTipoAprendizaje = ? AND pmf.numero_pokedex=?;";
				PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
				ps.setInt(1, formaAprendizaje);
				ps.setInt(2, numero_pokedex);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("MO"));	
				}		
			}else if(formaAprendizaje == 3){
				String sentenciaSQL = "SELECT mov.Nombre, niap.Nivel  FROM movimiento mov JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento = pmf.IdMovimiento JOIN forma_aprendizaje foap ON pmf.IdFormaAprendizaje = foap.IdFormaAprendizaje JOIN nivel_aprendizaje niap ON foap.IdFormaAprendizaje = niap.IdFormaAprendizaje WHERE foap.IdTipoAprendizaje = ? AND pmf.numero_pokedex=? ORDER BY 2;";
				PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
				ps.setInt(1, formaAprendizaje);
				ps.setInt(2, numero_pokedex);
				
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
		
		int numero_pokedex, tipoEvolucion;
		boolean comprobar;
		
		System.out.println("Has elegido tipo de evolución");
		
		do {
			System.out.println("Introduce el numero de pokedex del pokemon deseado:");
			numero_pokedex = sc.nextInt();
			comprobar=Main.comprobarPokemons(connection, numero_pokedex);
	
		}while(comprobar==false);
		
		try {					
			String sentenciaSQL = "SELECT tiev.IdTipoEvolucion FROM tipo_evolucion tiev JOIN forma_evolucion foel ON tiev.IdTipoEvolucion = foel.IdTipoEvolucion JOIN pokemon_forma_evolucion pfe ON foel.IdFormaEvolucion = pfe.IdFormaEvolucion WHERE pfe.numero_pokedex=?";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			ps.setInt(1, numero_pokedex);
			
			ResultSet rs = ps.executeQuery();

			if(rs.next()==true) {
				tipoEvolucion = rs.getInt("tiev.IdTipoEvolucion");
			}else {
				// Es necesario igualarlo a 0 porque sino va a dar un error al estar null
				tipoEvolucion = 0;
			}		
			
			// Según el tipo de evolución se tiene que realizar una consulta distinta de SQL
			if(tipoEvolucion==1) {
				String sentenciaNivel= "SELECT pok.Nombre, tiev.TipoEvolucion, niev.nivel FROM tipo_evolucion tiev JOIN forma_evolucion foel ON tiev.IdTipoEvolucion = foel.IdTipoEvolucion JOIN nivel_evolucion niev ON foel.IdFormaEvolucion = niev.IdFormaEvolucion JOIN pokemon_forma_evolucion pfe ON foel.IdFormaEvolucion = pfe.IdFormaEvolucion JOIN pokemon pok ON pfe.numero_pokedex = pok.numero_pokedex WHERE pfe.numero_pokedex= ?;";
				PreparedStatement psNivel = connection.prepareStatement(sentenciaNivel);
				psNivel.setInt(1, numero_pokedex);
				
				rs = psNivel.executeQuery();
				rs.next();
				
				System.out.format("%5s %20s %30s", "Nombre", "Tipo de Evolución", "Nivel al que evoluciona");
				System.out.println();
				System.out.format("%5s %10s %20s", rs.getString("pok.Nombre"), rs.getString("tiev.TipoEvolucion"), rs.getInt("niev.Nivel"));
				System.out.println();
				
			}else if (tipoEvolucion==2) {
				String sentenciaPiedra = "SELECT pok.Nombre, tiev.TipoEvolucion, tipi.NombrePiedra FROM tipo_evolucion tiev JOIN forma_evolucion foel ON tiev.IdTipoEvolucion = foel.IdTipoEvolucion JOIN piedra pie ON foel.IdFormaEvolucion = pie.IdFormaEvolucion JOIN tipo_piedra tipi ON pie.IdTipoPiedra = tipi.IdTipoPiedra JOIN pokemon_forma_evolucion pfe ON foel.IdFormaEvolucion = pfe.IdFormaEvolucion JOIN pokemon pok ON pfe.numero_pokedex = pok.numero_pokedex WHERE pfe.numero_pokedex=?;";
				PreparedStatement psPiedra = connection.prepareStatement(sentenciaPiedra);
				psPiedra.setInt(1, numero_pokedex);
				
				rs = psPiedra.executeQuery();
				
				System.out.format("%5s %20s %20s", "Nombre", "Tipo de Evolución", "Piedra necesaria");
				System.out.println();
				
				while(rs.next()) {
								
					System.out.format("%5s %10s %30s", rs.getString("pok.Nombre"), rs.getString("tiev.TipoEvolucion"), rs.getString("tipi.NombrePiedra"));
					System.out.println();
				}
				
			}else if (tipoEvolucion==3) {
				String sentenciaIntercambio = "SELECT pok.Nombre, tiev.TipoEvolucion FROM tipo_evolucion tiev JOIN forma_evolucion foel ON tiev.IdTipoEvolucion = foel.IdTipoEvolucion JOIN pokemon_forma_evolucion pfe ON foel.IdFormaEvolucion = pfe.IdFormaEvolucion JOIN pokemon pok ON pfe.numero_pokedex = pok.numero_pokedex WHERE pfe.numero_pokedex=?;";
				
				PreparedStatement psIntercambio = connection.prepareStatement(sentenciaIntercambio);
				psIntercambio.setInt(1, numero_pokedex);
				
				rs = psIntercambio.executeQuery();
				rs.next();
				
				System.out.format("%5s %20s", "Nombre", "Tipo de Evolución");
				System.out.println();
				System.out.format("%5s %20s", rs.getString("pok.Nombre"), rs.getString("tiev.TipoEvolucion"));
				System.out.println();
				
			}else {
				String sentenciaNoEvolucion = "SELECT pok.Nombre FROM pokemon pok WHERE pok.numero_pokedex=?";
				
				PreparedStatement psNoEvolucion = connection.prepareStatement(sentenciaNoEvolucion);
				psNoEvolucion.setInt(1, numero_pokedex);
				
				rs = psNoEvolucion.executeQuery();
				rs.next();
				
				System.out.format("%5s %20s", "Nombre", "Tipo de Evolución");
				System.out.println();
				System.out.format("%5s %20s", rs.getString("pok.Nombre"), "No evoluciona");
				System.out.println();
				
			}				
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}				
	}
	
	private void mostrarPokemonArrayList (Connection connection, Scanner sc){
		
			int numero_pokedex;
			ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
			
			System.out.println("Introduce el número del pokemon del que quieres obtener los datos");
			numero_pokedex = sc.nextInt();
			
			try {
				String sentenciaSQL = "SELECT * FROM pokemon";
				Statement ins = connection.createStatement();
				ResultSet rs = ins.executeQuery(sentenciaSQL);
					
				while(rs.next()) {
					pokemons.add(new Pokemon (rs.getInt("numero_pokedex"), rs.getString("Nombre"), rs.getFloat("Peso"), rs.getFloat("Altura")));
				}	
				
				System.out.println(pokemons.get(numero_pokedex-1));	
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
					pokemons.add(new Pokemon (rs.getInt("numero_pokedex"), rs.getString("Nombre"), rs.getFloat("Peso"), rs.getFloat("Altura")));
				}
				
				// Método comparator
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

			// Se abre la conexión con la API pública
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			// Se establece el tipo de respuesta que se espera
			conexion.setRequestMethod("GET");
			// Se obtiene el código correspondiente a la conexión para ver errores
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
				// Se parsea el StringBuilder  JsonObject
				JsonObject json = gson.fromJson(resultado.toString(), JsonObject.class);
				// Se parsea el JsonObject a JsonArray para poder tratarlo
				JsonArray lista = (JsonArray) json.get("results");
				
				System.out.format("%5s %12s %11s %10s", "numero_pokedex", "Nombre", "Altura", "Peso");
				System.out.println();
				
				for(int i=0; i < lista.size(); i++) {
					JsonObject aux = (JsonObject) lista.get(i);
					System.out.format("%2s %23s" ,(i+1), (aux.get("name").getAsString().toUpperCase().charAt(0) + aux.get("name").getAsString().substring(1)));
					
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
					// En este caso el StringBuilder se pasa a JsonElement porque la respuesta recibida no es un array sino un float
					JsonElement Peso = (JsonElement) json.get("weight");				
					JsonElement Altura = (JsonElement) json.get("height");
					System.out.format("%12s %10s", Peso.getAsFloat()/10,  Altura.getAsFloat()/10);
					System.out.println();
				}
				System.out.format("%5s %13s %11s %10s", "numero_pokedex", "Nombre", "Altura", "Peso");
				System.out.println();
			}
		}catch(IOException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}		
	}
	
	private void modificarNombre(Connection connection, BufferedReader br){
		
		int numero_pokedex=0;
		String NombreOG, NombreNEW;
		
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();		
	
		try {
			String sentenciaSQL = "SELECT * FROM pokemon";
			Statement ins = connection.createStatement();
			ResultSet rs = ins.executeQuery(sentenciaSQL);
				
			while(rs.next()) {
				pokemons.add(new Pokemon (rs.getInt("numero_pokedex"), rs.getString("Nombre"), rs.getFloat("Peso"), rs.getFloat("Altura")));
			}	
			
			do {
				System.out.println("Introduce el Nombre del pokemon a modificar");
				NombreOG = br.readLine();
				
				for (Pokemon pokemon : pokemons) {
					if(pokemon.getNombre().toUpperCase().equals(NombreOG.toUpperCase())) {
						numero_pokedex=pokemon.getnumero_pokedex();
						System.out.println("El pokemon " + NombreOG + " se encuentra en la posición " + numero_pokedex + " de la pokedex");
						break;				
					}else {
						System.out.println("El pokemon introducido no existe");
						// Se establece como -1 para poder tratarlo en el while
						numero_pokedex = -1;
						break;
					}
				}
				
			}while(numero_pokedex == -1);
			
			System.out.println("Introduce el nuevo Nombre del pokemon");
			NombreNEW = br.readLine();
			
			pokemons.get(numero_pokedex-1).setNombre(NombreNEW);		
			
			System.out.println("Se ha cambiado el Nombre de " + NombreOG + " a " + pokemons.get(numero_pokedex-1).getNombre());
			System.out.println(pokemons.get(numero_pokedex-1));
		
		}catch(SQLException e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}catch(IOException ioe) {
			System.out.println("Excepción: ------- " + ioe.getLocalizedMessage());
		}
	}
} 