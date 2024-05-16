
package DAO;

import Model.UsuarioInfo;
import Model.UsuarioModel;
import conexion.bd.conexionbd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteDao {
   private Connection getConnection() throws SQLException {
        return conexionbd.getInstancia().obtenerConexion();
    }


    public UsuarioModel obtenerContraseñaePorEmail(String email) {
        UsuarioModel contra = null;
        String sql = "SELECT contraseña FROM cliente WHERE email = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                contra = new UsuarioModel();
                contra.setMail(email);
                contra.setPassword(resultSet.getString("contraseña"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return contra;
    }
    
   public UsuarioInfo obtenerCliente(String email) {
    UsuarioInfo usuario = null;
        String strSQL = "SELECT idCliente, Dni, Nombres, Direccion, email, Password FROM cliente WHERE email=?";
        
        try (Connection connection = getConnection(); 
             PreparedStatement pstmt = connection.prepareStatement(strSQL)) {
            
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioInfo();
                    usuario.setId(rs.getInt("idCliente"));
                    usuario.setDni(rs.getString("Dni"));
                    usuario.setNombre(rs.getString("Nombres"));
                    usuario.setDireccion(rs.getString("Direccion"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("Password"));

                    // Imprimir los valores obtenidos para verificar
                    System.out.println("ID: " + usuario.getId());
                    System.out.println("DNI: " + usuario.getDni());
                    System.out.println("Nombre: " + usuario.getNombre());
                    // y así sucesivamente...
                } else {
                    // Imprimir mensaje si no se encontró el cliente
                    System.out.println("No se encontró el cliente con email: " + email);
                }
            }
        } catch (SQLException e) {
            System.out.println("Excepción SQL: " + e.toString());
        } catch (Exception e) {
            System.out.println("Excepción: " + e.toString());
        }
        return usuario;
}
   
public boolean ActualizarCliente(UsuarioInfo usuario) {
    boolean exito =false;
    try {
        // Obtener la conexión a la base de datos
        Connection connection = getConnection();

        if (connection != null) {
            // Sentencia SQL para actualizar los datos del usuario
            String strSQL = "UPDATE cliente SET Dni=?, Nombres=?, Direccion=?, email=?, Password=?  WHERE idCliente=?";
            PreparedStatement pstmt = connection.prepareStatement(strSQL);
            // Establecer los valores de los parámetros
            pstmt.setString(1, usuario.getDni());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getDireccion());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getPassword());
            // Ejecutar la actualización
            int filasActualizadas = pstmt.executeUpdate();
            // Verificar si se actualizó correctamente al menos una fila
            if (filasActualizadas > 0) {
                exito = true;
            }
            pstmt.close();
            connection.close();
        }
    } catch (SQLException e) {
        System.out.println("Excepción SQL: " + e.toString());
    } catch (Exception e) {
        System.out.println("Excepción: " + e.toString());
    }
    return exito;
}
}

