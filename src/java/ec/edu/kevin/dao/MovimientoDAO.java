/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.dao;

import ec.edu.kevin.modelo.Movimiento;
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
 * @author andre
 */
public class MovimientoDAO {

    private Conexion con;
    private Connection connection;

    public MovimientoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }
    public boolean insertar(Movimiento movimiento) throws SQLException {
        String sql = "INSERT INTO movimiento (codigoMovimiento, nombreMovimiento, signoMovimiento) VALUES (?,?,?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, movimiento.getCodigo());
        statement.setString(2, movimiento.getNombre());
        statement.setString(3, movimiento.getSignoMovimiento());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todos los productos
    public List<Movimiento> listarMovimientos() throws SQLException {

        List<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        String sql = "SELECT * FROM movimiento";
        con.conectar();
        connection = con.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);
        while (resulSet.next()) {
            int id = resulSet.getInt("idmovimiento");
            String codigo = resulSet.getString("codigomovimiento");
            String nombre = resulSet.getString("nombremovimiento");
            String signo = resulSet.getString("signomovimiento");
            Movimiento movimiento = new Movimiento(id, codigo, nombre, signo);
            listaMovimientos.add(movimiento);
        }
        con.desconectar();
        return listaMovimientos;
    }

    // obtener por id
    public Movimiento obtenerPorId(int id) throws SQLException {
        Movimiento movimiento = null;

        String sql = "SELECT * FROM movimiento WHERE idmovimiento= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            movimiento = new Movimiento(id, res.getString("codigomovimiento"), res.getString("nombremovimiento"),
                    res.getString("signomovimiento"));
        }
        res.close();
        con.desconectar();

        return movimiento;
    }

    // actualizar
    public boolean actualizar(Movimiento movimiento) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE movimiento SET codigomovimiento=?,nombremovimiento=?,signomovimiento=? WHERE idmovimiento=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, movimiento.getCodigo());
        statement.setString(2, movimiento.getNombre());
        statement.setString(3, movimiento.getSignoMovimiento());
        statement.setInt(4, movimiento.getId());
        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Movimiento movimiento) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM movimiento WHERE idmovimiento=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, movimiento.getId());
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        return rowEliminar;
    }
    
}
