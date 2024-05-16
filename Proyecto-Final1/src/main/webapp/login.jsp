

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    
    <title>Login</title>
</head>
<%
    String errormessage = "";
    if(request.getParameter("auth")!= null){
    int msg = Integer.parseInt(request.getParameter("auth"));
    if(msg == 0){
    errormessage = "Comprueba tu email y contraseña e inténtalo de nuevo.";
    }
    }
    %>

<body>
   
    <div class="container" id="container">
        <div class="form-container sign-in">
            <form id="loginForm"  action="LoginController">
                <h1>Iniciar sesión</h1>
                <div class="social-icons">
                    <a href="#" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
                    <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
                </div>
                <span>o utiliza tu contraseña de correo electrónico</span>
                <input type="email" id="email" placeholder="Email" name="txtmail" required="true">
                <input type="password" id="password" placeholder="Contraseña" name="txtpassword" required="true">
                
                <a href="#">→ ¿Olvidó su contraseña? ←</a>
                <button type="submit" name="action" value="Ingresar">Iniciar sesión</button>
                <p id="successMessage" style=" color:red;"><%=errormessage%></p>
            </form>
        </div>
        <div class="diseno-container">
            <div id="particles-js"></div>
            <div class="diseno">
                <div class="diseno-panel diseno-derecho">
                    <h1>¡Bienvenido! Tu viaje comienza aquí. 🚀</h1>
                    <p>Regístrese con sus datos personales para utilizar todas las funciones del sitio</p>
                    <button class="hidden" id="register">Registrarse</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            // Redireccionar al usuario a la página de registro cuando hace clic en el botón "Registrarse"
            var registerButton = document.getElementById("register");
            registerButton.addEventListener("click", function() {
                window.location.href = "registro.jsp";
            });
        });
    </script>
   
</body>

</html>