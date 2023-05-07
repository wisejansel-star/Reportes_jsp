package datos;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Conexion 
{
	private static HikariDataSource ds;
	private static HikariConfig config = new HikariConfig();
	
	//DATOS DE BD
	private static String username="root";
	private static String pwd = "1234";
	private static String host = "localhost";
	private static String db = "sakila";
	private static String url = "jdbc:mysql://"+host+":3306/"+db+"?serverTimeZone=UTC";
	
	static {
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(pwd);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}
	
	private Conexion() {}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection(); 
	}
	
	public static void main (String[] args) throws SQLException{
		Connection con = ds.getConnection();
		try 
		{	
			if(con != null) 
			{
				JOptionPane.showMessageDialog(null, "Conectado");
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Error al conectar con BD");
			}
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		finally 
		{
			try 
			{
				con.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
	}
}
