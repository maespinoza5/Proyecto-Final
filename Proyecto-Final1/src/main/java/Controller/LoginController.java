/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.Login_DAO;
import Model.UsuarioInfo;
import Model.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferna
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
        UsuarioModel usm = new UsuarioModel();
        Login_DAO logcos = new Login_DAO();
        String accion = request.getParameter("action");
        int result = 0;
        if (accion != null) {
            if (accion.equals("Ingresar")) {
                String correo = request.getParameter("txtmail");
                String password = request.getParameter("txtpassword");
                usm.setMail(correo);
                usm.setPassword(password);

                try {
                    result = logcos.validarLogin(usm);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (result != 0) {
                    try {
                        UsuarioInfo usInfo = logcos.ObtenerUsuario(correo);
                        String nombre = usInfo.getNombre();
                        int id = usInfo.getId();
                        HttpSession session = request.getSession(true); // Crea una nueva sesión si no existe
                        session.setAttribute("userId", id); // Guarda el ID del usuario en la sesión
                        session.setAttribute("userName", nombre); // Guarda el nombre en la sesión para ser usado luego
                        response.sendRedirect("index.jsp");
                    } catch (Exception ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    response.sendRedirect("login.jsp?auth=" + result);
                }
            } else {
                String nombre = request.getParameter("txtnom");
                String apellido = request.getParameter("txtnom2");
                String correo = request.getParameter("txtmail");
                String password = request.getParameter("txtpassword");
                usm.setNombre(nombre);
                usm.setApellido(apellido);
                usm.setMail(correo);
                usm.setPassword(password);
                try {
                    result = logcos.ValidarUsuarioReg(usm);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (result != 1) {
                    response.sendRedirect("registro.jsp?register=true");
                } else {
                    response.sendRedirect("registro.jsp?register=error");
                }
            }
        }
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
        processRequest(request, response);
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