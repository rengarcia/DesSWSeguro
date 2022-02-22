/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.dao;

import ec.edu.kevin.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zurit
 */
public class ClienteDAO {
    
	private Conexion con;
	private Connection connection;

	public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}

	// insertar artículo
	public boolean insertar(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO cliente (nombreCliente, ruccliente, direccioncliente) VALUES (?,?,?)";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cliente.getNombre());
		statement.setString(2, cliente.getRucCliente());
		statement.setString(3, cliente.getDireccion());
                if(cliente.getRucCliente().length()!=13){
                    return false;
                }
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}

	// listar todos los productos
	public List<Cliente> listarClientes() throws SQLException {

		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String sql = "SELECT * FROM CLIENTE";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
		while (resulSet.next()) {
			int id = resulSet.getInt("idcliente");
			String nombre = resulSet.getString("nombrecliente");
			String ruc = resulSet.getString("ruccliente");
			String direccion = resulSet.getString("direccioncliente");
			Cliente cliente = new Cliente(id, nombre, ruc, direccion);
			listaClientes.add(cliente);
		}
		con.desconectar();
		return listaClientes;
	}

	// obtener por id
	public Cliente obtenerPorId(int id) throws SQLException {
		Cliente cliente = null;

		String sql = "SELECT * FROM cliente WHERE idcliente= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet res = statement.executeQuery();
		if (res.next()) {
			cliente = new Cliente(id, res.getString("nombrecliente"), res.getString("ruccliente"),
					res.getString("direccioncliente"));
		}
		res.close();
		con.desconectar();

		return cliente;
	}

	// actualizar
	public boolean actualizar(Cliente cliente) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE cliente SET nombrecliente=?,ruccliente=?,direccioncliente=? WHERE idcliente=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cliente.getNombre());
		statement.setString(2, cliente.getRucCliente());
		statement.setString(3, cliente.getDireccion());
		statement.setInt(4, cliente.getId());
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Cliente cliente) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM cliente WHERE idcliente=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, cliente.getId());
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();

		return rowEliminar;
	}
}
