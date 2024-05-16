/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


public class Token {
    private int idToken;
    private String Email, token;
    private Object fecha_expiracion;

    public Token(int idToken, String Email, String token, Object fecha_expiracion) {
        this.idToken = idToken;
        this.Email = Email;
        this.token = token;
        this.fecha_expiracion = fecha_expiracion;
    }

    public int getIdToken() {
        return idToken;
    }

    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(Object fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }
    
}
