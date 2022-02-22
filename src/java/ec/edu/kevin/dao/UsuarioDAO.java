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
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author zurit
 */
public class UsuarioDAO {

    private Conexion con;
    private Connection connection;

    public UsuarioDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public int ingresar(Usuario usuario) throws SQLException {

        String sql = "SELECT * FROM usuario WHERE usuario = encrypt_decrypt.encrypt(?) AND password = encrypt_decrypt.encrypt(?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getUser());
        statement.setString(2, usuario.getPass());
        ResultSet resulSet = statement.executeQuery();
        while (resulSet.next()) {
            System.out.println("Valio");
            int nBase = Integer.parseInt(resulSet.getString(4));
            sql = "UPDATE usuario SET nsesiones = ? WHERE usuario = encrypt_decrypt.encrypt(?) AND password = encrypt_decrypt.encrypt(?)";
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setString(1, String.valueOf(nBase + 1));
            statement1.setString(2, usuario.getUser());
            statement1.setString(3, usuario.getPass());
            statement1.executeUpdate();
            statement1.close();
            statement.close();
            con.desconectar();
            return nBase + 1;
        }
        System.out.println("Nel");
        statement.close();
        con.desconectar();
        return -1;
    }

    public boolean insertar(Usuario usuario) throws SQLException {
        int idUsuario = 0;
        con.conectar();
        connection = con.getJdbcConnection();
        String id = "SELECT * FROM usuario";
        Statement statement_id = connection.createStatement();
        ResultSet resulSet = statement_id.executeQuery(id);
        if (resulSet.next() == false) {
            idUsuario = 1;
        } else {
            do {
                System.out.println(resulSet.getInt("codigo"));
                int temp = resulSet.getInt("codigo");
                if (temp > idUsuario) {
                    idUsuario = temp;
                }
            } while (resulSet.next());
            idUsuario += 1;
        }
        String sql = "INSERT INTO usuario (usuario, password, correo, codigo, nsesiones) VALUES (encrypt_decrypt.encrypt(?),encrypt_decrypt.encrypt(?), encrypt_decrypt.encrypt(?), ?, ?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getUser());
        statement.setString(2, usuario.getPass());
        statement.setString(3, usuario.getCorreo());
        statement.setInt(4, idUsuario);
        statement.setString(5, "0");
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        con.desconectar();
        return rowInserted;
    }

    public void cerrado(Usuario usuario) throws SQLException {

        String sql = "SELECT * FROM usuario WHERE usuario = encrypt_decrypt.encrypt(?) AND password = encrypt_decrypt.encrypt(?)";
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getUser());
        statement.setString(2, usuario.getPass());
        ResultSet resulSet = statement.executeQuery();
        while (resulSet.next()) {
            int nBase = Integer.parseInt(resulSet.getString(4));
            sql = "UPDATE usuario SET nsesiones = ? WHERE usuario = encrypt_decrypt.encrypt(?) AND password = encrypt_decrypt.encrypt(?)";
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setString(1, String.valueOf(nBase - 1));
            statement1.setString(2, usuario.getUser());
            statement1.setString(3, usuario.getPass());
            statement1.executeUpdate();
            statement1.close();
        }
        statement.close();
        con.desconectar();
    }

    public boolean valirdarHora_kmzh(Usuario usuario_kmzh) throws SQLException {
        String sql_kmzh = "SELECT * FROM usuario";
        con.conectar();
        connection = con.getJdbcConnection();
        Statement statement_kmzh = connection.createStatement();
        ResultSet resulSet_kmzh = statement_kmzh.executeQuery(sql_kmzh);
        Calendar calendario_kmzh = Calendar.getInstance();
        int hora_kmzh = calendario_kmzh.get(Calendar.HOUR_OF_DAY);
        int minutos_kmzh = calendario_kmzh.get(Calendar.MINUTE);
        int segundos_kmzh = calendario_kmzh.get(Calendar.SECOND);
        while (resulSet_kmzh.next()) {
            String uBase_kmzh = resulSet_kmzh.getString(1);
            String pBase_kmzh = resulSet_kmzh.getString(2);
            int nBase_kmzh = Integer.parseInt(resulSet_kmzh.getString(4));
            String dBase_kmzh = resulSet_kmzh.getString(6);
            String hBase_kmzh = resulSet_kmzh.getString(7);
            if (usuario_kmzh.getUser().equals(uBase_kmzh) && usuario_kmzh.getPass().equals(pBase_kmzh)) {
                String[] tiempo1_kmzh = dBase_kmzh.split(":");
                String[] tiempo2_kmzh = hBase_kmzh.split(":");
                if (hora_kmzh > Integer.parseInt(tiempo1_kmzh[0]) && hora_kmzh < Integer.parseInt(tiempo2_kmzh[0])) {
                    statement_kmzh.close();
                    con.desconectar();
                    return true;
                } else if ((hora_kmzh == Integer.parseInt(tiempo1_kmzh[0])) || (hora_kmzh == Integer.parseInt(tiempo2_kmzh[0]))) {
                    if (hora_kmzh == Integer.parseInt(tiempo1_kmzh[0])) {
                        if (minutos_kmzh > Integer.parseInt(tiempo1_kmzh[1])) {
                            statement_kmzh.close();
                            con.desconectar();
                            return true;
                        }
                    } else {
                        if (minutos_kmzh < Integer.parseInt(tiempo2_kmzh[1])) {
                            statement_kmzh.close();
                            con.desconectar();
                            return true;
                        }
                    }
                } else if ((hora_kmzh == Integer.parseInt(tiempo1_kmzh[0]) && minutos_kmzh == Integer.parseInt(tiempo1_kmzh[1])) || (hora_kmzh == Integer.parseInt(tiempo2_kmzh[0]) && minutos_kmzh == Integer.parseInt(tiempo2_kmzh[1]))) {
                    if (hora_kmzh == Integer.parseInt(tiempo1_kmzh[0]) && minutos_kmzh == Integer.parseInt(tiempo1_kmzh[1])) {
                        if (segundos_kmzh > Integer.parseInt(tiempo1_kmzh[2])) {
                            statement_kmzh.close();
                            con.desconectar();
                            return true;
                        }
                    } else {
                        if (segundos_kmzh < Integer.parseInt(tiempo2_kmzh[2])) {
                            statement_kmzh.close();
                            con.desconectar();
                            return true;
                        }
                    }
                }
            }
        }
        statement_kmzh.close();
        con.desconectar();
        return false;
    }
}
