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
public class FacturaDAO {

    private Conexion con;
    private Connection connection;

    public FacturaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // insertar artículo
    public int insertarCabecera(CabeceraFactura cabecera) throws SQLException {
        int idFactura = 0;
        con.conectar();
        connection = con.getJdbcConnection();
        String id = "SELECT * FROM cabecerafactura";
        Statement statement_id = connection.createStatement();
        ResultSet resulSet = statement_id.executeQuery(id);
        if (resulSet.next() == false) {
            idFactura = 1;
        } else {
            idFactura = resulSet.getInt("idfactura");
            while (resulSet.next()) {
                int temp = resulSet.getInt("idfactura");
                if (temp > idFactura) {
                    idFactura = temp;
                }
            }
            idFactura += 1;
        }
        String sql = "INSERT INTO cabecerafactura (idfactura, fechafactura, idciudad, idcliente) VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idFactura);
        statement.setString(2, cabecera.getFecha());
        statement.setInt(3, cabecera.getIdCiudad());
        statement.setInt(4, cabecera.getIdCliente());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        if (rowInserted) {
            return idFactura;
        }
        return -1;
    }

    public boolean insertarDetalle(int id, DetalleFactura detalle) throws SQLException {
        int idDetalle = 0;
        con.conectar();
        connection = con.getJdbcConnection();
        String idF = "SELECT * FROM detallefactura";
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
        String sql = "INSERT INTO detallefactura (iddetalle, idfactura, idarticulo, cantidaddetalle, preciodetalle) VALUES (?,?,?,?,?)";
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

    public List<CabeceraFactura> listarCabeceras() throws SQLException {
        List<CabeceraFactura> listaCabeceras = new ArrayList<CabeceraFactura>();
        String sql = "SELECT * FROM cabecerafactura";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        while (res.next()) {
            String nCliente = "";
            String nCiudad = "";
            int idCiudad = res.getInt("idciudad");
            int id = res.getInt("idfactura");
            int idCliente = res.getInt("idcliente");
            String fecha = res.getString("fechafactura");
            sql = "SELECT * FROM ciudad WHERE idciudad= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCiudad);
            ResultSet rest = statement.executeQuery();
            if (rest.next()) {
                nCiudad = rest.getString("nombreciudad");
            }
            rest.close();
            sql = "SELECT * FROM cliente WHERE idcliente= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);
            rest = statement.executeQuery();
            if (rest.next()) {
                nCliente = rest.getString("nombrecliente");
            }
            CabeceraFactura cabecera = new CabeceraFactura(id, fecha, idCiudad, nCiudad, idCliente, nCliente);
            listaCabeceras.add(cabecera);
            rest.close();
        }
        con.desconectar();
        return listaCabeceras;
    }

    // obtener por id
    public CabeceraFactura obtenerPorIdCabecera(int id) throws SQLException {
        CabeceraFactura cabecera = null;
        String sql = "SELECT * FROM cabecerafactura WHERE idfactura= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            String nCliente = "";
            String nCiudad = "";
            int idcliente = res.getInt("idcliente");
            int idciudad = res.getInt("idciudad");
            String fecha = res.getString("fechafactura");
            String sql1 = "SELECT * FROM ciudad WHERE idciudad= ? ";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, idciudad);
            ResultSet result1 = statement1.executeQuery();
            if (result1.next()) {
                nCiudad = result1.getString("nombreciudad");
            }
            result1.close();
            String sql2 = "SELECT * FROM cliente WHERE idcliente= ? ";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1, idcliente);
            result1 = statement2.executeQuery();
            if (result1.next()) {
                nCliente = result1.getString("nombrecliente");
            }
            result1.close();
            cabecera = new CabeceraFactura(id, fecha, idciudad, nCiudad, idcliente, nCliente);
            cabecera.toString();
        }
        res.close();
        con.desconectar();
        return cabecera;
    }

    public DetalleFactura obtenerPorDetalle(int id) throws SQLException {
        DetalleFactura detalle = null;
        String sql = "SELECT * FROM detallefactura WHERE iddetalle= ? ";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            String nArticulo = "";
            int cantidad = res.getInt("cantidaddetalle");
            int idFactura = res.getInt("idfactura");
            int idarticulo = res.getInt("idarticulo");
            double precio = Double.parseDouble(res.getString("preciodetalle"));
            String sql1 = "SELECT * FROM articulo WHERE idarticulo= ? ";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, idarticulo);
            ResultSet result = statement1.executeQuery();
            if (result.next()) {
                nArticulo = result.getString("nombrearticulo");
            }
            detalle = new DetalleFactura(id, idFactura, idarticulo, nArticulo, cantidad, precio);
            result.close();
        }
        res.close();
        con.desconectar();
        return detalle;
    }

    public List<DetalleFactura> obtenerPorIdDetalle(int id) throws SQLException {
        List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
        String sql = "SELECT * FROM detallefactura WHERE idfactura= ? ";
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
            DetalleFactura detalle = new DetalleFactura(idDetalle, id, idarticulo, nArticulo, cantidad, precio);
            detalles.add(detalle);
            result.close();
        }
        res.close();
        con.desconectar();
        return detalles;
    }

    // actualizar
    public boolean actualizarCabecera(CabeceraFactura cabecera) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE cabecerafactura SET fechafactura=?,idciudad=?, idcliente=? WHERE idfactura=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cabecera.getFecha());
        statement.setInt(2, cabecera.getIdCiudad());
        statement.setInt(3, cabecera.getIdCliente());
        statement.setInt(4, cabecera.getId());
        rowActualizar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowActualizar;
    }

    public boolean actualizarDetalle(DetalleFactura detalle) throws SQLException {
        boolean rowActualizar = false;
        String sql = "UPDATE detallefactura SET idarticulo=?, cantidaddetalle=?, preciodetalle=? WHERE iddetalle=?";
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
        String sql = "DELETE FROM cabecerafactura WHERE idfactura=?";
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
        String sql = "DELETE FROM detallefactura WHERE idfactura=?";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        rowEliminar = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();

        return rowEliminar;
    }

    public List<ReporteVentaCiudad> reporteVentas() throws SQLException {
        List<ReporteVentaCiudad> reportes = new ArrayList<ReporteVentaCiudad>();
        List<CabeceraFactura> listaCabeceras = new ArrayList<CabeceraFactura>();
        String sql = "SELECT * FROM cabecerafactura";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        while (res.next()) {
            String nCliente = "";
            String nCiudad = "";
            int idCiudad = res.getInt("idciudad");
            int id = res.getInt("idfactura");
            int idCliente = res.getInt("idcliente");
            String fecha = res.getString("fechafactura");
            sql = "SELECT * FROM ciudad WHERE idciudad= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCiudad);
            ResultSet rest = statement.executeQuery();
            if (rest.next()) {
                nCiudad = rest.getString("nombreciudad");
            }
            rest.close();
            sql = "SELECT * FROM cliente WHERE idcliente= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);
            rest = statement.executeQuery();
            if (rest.next()) {
                nCliente = rest.getString("nombrecliente");
            }
            CabeceraFactura cabecera = new CabeceraFactura(id, fecha, idCiudad, nCiudad, idCliente, nCliente);
            listaCabeceras.add(cabecera);
            rest.close();
        }
        for (int i = 0; i < listaCabeceras.size(); i++) {
            double tFactura = 0;
            String sql_detalle = "SELECT * FROM detallefactura WHERE idfactura= ? ";
            PreparedStatement statement_detalle = connection.prepareStatement(sql_detalle);
            statement_detalle.setInt(1, listaCabeceras.get(i).getId());
            ResultSet res_detalle = statement_detalle.executeQuery();
            while (res_detalle.next()) {
                tFactura = tFactura + res_detalle.getDouble("preciodetalle");
            }
            if (reportes.isEmpty()) {
                ReporteVentaCiudad reporte = null;
                String sql_ciudad = "SELECT * FROM ciudad WHERE idciudad= ? ";
                PreparedStatement statement_ciudad = connection.prepareStatement(sql_ciudad);
                statement_ciudad.setInt(1, listaCabeceras.get(i).getIdCiudad());
                ResultSet res_ciudad = statement_ciudad.executeQuery();
                if (res_ciudad.next()) {
                    reporte = new ReporteVentaCiudad(listaCabeceras.get(i).idCiudad, res_ciudad.getString("nombreciudad"), tFactura);
                }
                reportes.add(reporte);
            } else {
                boolean flag = false;
                for (int j = 0; j < reportes.size(); j++) {
                    if (listaCabeceras.get(i).getIdCiudad() == reportes.get(j).getId()) {
                        flag = true;
                        reportes.get(j).setVentas(reportes.get(j).getVentas() + tFactura);
                    }
                }
                if (!flag) {
                    ReporteVentaCiudad reporte = null;
                    String sql_ciudad = "SELECT * FROM ciudad WHERE idciudad= ? ";
                    PreparedStatement statement_ciudad = connection.prepareStatement(sql_ciudad);
                    statement_ciudad.setInt(1, listaCabeceras.get(i).getIdCiudad());
                    ResultSet res_ciudad = statement_ciudad.executeQuery();
                    if (res_ciudad.next()) {
                        reporte = new ReporteVentaCiudad(listaCabeceras.get(i).idCiudad, res_ciudad.getString("nombreciudad"), tFactura);
                    }
                    reportes.add(reporte);
                }
            }
        }
        res.close();
        con.desconectar();
        return reportes;
    }

    public String[][] reportecruzado() throws SQLException {
        List<DetalleFactura> listaDetalles = new ArrayList<DetalleFactura>();
        List<Integer> idsClientes = new ArrayList<Integer>();
        List<String> nombresClientes = new ArrayList<String>();
        List<Integer> idsArticulos = new ArrayList<Integer>();
        List<String> nombresArticulos = new ArrayList<String>();
        String sql = "SELECT * FROM detallefactura";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        while (res.next()) {
            String nArticulo = "";
            String nCliente = "";
            int idDetalle = res.getInt("iddetalle");
            int idArticulo = res.getInt("idarticulo");
            int idFactura = res.getInt("idfactura");
            double precio = Double.parseDouble(res.getString("preciodetalle"));
            int idCliente = 0;
            sql = "SELECT * FROM articulo WHERE idarticulo= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idArticulo);
            ResultSet rest = statement.executeQuery();
            if (rest.next()) {
                nArticulo = rest.getString("nombrearticulo");
            }
            rest.close();
            sql = "SELECT * FROM cabecerafactura WHERE idfactura = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idFactura);
            rest = statement.executeQuery();
            if (rest.next()) {
                idCliente = rest.getInt("idcliente");
            }
            rest.close();
            sql = "SELECT * FROM cliente WHERE idcliente = ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCliente);
            rest = statement.executeQuery();
            if (rest.next()) {
                nCliente = rest.getString("nombrecliente");
            }
            DetalleFactura detalle = new DetalleFactura(idDetalle, idCliente, nCliente, idArticulo, nArticulo, precio);
            listaDetalles.add(detalle);
            rest.close();
        }
        res.close();
        sql = "SELECT * FROM cliente";
        statement = connection.prepareStatement(sql);
        res = statement.executeQuery();
        while (res.next()) {
            idsClientes.add(res.getInt("idcliente"));
            nombresClientes.add(res.getString("nombrecliente"));
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
        int x = (idsClientes.size() + 1);
        System.out.println(x);
        int y = (idsArticulos.size() + 1);
        System.out.println(y);
        String matriz[][] = new String[y][x];
        matriz[0][0] = "/";
        for (int i = 1; i < x; i++) {
            matriz[0][i] = nombresClientes.get(i - 1);
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
                    if (idsClientes.get(j-1) == listaDetalles.get(k).getIdFactura() && idsArticulos.get(i-1) == listaDetalles.get(k).getIdArticulo()) {
                        matriz[i][j] = String.valueOf(Double.parseDouble(matriz[i][j]) + listaDetalles.get(k).getPrecio());
                    }
                }
            }
        }
        con.desconectar();
        return matriz;
    }
}
