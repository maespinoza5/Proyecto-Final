<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.UsuarioInfo" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Datos Privados</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script>
        // Función para activar el modo de edición en un campo
        function activarEdicion(inputId) {
            var input = document.getElementById(inputId);
            input.removeAttribute('readonly');
            input.focus();
        }
    </script>
</head>
<body>
    <%
        UsuarioInfo usuario = (UsuarioInfo) request.getAttribute("usuario");
        String error = (String) request.getAttribute("error");

        if (error != null) {
            out.println("<p style='color:red;'>" + error + "</p>");
        } else if (usuario != null) {
    %>

    <form id="formulario" name="formbusqueda" action="ActualizarDatosUsuario" method="post">
        <div>
            <label for="dni">DNI:</label>
            <input type="text" id="dni" name="Dni" value="<%= usuario.getDni() %>" readonly>
            <a href="#" onclick="activarEdicion('dni')">Edit</a>
        </div>
        <div>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="Nombres" value="<%= usuario.getNombre() %>" readonly>
        </div>
        <div>
            <label for="direccion">Dirección:</label>
            <input type="text" id="direccion" name="Direccion" value="<%= usuario.getDireccion() %>" readonly>
        </div>
        <div>
            <label for="email">Correo Electrónico:</label>
            <input type="email" id="email" name="email" value="<%= usuario.getEmail() %>" readonly>
        </div>
        <div>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="Password" value="<%= usuario.getPassword() %>" readonly>
        </div>
        <button type="submit" name="cambios" value="cambios">Guardar cambios</button>
    </form>

    <%
        } else {
            out.println("<p>No se encontró la información del usuario.</p>");
        }
    %>

</body>
</html>
