
package DAO;

import Model.Producto;
import conexion.bd.conexionbd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ferna
 */
public class Carrito_DAO {

    conexionbd bdc = conexionbd.getInstancia();
    Connection con = bdc.obtenerConexion();

    public List<Producto> listarCarrito(int userId, boolean wishlist) {
        List<Producto> productos = new ArrayList<>();
        char estado = 'A';
        if(wishlist){
            estado = 'I';
        }
        String sql = "SELECT p.idProducto, p.Nombres, c.cantidad, p.Foto, p.Descripcion, p.Precio "
                + "FROM Carrito c "
                + "INNER JOIN producto p ON c.producto_id = p.idProducto "
                + "WHERE c.usuario_id = ? AND c.estado = '" + estado + "'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getInt("idProducto"));
                    p.setNombre(rs.getString("Nombres"));
                    p.setCantidad(rs.getInt("cantidad"));
                    p.setImagen(rs.getString("Foto"));
                    p.setDescripcion(rs.getString("Descripcion"));
                    p.setPrecio(rs.getDouble("Precio"));
                    productos.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return productos;
    }
    
    public int CalcularTotalProductos(int userId){
        int total = 0;
        String sql = "SELECT SUM(cantidad) AS suma_total " +
        "FROM defaultdb.Carrito WHERE usuario_id = ? AND estado = 'A'";
         try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    total = resultSet.getInt("suma_total");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
        return total;
    
    }

    public boolean validarInsertarProducto(Producto producto, int userId) {
        String consultaProducto = "SELECT COUNT(*) AS num_rows FROM Carrito WHERE usuario_id = ? AND producto_id = ?";
        String actualizarProducto = "UPDATE Carrito SET cantidad = cantidad + ? WHERE usuario_id = ? AND producto_id = ?";
        String insertarProducto = "INSERT INTO Carrito (usuario_id, producto_id, cantidad, precio) VALUES (?, ?, ?, ?)";
        boolean productoYaEnCarrito = false;

        try {
            // Verificar si el producto ya está en el carrito para el usuario especificado
            try (PreparedStatement consultaStmt = con.prepareStatement(consultaProducto)) {
                consultaStmt.setInt(1, userId);
                consultaStmt.setInt(2, producto.getId());
                try (ResultSet resultSet = consultaStmt.executeQuery()) {
                    if (resultSet.next()) {
                        int num_rows = resultSet.getInt("num_rows");
                        if (num_rows > 0) {
                            productoYaEnCarrito = true;
                        }
                    }
                }
            }

            // Si el producto ya está en el carrito, actualizar la cantidad
            if (productoYaEnCarrito) {
                try (PreparedStatement actualizarStmt = con.prepareStatement(actualizarProducto)) {
                    actualizarStmt.setInt(1, 1);
                    actualizarStmt.setInt(2, userId);
                    actualizarStmt.setInt(3, producto.getId());
                    actualizarStmt.executeUpdate();
                }
            } else {
                // Si el producto no está en el carrito, insertarlo
                try (PreparedStatement insertarStmt = con.prepareStatement(insertarProducto)) {
                    insertarStmt.setInt(1, userId);
                    insertarStmt.setInt(2, producto.getId());
                    insertarStmt.setInt(3, 1);
                    insertarStmt.setDouble(4, producto.getPrecio());
                    insertarStmt.executeUpdate();
                }
            }

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarProductoDelCarrito(int productoId, int userId) {
        String sql = "DELETE FROM Carrito WHERE usuario_id = ? AND producto_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productoId);
            int filasEliminadas = ps.executeUpdate();
            // Si al menos una fila fue eliminada, significa que el producto fue eliminado correctamente
            return filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
            return false;
        }
    }
    
    public boolean cambiarEstadoProducto(int productoId, int userId, boolean forwishlist) {
        String estado = (forwishlist ? "I" : "A");
        String sql = "UPDATE Carrito SET estado = ? WHERE usuario_id = ? AND producto_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, userId);
            ps.setInt(3, productoId);
            int filasEliminadas = ps.executeUpdate();
            // Si al menos una fila fue eliminada, significa que el producto fue eliminado correctamente
            return filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
            return false;
        }
    }

}