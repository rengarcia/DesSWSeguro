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
public class ComprobanteDAO {

    private Conexion con;
    private Connection connection;

    public ComprobanteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public int insertarCabecera(CabeceraComprobante cabecera) throws SQLException {
        int idComprobante = 0;
        con.conectar();
        connection = con.getJdbcConnection();
        String id = "SELECT * FROM cabeceracomprobante";
        Statement statement_id = connection.createStatement();
        ResultSet resulSet = statement_id.executeQuery(id);
        if (resulSet.next() == false) {
            idComprobante = 1;
        } else {
            idComprobante = resulSet.getInt("idcomprobante");
            while (resulSet.next()) {
                int temp = resulSet.getInt("idcomprobante");
                if (temp > idComprobante) {
                    idComprobante = temp;
                }
            }
            idComprobante += 1;
        }
        String sql = "INSERT INTO cabeceracomprobante (idcomprobante, fechacomprobante, idmovimiento) VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idComprobante);
        statement.setString(2, cabecera.getFecha());
        statement.setInt(3, cabecera.getIdMovimiento());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        if (rowInserted) {
            return idComprobante;
        }
        return -1;
    }

    public boolean insertarDetalle(int id, DetalleComprobante detalle) throws SQLException {
        int idDetalle = 0;
        con.conectar();
        connection = con.getJdbcConnection();
        String idF = "SELECT * FROM detallecomprobante";
        Statement statement_id = connection.createStatement();
        ResultSet resulSet = statement_id.executeQuery(idF);
        if (resulSet.next() == false) {
            idDetalle = 1;
        } else {
            idDetalle = resulSet.getInt("iddetalle");
            while (resulSet.next()) {
                int temp = resulSet.getInt("iddetalle");
                if (temp > idDetalle) {
                    idDetalle = temp;
                }
            }
            idDetalle += 1;
        }
        String sql = "INSERT INTO detallecomprobante (iddetalle, idcomprobante, idarticulo, cantidaddetalle, preciodetalle) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idDetalle);
        statement.setInt(2, id);
        statement.setInt(3, detalle.getIdArticulo());
        statement.setInt(4, detalle.getCantidad());
        statement.setString(5, String.valueOf(detalle.getPrecio()));

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    public List<CabeceraComprobante> listarCabeceras() throws SQLException {
        List<CabeceraComprobante> listaCabeceras = new ArrayList<CabeceraComprobante>();
        String sql = "SELECT * FROM cabeceracomprobante";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        while (res.next()) {
            String sMovimiento = "";
            String nMovimiento = "";
            int idMovimiento = res.getInt("idmovimiento");
            int id = res.getInt("idcomprobante");
            String fecha = res.getString("fechacomprobante");
            sql = "SELECT * FROM movimiento WHERE idmovimiento= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idMovimiento);
            ResultSet rest = statement.executeQuery();
            if (rest.next()) {
                nMovimiento = rest.getString("nombremovimiento");
                sMovimiento = rest.getString("signomovimiento");
            }
            rest.close();
            CabeceraComprobante cabecera = new CabeceraComprobante(id, fecha, idMovimiento, nMovimiento, sMovimiento);
            listaCabeceras.add(cabecera);
            rest.close();
        }
        con.desconectar();
        return listaCabeceras;
    }

    // obtener por id
    public CabeceraComprobante obtenerPorIdCabecera(int id) throws SQLException {
        CabeceraComprobante cabecera = null;
        String sql = "SELECT * FROM cabeceracomprobante WHERE idcomprobante= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            String sMovimiento = "";
            String nMovimiento = "";
            int idMovimiento = res.getInt("idmovimiento");
            String fecha = res.getString("fechacomprobante");
            sql = "SELECT * FROM movimiento WHERE idmovimiento= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idMovimiento);
            ResultSet rest = statement.executeQuery();
            if (rest.next()) {
                nMovimiento = rest.getString("nombremovimiento");
                sMovimiento = rest.getString("signomovimiento");
            }
            rest.close();
            cabecera = new CabeceraComprobante(id, fecha, idMovimiento, nMovimiento, sMovimiento);
        }
        res.close();
        con.desconectar();
        return cabecera;
    }

    public DetalleComprobante obtenerPorDetalle(int id) throws SQLException {
        DetalleComprobante detalle = null;
        String sql = "SELECT * FROM detallecomprobante WHERE iddetalle= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            String nArticulo = "";
            int cantidad = res.getInt("cantidaddetalle");
            int idComprobante = res.getInt("idcomprobante");
            int idarticulo = res.getInt("idarticulo");
            double precio = Double.parseDouble(res.getString("preciodetalle"));
            String sql1 = "SELECT * FROM articulo WHERE idarticulo= ? ";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, idarticulo);
            ResultSet result = statement1.executeQuery();
            if (result.next()) {
                nArticulo = result.getString("nombrearticulo");
            }
            detalle = new DetalleComprobante(id, idComprobante, idarticulo, nArticulo, cantidad, precio);
            result.close();
        }
        res.close();
        con.desconectar();
        return detalle;
    }

    public List<DetalleComprobante> obtenerPorIdDetalle(int id) throws SQLException {
        List<DetalleComprobante> detalles = new ArrayList<DetalleComprobante>();
        String sql = "SELECT * FROM detallecomprobante WHERE idcomprobante= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        while (res.next()) {
            String nArticulo = "";
            int cantidad = res.getInt("cantidaddetalle");
            int idDetalle = res.getInt("iddetalle");
            int idarticulo = res.getInt("idarticulo");
            double precio = Double.parseDouble(res.getString("preciodetalle"));
            String sql1 = "SELECT * FROM articulo WHERE idarticulo= ? ";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, idarticulo);
            ResultSet result = statement1.executeQuery();
            if (result.next()) {
                nArticulo = result.getString("nombrearticulo");
            }
            DetalleComprobante detalle = new DetalleComprobante(idDetalle, id, idarticulo, nArticulo, cantidad, precio);
            detalles.add(detalle);
            result.close();
        }
        res.close();
        con.desconectar();
        return detalles;
    }

    // actualizar
    public boolean actualizarCabecera(CabeceraComprobante cabecera) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE cabeceracomprobante SET fechacomprobante=?, idmovimiento=? WHERE idcomprobante=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cabecera.getFecha());
        statement.setInt(2, cabecera.getIdMovimiento());
        statement.setInt(3, cabecera.getId());
        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    public boolean actualizarDetalle(DetalleComprobante detalle) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE detallecomprobante SET idarticulo=?, cantidaddetalle=?, preciodetalle=? WHERE iddetalle=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, detalle.getIdArticulo());
        statement.setInt(2, detalle.getCantidad());
        statement.setString(3, String.valueOf(detalle.getPrecio()));
        statement.setInt(4, detalle.getId());
        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    //eliminar
    public boolean eliminarCabecera(int id) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM cabeceracomprobante WHERE idcomprobante=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        return rowEliminar;
    }

    public boolean eliminarDetalle(int id) throws SQLException {
        boolean rowEliminar = false;
        String sql = "DELETE FROM detallecomprobante WHERE idcomprobante=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        return rowEliminar;
    }

    public List<ReporteSaldoArticulo> reporteVentas() throws SQLException {
        List<ReporteSaldoArticulo> reportes = new ArrayList<ReporteSaldoArticulo>();
        List<DetalleComprobante> listaDetalles = new ArrayList<DetalleComprobante>();
        String sql = "SELECT * FROM detallecomprobante";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        while (res.next()) {
            String nArticulo = "";
            int idDetalle = res.getInt("iddetalle");
            int idArticulo = res.getInt("idarticulo");
            int cantidad = res.getInt("cantidaddetalle");
            sql = "SELECT * FROM articulo WHERE idarticulo= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idArticulo);
            ResultSet rest = statement.executeQuery();
            if (rest.next()) {
                nArticulo = rest.getString("nombrearticulo");
            }
            rest.close();
            DetalleComprobante detalle = new DetalleComprobante(idDetalle, idArticulo, nArticulo, cantidad);
            if (reportes.isEmpty()) {
                ReporteSaldoArticulo reporte = new ReporteSaldoArticulo(detalle.getIdArticulo(), detalle.getnArticulo(), detalle.getCantidad());
                reportes.add(reporte);
            } else {
                boolean flag = false;
                for (int j = 0; j < reportes.size(); j++) {
                    if (detalle.getIdArticulo() == reportes.get(j).getId()) {
                        flag = true;
                        reportes.get(j).setCantidad(reportes.get(j).getCantidad() + detalle.getCantidad());
                    }
                }
                if (!flag) {
                    ReporteSaldoArticulo reporte = new ReporteSaldoArticulo(detalle.getIdArticulo(), detalle.getnArticulo(), detalle.getCantidad());
                    reportes.add(reporte);
                }
            }
            rest.close();
        }

        res.close();
        con.desconectar();
        return reportes;
    }

    public String[][] reportecruzado() throws SQLException {
        List<DetalleComprobante> listaDetalles = new ArrayList<DetalleComprobante>();
        List<Integer> idsMovimientos = new ArrayList<Integer>();
        List<String> nombresMovimientos = new ArrayList<String>();
        List<Integer> idsArticulos = new ArrayList<Integer>();
        List<String> nombresArticulos = new ArrayList<String>();
        String sql = "SELECT * FROM detallecomprobante";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        while (res.next()) {
            String nArticulo = "";
            String nMovimiento = "";
            int idDetalle = res.getInt("iddetalle");
            int idArticulo = res.getInt("idarticulo");
            int idComprobante = res.getInt("idcomprobante");
            double precio = Double.parseDouble(res.getString("preciodetalle"));
            int idMovimiento = 0;
            sql = "SELECT * FROM articulo WHERE idarticulo= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idArticulo);
            ResultSet rest = statement.executeQuery();
            if (rest.next()) {
                nArticulo = rest.getString("nombrearticulo");
            }
            rest.close();
            sql = "SELECT * FROM cabeceracomprobante WHERE idcomprobante = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idComprobante);
            rest = statement.executeQuery();
            if (rest.next()) {
                idMovimiento = rest.getInt("idmovimiento");
            }
            rest.close();
            sql = "SELECT * FROM movimiento WHERE idmovimiento = ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idMovimiento);
            rest = statement.executeQuery();
            if (rest.next()) {
                nMovimiento = rest.getString("nombremovimiento");
            }
            DetalleComprobante detalle = new DetalleComprobante(idDetalle, idMovimiento, nMovimiento, idArticulo, nArticulo, precio);
            listaDetalles.add(detalle);
            rest.close();
        }
        res.close();
        sql = "SELECT * FROM movimiento";
        statement = connection.prepareStatement(sql);
        res = statement.executeQuery();
        while (res.next()) {
            idsMovimientos.add(res.getInt("idmovimiento"));
            nombresMovimientos.add(res.getString("nombremovimiento"));
        }
        res.close();
        sql = "SELECT * FROM articulo";
        statement = connection.prepareStatement(sql);
        res = statement.executeQuery();
        while (res.next()) {
            idsArticulos.add(res.getInt("idarticulo"));
            nombresArticulos.add(res.getString("nombrearticulo"));
        }
        res.close();
        int x = (idsMovimientos.size() + 1);
        System.out.println(x);
        int y = (idsArticulos.size() + 1);
        System.out.println(y);
        String matriz[][] = new String[y][x];
        matriz[0][0] = "/";
        for (int i = 1; i < x; i++) {
            matriz[0][i] = nombresMovimientos.get(i - 1);
        }
        for (int i = 1; i < y; i++) {
            matriz[i][0] = nombresArticulos.get(i - 1);
        }
        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                matriz[i][j] = "0";
            }
        }
        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                for (int k = 0; k < listaDetalles.size(); k++) {
                    if (idsMovimientos.get(j - 1) == listaDetalles.get(k).idComprobante && idsArticulos.get(i - 1) == listaDetalles.get(k).getIdArticulo()) {
                        matriz[i][j] = String.valueOf(Double.parseDouble(matriz[i][j]) + listaDetalles.get(k).getPrecio());
                    }
                }
            }
        }
        con.desconectar();
        return matriz;
    }
}
