
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    
    <title>Registro</title>
</head>
<%
    String msg = "";
    String color = "";
    if(request.getParameter("register")!= null){
    String reg = request.getParameter("register");
    switch (reg) {
    case "true":
        msg = "¡Su cuenta ha sido creada con éxito!";
        color = "green";
        break;
    case "error":
        msg = "Correo electrónico ya registrado";
        color ="red";
        break;
    default:
        msg = "ERROR EN EL SISTEMA";
        break;
}
    }
%>

<body>
   
    <div class="container" id="container">
        <div class="form-container sign-in">
            <form id="registrationForm" action="LoginController">
                <h1>Registrarse </h1>
                <div class="social-icons">
                    <a href="#" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
                    <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
                </div>
                <span>o utiliza tu contraseña de correo electrónico</span>
                <input type="text" id="nombres" placeholder="Nombres" name="txtnom" required="true">
                <input type="text" id="apellidos" placeholder="Apellidos" name="txtnom2" required="true">
                <input type="email" id="email" placeholder="Email" name="txtmail" required="true">
                <input type="password" id="password" placeholder="Contraseña" name="txtpassword" required="true">
                <button type="submit" name='action' value="Registrar">Registrarse</button>
                <p id="successMessage" style="color:<%=color%>;"><%=msg%></p>
            </form>
        </div>
        <div class="diseno-container">
            <div id="particles-js"></div>
            <div class="diseno">
                <div class="diseno-panel diseno-derecho">
                    <h1>¡Bienvenido! Tu viaje comienza aquí. 🚀</h1>
                    <p>¿Ya tienes una cuenta?</p>
                    <button class="hidden" id="register">Iniciar Sesión</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            // Redireccionar al usuario a la página de inicio de sesión cuando hace clic en el botón "Iniciar Sesión"
            var registerButton = document.getElementById("register");
            registerButton.addEventListener("click", function() {
                window.location.href = "login.jsp";
            });
        });
    </script>
</body>

</html>