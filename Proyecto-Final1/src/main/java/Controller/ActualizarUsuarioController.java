/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ClienteDao;
import Model.UsuarioInfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ActualizarUsuario", urlPatterns = {"/ActualizarUsuario"})
public class ActualizarUsuarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Obtener los parámetros del formulario
            String dni = request.getParameter("txt_dni");
            String nombre = request.getParameter("txt_nom");
            String direccion = request.getParameter("txt_direc");
            String email = request.getParameter("txt_mail");
            String password = request.getParameter("usr_pass");
            
            // Crear un objeto UsuarioInfo con los datos del formulario
            UsuarioInfo usr = new UsuarioInfo();
            usr.setDni(dni);
            usr.setNombre(nombre);
            usr.setDireccion(direccion);
            usr.setEmail(email);
            usr.setPassword(password);
            
            // Llamar al método para actualizar el usuario en la base de datos
            ClienteDao cliente = new ClienteDao();
            boolean actualizacionExitosa = cliente.ActualizarCliente(usr);
            
            // Generar la respuesta HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualización de Usuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Actualización de Usuario</h1>");
            if (actualizacionExitosa) {
                out.println("<p>Los datos del usuario se han actualizado correctamente.</p>");
            } else {
                out.println("<p>Error al actualizar los datos del usuario.</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para actualizar los datos de un usuario";
    }

}
