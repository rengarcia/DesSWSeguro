/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author zurit
 */
public class DetalleComprobante {
    public int id;
    public int idComprobante;
    public String nMovimiento;
    public int idArticulo;
    public String nArticulo;
    public int cantidad;
    public double precio;

    public DetalleComprobante() {
    }

    public DetalleComprobante(int id, int idComprobante, String nMovimiento, int idArticulo, String nArticulo, double precio) {
        this.id = id;
        this.idComprobante = idComprobante;
        this.nMovimiento = nMovimiento;
        this.idArticulo = idArticulo;
        this.nArticulo = nArticulo;
        this.precio = precio;
    }

    public DetalleComprobante(int id, String nArticulo, int cantidad, double precio) {
        this.id = id;
        this.nArticulo = nArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleComprobante(int idArticulo, int cantidad, double precio) {
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleComprobante(int id, int idComprobante, int idArticulo, String nArticulo, int cantidad, double precio) {
        this.id = id;
        this.idComprobante = idComprobante;
        this.idArticulo = idArticulo;
        this.nArticulo = nArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }    

    public DetalleComprobante(int id, int idComprobante, int idArticulo, int cantidad, double precio) {
        this.id = id;
        this.idComprobante = idComprobante;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleComprobante(int id, int idArticulo, String nArticulo, int cantidad) {
        this.id = id;
        this.idArticulo = idArticulo;
        this.nArticulo = nArticulo;
        this.cantidad = cantidad;
    }
    

    public String getnArticulo() {
        return nArticulo;
    }

    public void setnArticulo(String nArticulo) {
        this.nArticulo = nArticulo;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getnMovimiento() {
        return nMovimiento;
    }

    public void setnMovimiento(String nMovimiento) {
        this.nMovimiento = nMovimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
