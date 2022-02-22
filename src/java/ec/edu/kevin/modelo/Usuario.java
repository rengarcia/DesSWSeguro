/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.modelo;

/**
 *
 * @author zurit
 */
public class Usuario {
    public String user;
    public String pass;
    public String correo;
    public String codigo;

    public Usuario() {
    }

    public Usuario(String user) {
        this.user = user;
    }

    public Usuario(String user, String pass, String correo, String codigo) {
        this.user = user;
        this.pass = pass;
        this.correo = correo;
        this.codigo = codigo;
    }
    
    public Usuario(String user, String pass) {
        this.user = user;
        this.pass = pass;
        
    }
    
    public Usuario(String user, String pass, String mail) {
        this.user = user;
        this.pass = pass;
        this.correo = mail;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
