package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class conexion {

		private static String driver ="com.mysql.jdbc.Driver";
		private static String url ="jdbc:mysql://localhost:6666/dbpokemon";
		private Connection connection;
		
		public conexion() {
			super();
		}

		public void mostrarPokemons () throws ClassNotFoundException, SQLException {
			
			Class.forName(driver);
			connection= DriverManager.getConnection(url, "root", "");
			
			String sentenciaSQL = "SELECT * FROM pokemon";
			Statement ins = connection.createStatement();
			ResultSet rs = ins.executeQuery(sentenciaSQL);
			
			while(rs.next()) {
				System.out.println(rs.getInt("numero_pokedex") + "\t|" + rs.getString("Nombre") + "\t|" + rs.getFloat("Altura") 
				+ "\t|" + rs.getFloat("Peso"));
			}
		}
			
		public void crearPokemon (int numero_pokedex, String Nombre, float Peso, float Altura) throws ClassNotFoundException, SQLException {
			
			Class.forName(driver);
			connection = connection= DriverManager.getConnection(url, "root", "");
			
			String sentenciaSQL = "INSERT INTO pokemon (numero_pokedex, Nombre, Peso, Altura) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			
		}

}


