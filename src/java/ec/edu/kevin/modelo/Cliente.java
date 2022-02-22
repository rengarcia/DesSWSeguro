/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author zurit
 */
public class Cliente {
    public int id;
    public String nombre;
    public String rucCliente;
    public String direccion;

    public Cliente() {
    }

    public Cliente(int id, String Nombre, String RUC, String Direccion) {
        this.id = id;
        this.nombre = Nombre;
        this.rucCliente = RUC;
        this.direccion = Direccion;
    }

    public Cliente(String Nombre, String RUC, String Direccion) {
        this.nombre = Nombre;
        this.rucCliente = RUC;
        this.direccion = Direccion;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", rucCliente=" + rucCliente + ", direccion=" + direccion + '}';
    }
    
}
