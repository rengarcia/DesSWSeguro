/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.dao;

import ec.edu.kevin.modelo.*;
import ec.edu.kevin.modelo.Conexion;
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
public class AuditoriaDAO {
    
    
	private Conexion con;
	private Connection connection;

	public AuditoriaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}

	// insertar artículo
	public boolean insertar(Auditoria auditoria) throws SQLException {
            System.out.println("LLege aqui");
		String sql = "INSERT INTO auditoria (ip, fecha, hora, actividad, lugar, operacion, usuario) VALUES (?,?,?,?,?,?,encrypt_decrypt.encrypt(?))";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, auditoria.getIP());
		statement.setString(2, auditoria.getFecha());
		statement.setString(3, auditoria.getHora());
		statement.setString(4, auditoria.getActividad());
		statement.setString(5, auditoria.getPantalla());
		statement.setString(6, auditoria.getOperacion());
		statement.setString(7, auditoria.getUsuario());
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}

	// listar todos los productos
	public List<Auditoria> listarAuditoria() throws SQLException {

		List<Auditoria> listaAuditorias = new ArrayList<Auditoria>();
		String sql = "SELECT encrypt_decrypt.decrypt(usuario),ip,fecha,hora,actividad,lugar,operacion FROM auditoria";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
		while (resulSet.next()) {
			String usuario = resulSet.getString("encrypt_decrypt.decrypt(usuario)");
			String ip = resulSet.getString("ip");
			String fecha = resulSet.getString("fecha");
			String hora = resulSet.getString("hora");
			String lugar = resulSet.getString("lugar");
			String actividad = resulSet.getString("actividad");
			String operacion = resulSet.getString("operacion");
			Auditoria auditoria = new Auditoria(ip, fecha, actividad, operacion, usuario, lugar, hora);
			listaAuditorias.add(auditoria);
		}
		con.desconectar();
		return listaAuditorias;
	}

}
