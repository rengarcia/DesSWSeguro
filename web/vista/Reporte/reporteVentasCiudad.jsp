<%-- 
    Document   : reporteVentasCiudad
    Created on : 29 nov. 2021, 13:04:20
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reporte Ventas Ciudad</title>
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
        <h2 align="center">Reporte de ventas por ciudad</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminFactura?action=index" >Ir al menú</a> </td>
            </tr>
            <tr> 
                <td></td>
            </tr>
            <tr> 
                <td></td>
            </tr>
            <tr>
                <td>
                    <button onclick="window.print();">Imprimir</button>
                </td>
            </tr>
        </table>
        <br>
        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> CIUDAD</td>
                <td> VENTAS</td>
            </tr>
            <c:forEach var="reporteventaciudad" items="${lista}">
                <tr>
                    <td><c:out value="${reporteventaciudad.id}"/></td>
                    <td><c:out value="${reporteventaciudad.nombre}"/></td>
                    <td><c:out value="${reporteventaciudad.ventas}"/></td>		
                </tr>
            </c:forEach>
        </table>

    </body>
</html>