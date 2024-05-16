
package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import DAO.TokenDao;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Base64;




@WebServlet("/RecuperarContraseñaServlet")
public class RecuperarContraseñaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("txtmail");
        String token = generarToken();
        LocalDateTime fechaExpiracion = LocalDateTime.now().plus(1, ChronoUnit.HOURS); // Token expira en 1 hora

        // Guardar el token en la base de datos
        TokenDao.guardarToken(email, token, Timestamp.valueOf(fechaExpiracion));

//         Mostrar el token en una ventana flotante
        out.println("<html><body>");
        out.println("<script>");
        out.println("alert('Tu token es: " + token + "');");
        out.println("window.location.href='ValidarToken.jsp';"); // Redirige a la página para verificar el token
        out.println("</script>");
        out.println("</body></html>");
          
    }

    private String generarToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}
