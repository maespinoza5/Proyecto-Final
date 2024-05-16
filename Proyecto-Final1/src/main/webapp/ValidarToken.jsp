

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Validar Token</title>
    <link href="css/estiloRecuperacion.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container">
        <h1>Validar Token</h1>
        <form action="ValidarTokenServlet" method="post">
            <label for="email">Correo:</label>
        <input type="Email" name="Email" required="true"> Ingrese su correo:<br>
        <label for="token">Ingrese el Token:</label>
        <input type="text" name="token" required="true">Token: <br>
            <input type="submit" value="Validar">
        </form>
    </div>    
</body>
</html>
