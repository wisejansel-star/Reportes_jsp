package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
	private static Connection connection;
	
	//DATOS DE BD
	private static String username="root";
	private static String pwd = "PPP-ISI2022UCA@";

	private static String url = "jdbc:mysql://localhost:3306/sakila?useTimeZone=true&serverTimeZone=UTC&autoReconnect=true&useSSL=false";
	
	public static Connection getInstance() throws SQLException, ClassNotFoundException {
		if(connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,pwd);
		}
		return connection;
	}
}
