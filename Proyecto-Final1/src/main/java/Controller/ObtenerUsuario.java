
package Controller;

import DAO.ClienteDao;
import Model.UsuarioInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet(name = "ObtenerUsuario", urlPatterns = {"/ObtenerUsuario"})
public class ObtenerUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String email = request.getParameter("email");
        ClienteDao clienteDao = new ClienteDao();
        UsuarioInfo usuario = clienteDao.obtenerCliente(email);

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
        } else {
            request.setAttribute("error", "No se encontró el cliente con email: " + email);
        }

        request.getRequestDispatcher("InformacionUsuario.jsp").forward(request, response);
    }
}

