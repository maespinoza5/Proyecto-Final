
package Controller;

import DAO.Carrito_DAO;
import DAO.Home_DAO;
import Model.Producto;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

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
        if (request.getParameter("btnBuscar") != null) {
            if (request.getParameter("busqueda") != null || !request.getParameter("busqueda").isEmpty()) {
                String busqueda = request.getParameter("busqueda");

                // Llamar al método buscarPorNombre del DAO para obtener los resultados de la búsqueda
                Home_DAO homeDAO = new Home_DAO();
                List<Producto> resultadosBusqueda = homeDAO.listarPorBusqueda(busqueda);

                // Puedes pasar los resultados de la búsqueda a la vista (JSP) para mostrarlos
                request.setAttribute("resultadosBusqueda", resultadosBusqueda);

                // Redirigir a la página de resultados de búsqueda
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            HttpSession session = request.getSession();
            if(session.getAttribute("userId")!= null){
                Home_DAO hDao = new Home_DAO();
            Carrito_DAO carDAO = new Carrito_DAO();
            int userId = Integer.parseInt(session.getAttribute("userId").toString());
            int productoId = Integer.parseInt(request.getParameter("productoId"));

            // Aquí se ejecuta la consulta SQL para obtener los detalles del producto con el ID
            Producto producto = hDao.obtenerProductoPorId(productoId);
            boolean insert = carDAO.validarInsertarProducto(producto, userId);
            if(!insert){
                System.out.println("mal ahi, pa");
            } else {
            
            System.out.println("chimichangas");
            // Se debe agregar alguna vista para indicar que el producto ha sido agregado.
            }
            }
        }

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
        response.setContentType("text/html;charset=UTF-8");

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