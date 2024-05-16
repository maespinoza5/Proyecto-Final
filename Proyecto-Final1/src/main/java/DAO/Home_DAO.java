/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Producto;
import conexion.bd.conexionbd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ferna
 */
public class Home_DAO {
    conexionbd bdc = conexionbd.getInstancia();
    Connection con = bdc.obtenerConexion();
    
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setImagen(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                productos.add(p);
            }
        } catch (Exception e) {
           e.printStackTrace(); // Manejo de excepciones
        }
        return productos;
    }
    
     public Producto obtenerProductoPorId(int productoId) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE idProducto = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, productoId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    producto = new Producto();
                    producto.setId(resultSet.getInt("idProducto"));
                    producto.setNombre(resultSet.getString("Nombres"));
                    producto.setImagen(resultSet.getString("Foto"));
                    producto.setDescripcion(resultSet.getString("Descripcion"));
                    producto.setPrecio(resultSet.getDouble("Precio"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
        return producto;
    }
     
     public List<Producto> listarPorCategoria(int catId) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE idCategoria = " + catId;
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setImagen(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                productos.add(p);
            }
        } catch (Exception e) {
           e.printStackTrace(); // Manejo de excepciones
        }
        return productos;
    }
     
      public List<Producto> listarPorBusqueda(String busqueda) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE Nombres LIKE '%" + busqueda + "%'";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setImagen(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                productos.add(p);
            }
        } catch (Exception e) {
           e.printStackTrace(); // Manejo de excepciones
        }
        return productos;
    }
     
     public List<Producto> obtenerImgPorId(int productoId) {
         List<Producto> productos = new ArrayList<>();
        Producto producto = null;
        String sql = "SELECT urlImagen FROM defaultdb.Imagenes_Productos WHERE idProducto = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, productoId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    producto = new Producto();
                    producto.setImagen(resultSet.getString("urlImagen"));
                    productos.add(producto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
        return productos;
    }
}