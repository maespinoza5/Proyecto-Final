

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recuperar Contraseña</title>
    <link href="css/estiloRecuperacion.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div>
        <div class="logo">
            <img src="img/tess.png" alt="Logo de la pagina"/>
        </div>
        <div class="container">
            <!-- Logo de la página -->
            <h1>Recuperar Contraseña</h1>
            <p>Escriba la dirección de correo electrónico asociado a tu cuenta de Ecommerce.</p>
            <!-- Formulario -->
            <form action="RecuperarContraseñaServlet" method="post">
                <label for="email">Dirección de correo electrónico:</label>
                <input type="email" id="email" name="txtmail" placeholder="correo@example.com" required="true">
                <button type="submit" value="Recuperar Contraseña">Continuar</button>
            </form>
        </div>
    </div>
</body>
</html>
