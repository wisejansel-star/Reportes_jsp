package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vistas.VW_Customer_List;

import javax.swing.*;

public class DT_VW_Customer_List {
	
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		return Conexion.getInstance();
	}
	
	public ArrayList<VW_Customer_List> listaDeClientes(){
		ArrayList<VW_Customer_List> lista_clientes = new ArrayList<VW_Customer_List>();
		String sql = "SELECT distinct(country) as 'Pais' FROM sakila.customer_list";;
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				VW_Customer_List customers = new VW_Customer_List();
				customers.setCountry(rs.getString("Pais"));

				lista_clientes.add(customers);
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		return lista_clientes;
	}
}
