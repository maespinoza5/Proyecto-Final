
package Controller;

import DAO.TokenDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/ValidarTokenServlet")
public class ValidarTokenServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        

        String Email = request.getParameter("Email");
        String token = request.getParameter("token");
        
        // metodos de deuracion
        System.out.println("Email obtenido del formulario de HTML: " + Email);
        System.out.println("Token obtenido del formulario HTML: " + token);

        // Validar el token y obtener la contraseña asociada al email
        String Password = TokenDao.obtenerContraseña(Email, token);
        
        System.out.println("Contrasena validada: " + Password);

        if (Password != null) {
            // Mostrar la contraseña en una ventana flotante
            out.println("<script>");
            out.println("alert('Tu contraseña es: " + Password + "');");
            out.println("window.location.href='login.jsp';");
            out.println("</script>");
        } else {
            out.println("<script>alert('Token inválido.');</script>");
        }
        

    }
}
