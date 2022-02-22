/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author andre
 */
public class Movimiento {

    public int id;
    public String codigo;
    public String nombre;
    public String signoMovimiento;

    public Movimiento() {
    }
    
    public Movimiento(int id, String codigoMovimiento, String nombreMovimiento, String signoMovimiento){
        this.id = id;
        this.codigo= codigoMovimiento;
        this.nombre= nombreMovimiento;
        this.signoMovimiento= signoMovimiento; 
    }
    
    public Movimiento(String codigoMovimiento, String nombreMovimiento, String signoMovimiento){
        this.codigo= codigoMovimiento;
        this.nombre= nombreMovimiento;
        this.signoMovimiento= signoMovimiento; 
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

    public String getSignoMovimiento() {
        return signoMovimiento;
    }

    public void setSignoMovimiento(String signoMovimiento) {
        this.signoMovimiento = signoMovimiento;
    }
    
}

