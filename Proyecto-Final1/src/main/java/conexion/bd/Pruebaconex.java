/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conexion.bd;

/**
 *
 * @author ferna
 */
public class Pruebaconex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        conexionbd bdc = conexionbd.getInstancia();
        bdc.obtenerConexion();

    }
    
}
