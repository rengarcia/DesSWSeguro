/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author zurit
 */
public class DetalleFactura {
    public int id;
    public int idFactura;
    public String ncliente;
    public int idArticulo;
    public String nArticulo;
    public int cantidad;
    public double precio;

    public DetalleFactura() {
    }

    public DetalleFactura(int id, int idFactura, String ncliente, int idArticulo, String nArticulo, double precio) {
        this.id = id;
        this.idFactura = idFactura;
        this.ncliente = ncliente;
        this.idArticulo = idArticulo;
        this.nArticulo = nArticulo;
        this.precio = precio;
    }

    public DetalleFactura(int id, String nArticulo, int cantidad, double precio) {
        this.id = id;
        this.nArticulo = nArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    
    public DetalleFactura(int id, int idArticulo, int cantidad, double precio) {
        this.id = id;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleFactura(int idArticulo, int cantidad, double precio) {
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleFactura(int id, int idFactura, int idArticulo, String nArticulo, int cantidad, double precio) {
        this.id = id;
        this.idFactura = idFactura;
        this.idArticulo = idArticulo;
        this.nArticulo = nArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }    

    public DetalleFactura(int id, int idFactura, int idArticulo, int cantidad, double precio) {
        this.id = id;
        this.idFactura = idFactura;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getnArticulo() {
        return nArticulo;
    }

    public void setnArticulo(String nArticulo) {
        this.nArticulo = nArticulo;
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

    @Override
    public String toString() {
        return "DetalleFactura{" + "id=" + id + ", idFactura=" + idFactura + ", idArticulo=" + idArticulo + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
    
}
