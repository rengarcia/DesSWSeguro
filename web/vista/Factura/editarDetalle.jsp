<%-- 
    Document   : editarDetalle
    Created on : 27 nov. 2021, 20:50:36
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actualizar Cliente</title>
        <script>
            function calcularPrecio(sel){
                var cantidad = document.getElementById("cantidad").value;
                console.log(cantidad);
                var valor = sel.options[sel.selectedIndex].getAttribute("name");
                console.log(valor);
                document.getElementById('precio').readOnly = false;
                document.getElementById("precio").value=valor*cantidad;
                document.getElementById('precio').readOnly = true;
            }
            function cambiarPrecio(sel){
                var index = document.getElementById("selectArticulo").selectedIndex;
                var valor = document.getElementById("selectArticulo").children[index].getAttribute("name");
                var cantidad = sel.value;
                console.log(cantidad);
                console.log(valor);
                var total = valor*cantidad;
                document.getElementById('precio').readOnly = false;
                document.getElementById("precio").value=total;
                document.getElementById('precio').readOnly = true;
            }
        </script>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            String usuario = (String)sesion.getAttribute("usuario");
            String rol;
            if(usuario==null){
                out.print("<script>location.replace('index.jsp');</script>");
            }
        %>
        <h3 align="center">Actualizar Cliente</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <form action="adminFactura?action=editarDetalle" method="post" >
            <table align="center">
                <tr>
                    <td><label>Id del Detalle</label></td>
                    <td><input type="text" name="id" value="<c:out value="${detalle.id}"></c:out>" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Id de la Factura</label></td>
                        <td><input type="text" name="idFactura" value="<c:out value="${detalle.idFactura}" ></c:out>" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Articulo</label></td>
                        <td>
                            <select name="articuloSeleccionado" id="selectArticulo" onchange="calcularPrecio(this);">
                            <c:forEach var="articulo" items="${listaArticulo}">
                                <option name="<c:out value="${articulo.precioArticulo}"/>" value="<c:out value="${articulo.id}"/>"><c:out value="${articulo.nombre}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label>Cantidad</label></td>
                    <td><input type="number" name="cantidad" id="cantidad" value='<c:out value="${detalle.cantidad}"></c:out>' min="0" onchange="cambiarPrecio(this);"></td>
                    </tr>
                    <tr>
                        <td><label>Precio</label></td>
                        <td><input type="text" id="precio" pattern="^\d+(,\d{1,2})?$" name="precio" value="<c:out value="${detalle.precio}" ></c:out>" readonly></td>
                </tr>
            </table>
                <p align="center">
                    
                </p>
            <input type="submit" name="registrar" value="Guardar"> 
        </form>

    </body>
</html>
