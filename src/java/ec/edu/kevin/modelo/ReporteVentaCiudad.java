/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author zurit
 */
public class ReporteVentaCiudad {
    public int id;
    public String nombre;
    public double ventas;

    public ReporteVentaCiudad(int id, String nombre, double ventas) {
        this.id = id;
        this.nombre = nombre;
        this.ventas = ventas;
    }

    public ReporteVentaCiudad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }
}
