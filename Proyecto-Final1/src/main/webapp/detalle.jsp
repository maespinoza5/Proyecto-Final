<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalles de producto</title>
        <link rel="stylesheet" href="css/detalles-producto.css">

    </head>
    <%@ page import="Model.Producto" %>
    <%@ page import="DAO.Home_DAO" %>
    <%@ page import="Model.Producto" %>
    <%@include file="componentes/header.jsp" %>
    <%@ page import="java.util.List" %>
    
    <div class="principal">
        <div class="icono-izquierdo">
            
            <%
          // Obtener el ID del producto de la solicitud
          int productId = Integer.parseInt(request.getParameter("item"));

          // Crear una instancia de Home_DAO
          Home_DAO homeDAO = new Home_DAO();
          List<Producto> productosImg = homeDAO.obtenerImgPorId(productId);
          for (Producto producto : productosImg) {
          %>
            
          <div><img onclick="document.getElementById('contenedor-img').style.backgroundImage = 'url(<%= producto.getImagen() %>)'" src="<%= producto.getImagen() %>" id="c?rculo-izquierdo"></div>
            

    <%
        }
    // Obtener el producto por su ID
          Producto producto = homeDAO.obtenerProductoPorId(productId);
            %>

        </div>
        <div class="fila">
            <div class="columna">
                <div id="contenedor-img" style="background-image: url(<%= producto.getImagen() %>);"></div>
            </div>
            <div class="columna">
                <h><%= producto.getNombre() %></h>
                <p><%= producto.getDescripcion() %></p>

                <h style="font-size:0.8em; display:block;padding-top: 8px;">Potencia y Portabilidad</h><br>
                <br><br><br>
                <div class="caja-icono">
                    <div><img src="img/1562687-code-computer-creative-html-process-technology-web-development_107058.svg" id="icono-flex"></div>
                    <div><img src="img/technology_no_wifi_wireless_internet_icon_192894.svg" id="icono-flex"></div>
                    <div><img src="img/technology_safety_internet_privacy_protection_secure_security_icon_231794.svg" id="icono-flex"></div>
                </div>
                        <button class="bot?n-precio" onclick="agregarAlCarrito(<%=productId%>)">A?adir al carrito - <%= "$" + producto.getPrecio() %></button>
            </div>

        </div>
    </div>

    <%@include file="componentes/footer.jsp" %>
    <script>
        function agregarAlCarrito(idProducto) {
            
            // Crear una nueva instancia de XMLHttpRequest
            var xhr = new XMLHttpRequest();

            // Configurar la solicitud AJAX
            xhr.open("GET", "HomeController?productoId=" + idProducto, true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // Definir la funci?n de respuesta
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // La solicitud fue exitosa
                    console.log('s');
                    window.location.href = "comprafinal.jsp";
                    // Aqui se puede redirigir al usuario a una p?gina de confirmaci?n o actualizar la UI aqu? si es necesario
                } else {
                    // Hubo un error en la solicitud
                    console.error('E');
                }
            };

            // Enviar la solicitud con el ID del producto como par?metro
            xhr.send();
        }
    </script>