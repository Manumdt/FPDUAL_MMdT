package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url ="jdbc:mysql://localhost:6666/dbpokemon";
	private Connection connection;	

	public void mostarPokemons() throws ClassNotFoundException, SQLException {
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, "root", "");
		
		String sentenciaSQL = "SELECT * FROM pokemon";
		Statement ins = connection.createStatement();
		ResultSet rs = ins.executeQuery(sentenciaSQL);
		
		System.out.format("%5s %10s", "NumeroPokedex", "Nombre");
		System.out.println();
		
		while(rs.next()) {
			System.out.format("%5s %20s",rs.getInt("NumeroPokedex"), rs.getString("Nombre"));
			System.out.println();
		}
				
	}
	
	public int actualizarPokemon (int numeroPokedex, String nombre) throws ClassNotFoundException, SQLException {
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, "root", "");
		
		String sentenciaSQL = "UPDATE pokemon SET Nombre=? WHERE NumeroPokedex=?";
		PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
		ps.setString(1, nombre);
		ps.setInt(2,  numeroPokedex);
		
		System.out.println("Nombre modificado correctamente");
		
		return (ps.executeUpdate());
	}
	
	public int borrarPokemon (int numeroPokedex) throws ClassNotFoundException, SQLException {
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, "root", "");
		
		String sentenciaSQL = "DELETE FROM pokemon WHERE NumeroPokedex=?";
		PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
		ps.setInt(1,  numeroPokedex);
		
		System.out.println("Se ha eliminado el pokemon " + numeroPokedex + " correctamente");
		
		return (ps.executeUpdate());
	}
	
	public int crearPokemon (String nombre, float peso, float altura) throws ClassNotFoundException, SQLException {
		
		int numeroPokedex;
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, "root", "");
		
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
		
		return (ps.executeUpdate());
	}
	
	public void mostrarStats(int numeroPokedex) throws ClassNotFoundException, SQLException{
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, "root", "");
		
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

	}
	
	public void mostrarAprendizajeMovimientos(int numeroPokedex, int formaAprendizaje) throws ClassNotFoundException, SQLException{
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, "root", "");
		
		String sentenciaSQL = "SELECT mov.Nombre FROM movimiento mov JOIN pokemon_movimiento_forma pmf ON mov.IdMovimiento = pmf.IdMovimiento JOIN forma_aprendizaje foap ON pmf.IdFormaAprendizaje = foap.IdFormaAprendizaje WHERE foap.IdTipoAprendizaje = ? AND pmf.NumeroPokedex=?;";
		PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
		ps.setInt(1, formaAprendizaje);
		ps.setInt(2, numeroPokedex);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("mov.Nombre"));	
		}				
	}
	
	public void mostrarTipoEvolucion(int numeroPokedex) throws ClassNotFoundException, SQLException {
		
		int tipoEvolucion;
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, "root", "");
		
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
				
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		int numeroPokedex, opcion;
		float peso, altura;
		String nombre;
		boolean salir=true;
		
		Scanner sc = new Scanner(System.in);
		Main mn = new Main();

		do {
			
			System.out.println("----------------------- MENÚ PRINCIPAL -----------------------");
			System.out.println("1. Mostrar todos los pokemons");
			System.out.println("2. Actualizar el nombre del pokemon por su numero de pokedex");
			System.out.println("3. Borrar pokemon");
			System.out.println("4. Insertar un nuevo pokemon");
			System.out.println("5. Mostrar estadísticas");
			System.out.println("6. Aprendizaje de movimientos");
			System.out.println("7. Tipo de evolución");
			System.out.println("8. SALIR");
			System.out.println("--------------------------------------------------------------");
			System.out.println("Ingrese una opcion:");
			opcion = sc.nextInt();
			
			switch(opcion) {
				case 1:
					System.out.println("Se van a mostar a continuación todos los pokemons");
					mn.mostarPokemons();					
					break;
					
				case 2:													
					System.out.println("Has elegido modificar el nombre de un pokemon");
					System.out.println("Introduce el numero de pokedex del pokemon a modificar:");
					numeroPokedex = sc.nextInt();
					System.out.println("Introduce el nuevo nombre:");
					nombre = sc.next();
					
					mn.actualizarPokemon(numeroPokedex, nombre);
					break;
					
				case 3:
					System.out.println("Has elegido eliminar un pokemon");
					System.out.println("Introduce el numero de pokedex del pokemon a modificar:");
					numeroPokedex = sc.nextInt();
					
					mn.borrarPokemon(numeroPokedex);
					break;
					
				case 4:
					System.out.println("Has elegido insertar un pokemon");
					System.out.println("Introduce el nombre");
					nombre = sc.next();
					System.out.println("Introduce el peso");
					peso = sc.nextFloat();
					System.out.println("Introduce la altura");
					altura = sc.nextFloat();
					
					mn.crearPokemon(nombre, peso, altura);
					break;
					
				case 5:
					System.out.println("Has elegido mostrar estadísticas");
					System.out.println("Introduce el numero de pokedex del pokemon deseado:");
					numeroPokedex = sc.nextInt();
					
					mn.mostrarStats(numeroPokedex);
					break;
					
				case 6:
					System.out.println("Has elegido aprendizaje de movimientos");
					System.out.println("Introduce el numero de pokedex del pokemon deseado:");
					numeroPokedex = sc.nextInt();
					System.out.println("Que tipo de aprendizaje quieres comprobar");
					System.out.println("1. Mt");
					System.out.println("2. Mo");
					System.out.println("3. Nivel");
					opcion=sc.nextInt();
					
					mn.mostrarAprendizajeMovimientos(numeroPokedex, opcion);
					
					break;
					
				case 7:
					System.out.println("Has elegido tipo de evolución");
					System.out.println("Introduce el numero de pokedex del pokemon deseado:");
					numeroPokedex = sc.nextInt();
					
					mn.mostrarTipoEvolucion(numeroPokedex);
					
					break;
					
				case 8:
					System.exit(0);
					break;					
			}
			
		}while(salir==true);
		
	}

}
