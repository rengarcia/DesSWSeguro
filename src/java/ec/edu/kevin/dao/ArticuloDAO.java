/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.dao;

import ec.edu.kevin.modelo.Articulo;
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
public class ArticuloDAO {

    private Conexion con;
    private Connection connection;

    public ArticuloDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }
    // insertar artículo

    public boolean insertar(Articulo articulo) throws SQLException {
        String sql = "INSERT INTO articulo (codigoArticulo, nombreArticulo, precioArticulo) VALUES (?,?,?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, articulo.getCodigo());
        statement.setString(2, articulo.getNombre());
        statement.setString(3, articulo.getPrecioArticulo());
        if(Double.parseDouble(articulo.getPrecioArticulo())<=0){
            return false;
        }
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    // listar todos los productos
    public List<Articulo> listarArticulos() throws SQLException {

        List<Articulo> listaArticulos = new ArrayList<Articulo>();
        String sql = "SELECT * FROM articulo";
        con.conectar();
        connection = con.getJdbcConnection();
        Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery(sql);
        while (resulSet.next()) {
            int id = resulSet.getInt("idarticulo");
            String codigo = resulSet.getString("codigoarticulo");
            String nombre = resulSet.getString("nombrearticulo");
            String precio = resulSet.getString("precioarticulo");
            Articulo articulo = new Articulo(id, codigo, nombre, precio);
            listaArticulos.add(articulo);
        }
        con.desconectar();
        return listaArticulos;
    }

    // obtener por id
    public Articulo obtenerPorId(int id) throws SQLException {
        Articulo articulo = null;

        String sql = "SELECT * FROM articulo WHERE idarticulo= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet res = statement.executeQuery();
        if (res.next()) {
            articulo = new Articulo(id, res.getString("codigoarticulo"), res.getString("nombrearticulo"),
                    res.getString("precioarticulo"));
        }
        res.close();
        con.desconectar();

        return articulo;
    }

    // actualizar
    public boolean actualizar(Articulo articulo) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE articulo SET codigoarticulo=?,nombrearticulo=?,precioarticulo=? WHERE idarticulo=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, articulo.getCodigo());
        statement.setString(2, articulo.getNombre());
        statement.setString(3, articulo.getPrecioArticulo());
        statement.setInt(4, articulo.getId());
        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminar(Articulo articulo) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM articulo WHERE idarticulo=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, articulo.getId());
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        return rowEliminar;
    }
}
