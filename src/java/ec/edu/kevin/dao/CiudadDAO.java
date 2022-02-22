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
public class CiudadDAO {
    private Conexion con;
	private Connection connection;

	public CiudadDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}

	// insertar artículo
	public boolean insertar(Ciudad ciudad) throws SQLException {
		String sql = "INSERT INTO ciudad (codigociudad, nombreciudad) VALUES (?,?)";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, ciudad.getCodigo());
		statement.setString(2, ciudad.getNombre());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}

	// listar todos los productos
	public List<Ciudad> listarCiudades() throws SQLException {

		List<Ciudad> listaCiudad = new ArrayList<Ciudad>();
		String sql = "SELECT * FROM ciudad";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);

		while (resulSet.next()) {
			int id = resulSet.getInt("idciudad");
			String codigo = resulSet.getString("codigociudad");
			String nombre = resulSet.getString("nombreciudad");
			Ciudad ciudad = new Ciudad(id, codigo, nombre);
			listaCiudad.add(ciudad);
		}
		con.desconectar();
		return listaCiudad;
	}

	// obtener por id
	public Ciudad obtenerPorId(int id) throws SQLException {
		Ciudad ciudad = null;

		String sql = "SELECT * FROM ciudad WHERE idciudad= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet res = statement.executeQuery();
		if (res.next()) {
			ciudad = new Ciudad(id, res.getString("codigociudad"), res.getString("nombreciudad"));
		}
		res.close();
		con.desconectar();

		return ciudad;
	}

	// actualizar
	public boolean actualizar(Ciudad ciudad) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE ciudad SET codigociudad=?,nombreciudad=? WHERE idciudad=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, ciudad.getCodigo());
		statement.setString(2, ciudad.getNombre());
		statement.setInt(3, ciudad.getId());
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Ciudad ciudad) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM ciudad WHERE idciudad=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, ciudad.getId());
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();

		return rowEliminar;
	}
}
