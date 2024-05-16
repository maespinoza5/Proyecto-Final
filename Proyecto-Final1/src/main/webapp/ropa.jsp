<%@ page import="DAO.Home_DAO" %>
<%@ page import="Model.Producto" %>
<%@ page import="java.util.List" %>

<%@include file="componentes/header.jsp" %>

<main>

    <section class="productos-destacados">
        <h1>Ropa: Ponte al d?a en la moda.</h1>
        <div class="sections">
            <%
            Home_DAO homeDAO = new Home_DAO();
            
            List<Producto> productos = homeDAO.listarPorCategoria(3);
            for (Producto producto : productos) {
            %>
            <div class="producto" onclick="verDetalle(<%= producto.getId() %>)">
                <img id="imgproducto" src="<%= producto.getImagen() %>" alt="<%= producto.getNombre() %>">
                <h3><%= producto.getNombre() %></h3>
                <br>
                <h4 class="price"><%= "$" + producto.getPrecio() %></h4>
                <!-- Bot?n para agregar al carrito -->
                <button onclick="agregarAlCarrito(<%= producto.getId() %>, event);"><i class="fa-solid fa-cart-shopping buy-icon"></i></button>

            </div>
            <% } %>
        </div>
    </section>
</main>


<%@include file="componentes/footer.jsp" %>