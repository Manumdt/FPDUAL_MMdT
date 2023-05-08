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
				System.out.println(rs.getInt("NumeroPokedex") + "\t|" + rs.getString("Nombre") + "\t|" + rs.getFloat("Altura") 
				+ "\t|" + rs.getFloat("peso"));
			}
		}
			
		public void crearPokemon (int NumeroPokedex, String nombre, float peso, float altura) throws ClassNotFoundException, SQLException {
			
			Class.forName(driver);
			connection = connection= DriverManager.getConnection(url, "root", "");
			
			String sentenciaSQL = "INSERT INTO pokemon (NumeroPokedex, Nombre, Peso, Altura) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sentenciaSQL);
			
		}

}


