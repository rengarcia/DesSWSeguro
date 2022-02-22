/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author andre
 */
public class Articulo {

   

    public int id;
    public String codigo;
    public String nombre;
    public String precioArticulo;

    public Articulo() {

    }

    public Articulo(int id, String codigoArticulo, String nombreArticulo, String precioArticulo) {
        this.id = id;
        this.codigo = codigoArticulo;
        this.nombre = nombreArticulo;
        this.precioArticulo = precioArticulo;
    }

    public Articulo(String codigoArticulo, String nombreArticulo, String precioArticulo) {
        this.codigo = codigoArticulo;
        this.nombre = nombreArticulo;
        this.precioArticulo = precioArticulo;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(String precioArticulo) {
        this.precioArticulo = precioArticulo;
    }
}
