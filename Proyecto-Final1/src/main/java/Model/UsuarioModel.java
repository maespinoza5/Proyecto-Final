/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ferna
 */
public class UsuarioModel {
    private int coduser;
    private String nombre, apellido, mail, password;
    private Object fechacreacion;

    public UsuarioModel(int coduser, String nombre, String apellido, String mail, String password, Object fechacreacion) {
        this.coduser = coduser;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.password = password;
        this.fechacreacion = fechacreacion;
    }

    public UsuarioModel() {
        
    }

    public int getCoduser() {
        return coduser;
    }

    public void setCoduser(int coduser) {
        this.coduser = coduser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Object fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public UsuarioModel obtenerContraseñaePorEmail(String email, String tokenIngresado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
