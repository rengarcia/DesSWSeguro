/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author zurit
 */
public class Auditoria {
    public String IP;
    public String fecha;
    public String actividad;
    public String operacion;
    public String usuario;
    public String pantalla;
    public String hora;

    public Auditoria() {
    }

    public Auditoria(String usuario, String pantalla, String hora) {
        this.usuario = usuario;
        this.pantalla = pantalla;
        this.hora = hora;
    }

    public Auditoria(String IP, String fecha, String actividad, String operacion, String usuario, String pantalla, String hora) {
        this.IP = IP;
        this.fecha = fecha;
        this.actividad = actividad;
        this.operacion = operacion;
        this.usuario = usuario;
        this.pantalla = pantalla;
        this.hora = hora;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Auditoria{" + "IP=" + IP + ", fecha=" + fecha + ", actividad=" + actividad + ", operacion=" + operacion + ", usuario=" + usuario + ", pantalla=" + pantalla + ", hora=" + hora + '}';
    }
    
    
}
