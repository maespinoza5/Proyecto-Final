/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.Carrito_DAO;
import Model.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author ferna
 */
@WebServlet(name = "CarController", urlPatterns = {"/CarController"})
public class CarController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */

        // Verificar si se recibió una solicitud para eliminar un producto del carrito
        String eliminarProductoId = request.getParameter("eliminarProductoId");
        String actualizarProductoId = request.getParameter("actualizarProductoId");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (eliminarProductoId != null && !eliminarProductoId.isEmpty()) {
            // Obtener el ID del producto a eliminar del parámetro de la solicitud
            int productoId = Integer.parseInt(eliminarProductoId);

            // Lógica para eliminar el producto del carrito en la base de datos
            Carrito_DAO carritoDAO = new Carrito_DAO();
            boolean eliminado = carritoDAO.eliminarProductoDelCarrito(productoId,userId);

            if (eliminado) {
                // Enviar una respuesta al cliente indicando que el producto fue eliminado con éxito
                response.setStatus(HttpServletResponse.SC_OK);
                out.print("Producto eliminado del carrito");
            } else {
                // Enviar una respuesta al cliente indicando que hubo un problema al eliminar el producto
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("Error al eliminar el producto del carrito");
            }
            return;
        }
        if (actualizarProductoId != null && !actualizarProductoId.isEmpty()){
            // Obtener el ID del producto a actualizar del parámetro de la solicitud
            int productoId = Integer.parseInt(actualizarProductoId);
            boolean forWishList = Boolean.parseBoolean(request.getParameter("forwishlist"));

            // Lógica para actualizar el producto del carrito en la base de datos
            Carrito_DAO carritoDAO = new Carrito_DAO();
            boolean actualizado = carritoDAO.cambiarEstadoProducto(productoId,userId,forWishList);

            if (actualizado) {
                // Enviar una respuesta al cliente indicando que el producto fue actualizado con éxito
                response.setStatus(HttpServletResponse.SC_OK);
                out.print("Estado del producto actualizado");
            } else {
                // Enviar una respuesta al cliente indicando que hubo un problema al actualizar el producto
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("Error al actualizar");
            }
            return;
        }

        // Lógica para mostrar la cantidad de items en el carrito
        int numeroItemsEnCarrito = 0;
        
        String act = request.getParameter("act");
        if (userId != null || "actualizarcont".equals(act)) {
            Carrito_DAO carDAO = new Carrito_DAO();
            numeroItemsEnCarrito = carDAO.CalcularTotalProductos(userId);
        }

        out.print(numeroItemsEnCarrito);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}