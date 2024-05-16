<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="componentes/header.jsp" %>  
<%@ page import="java.util.List" %>
<%@ page import="Model.Producto" %>
<%@ page import="DAO.Carrito_DAO" %>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Carrito de Compras</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="css/comprafinal.css">
    </head>
    <body>
        <main>
            <%
                // Obtener el valor de vistaGuardados
                String vistaGuardados = request.getParameter("vistaGuardados");
                String titleTxt = (vistaGuardados != null && vistaGuardados.equals("true")) ? "Productos Guardados" : "Carrito de Compras";
            %>
            <section class="seccion-carrito" style="border:solid #000">
                <h1 style="font-size: 36px"><%= titleTxt %></h1>
                <%
    // Establecer el texto del botón y la URL del botón en función de vistaGuardados
    String buttonText = (vistaGuardados != null && vistaGuardados.equals("true")) ? "Ver Carrito" : "Ver Guardados";
    String buttonURL = (vistaGuardados != null && vistaGuardados.equals("true")) ? "comprafinal.jsp" : "comprafinal.jsp?vistaGuardados=true";
                 if(session.getAttribute("userId") != null) { %>
                <button id="btnVerGuardados" style="
                        background-color: #8979c3;
                        color: #fff;
                        border: none;
                        padding: 10px 20px;
                        margin-right: 10px;
                        cursor: pointer;
                        border-radius: 4px;
                        transition: background-color 0.3s cubic-bezier(0.25, 0.1, 0, 1.11);
                        " onclick="window.location.href = '<%= buttonURL %>';"><%= buttonText %></button>
                <br><br>
                <%}%>
                <div class="contenedor-carrito">
                    <%
                        Carrito_DAO carDAO = new Carrito_DAO();
                        boolean forWishList = true;
                        String txtButtonState = " Guardar para más tarde";
                        if(session.getAttribute("userId") != null){
                        int userId = Integer.parseInt(session.getAttribute("userId").toString());
                        List<Producto> carrito = carDAO.listarCarrito(userId,false);
                        if(request.getParameter("vistaGuardados")!= null && request.getParameter("vistaGuardados").toString().equals("true")){
                        carrito = carDAO.listarCarrito(userId,true);
                        forWishList = false;
                        txtButtonState = " Devolver al carrito";
                        
                        }
                        if (carrito != null && !carrito.isEmpty()) {
                        for (Producto producto : carrito) {
                    %>
                    <div class="item-carrito" id="item-id-<%= producto.getId() %>">
                        <div class="imagen-item">
                            <img src="<%= producto.getImagen() %>" alt="<%= producto.getNombre() %>">
                        </div>
                        <div class="detalles-item">
                            <h2><%= producto.getNombre() %></h2>
                            <p><%= producto.getDescripcion() %></p>
                            <p class="precio" data-precio="<%= producto.getPrecio() %>"><%= "$" + producto.getPrecio() %></p> <!-- No se necesitan cambios aquí -->
                            <div class="control-cantidad">
                                <button class="btn-menos"><i class="fas fa-minus"></i></button> <!-- Cambiado de onclick a class -->
                                <input type="number" class="input-cantidad" value="<%= producto.getCantidad() %>" min="1">
                                <button class="btn-mas"><i class="fas fa-plus"></i></button> <!-- Cambiado de onclick a class -->
                            </div>
                            <div class="acciones">
                                <button class="btn-eliminar" onclick="eliminarDelCarrito(<%= producto.getId() %>);"><i class="fas fa-trash"></i> Eliminar</button>
                                <button class="btn-guardar" onclick="actualizarEstadoProducto(<%= producto.getId() %>, <%= forWishList %>);"><i class="fas fa-heart"></i><%= txtButtonState %></button>
                            </div>
                        </div>
                    </div>
                    <% } } else { %>
                    <p>El carrito está vacío.</p>
                    <% } } else { %>
                    <p>El carrito está vacío.</p>
                    <p style="color: red">Para agregar artículos, inicie sesión.</p>
                    <% } %>
                </div>
                <div class="resumen-carrito">
                    <p>Subtotal : <span class="precio-total">US$0.00</span></p>
                    <button class="btn-pago">Proceder al pago</button>
                    <p class="oferta-prime">Compra rápido. Con entrega más rápida.<a href="#">Síguenos</a></p>
                </div>
            </section>
        </main>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const btnMenos = document.querySelectorAll('.btn-menos');
                const btnMas = document.querySelectorAll('.btn-mas');
                const btnDelete = document.querySelectorAll('.btn-eliminar');
                const btnSave = document.querySelectorAll('.btn-guardar');
                var cantidadInput = document.querySelectorAll('.input-cantidad');
                const elementoPrecioTotal = document.querySelector('.precio-total');

                // Función para actualizar el precio total
                function actualizarPrecioTotal() {
                    let precioTotal = 0;
                    cantidadInput = document.querySelectorAll('.input-cantidad');
                    cantidadInput.forEach((input, index) => {
                        const cantidad = parseInt(input.value);
                        const precioUnitario = parseFloat(input.closest('.item-carrito').querySelector('.precio').getAttribute('data-precio'));
                        precioTotal += cantidad * precioUnitario;
                    });
                    elementoPrecioTotal.textContent = 'US$ ' + precioTotal.toFixed(2);
                }

                // Agregar eventos para los botones de cantidad
                btnMenos.forEach((btn, index) => {
                    btn.addEventListener('click', () => {
                        if (cantidadInput[index].value > 1) {
                            cantidadInput[index].value = parseInt(cantidadInput[index].value) - 1;
                            actualizarPrecioTotal();
                        }
                    });
                });

                btnMas.forEach((btn, index) => {
                    btn.addEventListener('click', () => {
                        cantidadInput[index].value = parseInt(cantidadInput[index].value) + 1;
                        actualizarPrecioTotal();
                    });
                });

                // Actualizar el precio total al cargar la página
                actualizarPrecioTotal();
            });
        </script>
    </body>
    <%@include file="componentes/footer.jsp" %>
</html>