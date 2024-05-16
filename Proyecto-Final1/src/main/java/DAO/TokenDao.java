package DAO;

import conexion.bd.conexionbd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


    public class TokenDao {

        public static void guardarToken(String Email, String token, Timestamp fechaExpiracion) {
            conexionbd bdc = conexionbd.getInstancia();
            Connection conn = bdc.obtenerConexion();

            try {
                String query = "INSERT INTO tokens (Email, token, fecha_expiracion) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, Email);
                statement.setString(2, token);
                statement.setTimestamp(3, fechaExpiracion);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }

        public static String obtenerContraseña(String Email, String token) {
            conexionbd bdc = conexionbd.getInstancia();
            Connection conn = bdc.obtenerConexion();
            String Password = null;

            try {
                String sql = "SELECT c.Password "
                        + "FROM tokens t "
                        + "INNER JOIN cliente c ON t.Email = c.email "
                        + "WHERE t.Email = ? AND t.token = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, Email);
                statement.setString(2, token);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Password = resultSet.getString("Password");

                    // Registro de depuración para imprimir el token recuperado
                    System.out.println("Email obtenido del formulario de HTML: " + Email);
                    System.out.println("Token recuperado de la base de datos: " + token);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } 
           
            return Password;
        }
    }
