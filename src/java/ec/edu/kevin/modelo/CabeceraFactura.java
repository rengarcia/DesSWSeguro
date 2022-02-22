/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author zurit
 */
public class CabeceraFactura {
    public int id;
    public String fecha;
    public int idCiudad;
    public String nombreCiudad;
    public int idCliente;
    public String nombreCliente;
    
    public CabeceraFactura() {
    }

    public CabeceraFactura(int id, String fecha, int idCiudad, int idCliente) {
        this.id = id;
        this.fecha = fecha;
        this.idCiudad = idCiudad;
        this.idCliente = idCliente;
    }

    public CabeceraFactura(String fecha, int idCiudad, int idCliente) {
        this.fecha = fecha;
        this.idCiudad = idCiudad;
        this.idCliente = idCliente;
    }

    public CabeceraFactura(int id, String fecha, String nCiudad, String nCliente) {
        this.id = id;
        this.fecha = fecha;
        this.nombreCiudad = nCiudad;
        this.nombreCliente = nCliente;
    }

    public CabeceraFactura(int id, String fecha, int idCiudad, String nCiudad, int idCliente, String nCliente) {
        this.id = id;
        this.fecha = fecha;
        this.idCiudad = idCiudad;
        this.nombreCiudad = nCiudad;
        this.idCliente = idCliente;
        this.nombreCliente = nCliente;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }   

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    @Override
    public String toString() {
        return "CabeceraFactura{" + "id=" + id + ", fecha=" + fecha + ", idCiudad=" + idCiudad + ", nombreCiudad=" + nombreCiudad + ", idCliente=" + idCliente + ", nombreCliente=" + nombreCliente + '}';
    }
    
}
