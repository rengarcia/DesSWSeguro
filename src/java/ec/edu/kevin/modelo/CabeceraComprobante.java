/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author zurit
 */
public class CabeceraComprobante {
    public int id;
    public String fecha;
    public int idMovimiento;
    public String nombreMovimiento;
    public String signoMovimiento;

    public CabeceraComprobante() {
    }

    public CabeceraComprobante(int id, String fecha, int idMovimiento) {
        this.id = id;
        this.fecha = fecha;
        this.idMovimiento = idMovimiento;
    }

    public CabeceraComprobante(String fecha, int idMovimiento) {
        this.fecha = fecha;
        this.idMovimiento = idMovimiento;
    }

    public CabeceraComprobante(int id, String fecha, int idMovimiento, String nombreMovimiento, String signoMovimiento) {
        this.id = id;
        this.fecha = fecha;
        this.idMovimiento = idMovimiento;
        this.nombreMovimiento = nombreMovimiento;
        this.signoMovimiento = signoMovimiento;
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

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    public String getSignoMovimiento() {
        return signoMovimiento;
    }

    public void setSignoMovimiento(String signoMovimiento) {
        this.signoMovimiento = signoMovimiento;
    }
    
    
}
