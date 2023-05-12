package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	private static final String driver="com.mysql.jdbc.Driver";
	private static final String bbdd="jdbc:mysql://localhost:6666/dbpokemon";
	private static final String user ="root";
	private static final String password="";
	
	public static Connection conexion() {
		
		Connection connection = null;
		
		try {
			
			Class.forName(driver);
			connection = DriverManager.getConnection(bbdd, user, password);
			
		}catch(Exception e) {
			System.out.println("Excepción: ------- " + e.getLocalizedMessage());
		}
		
		return connection;
	}
}