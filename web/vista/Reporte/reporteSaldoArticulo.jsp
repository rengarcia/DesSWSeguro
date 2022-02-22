<%-- 
    Document   : reporteSaldoArticulo
    Created on : 30 nov. 2021, 10:37:15
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reporte Saldo Articulo</title>
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
        <h2 align="center">Reporte de saldos por articulos</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td>
                    <a href="adminComprobante?action=index" >Ir al menú</a> 
                </td>
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
                <td> ARTICULO</td>
                <td> CANTIDAD</td>
            </tr>
            <c:forEach var="reportesaldoarticulo" items="${lista}">
                <tr>
                    <td><c:out value="${reportesaldoarticulo.id}"/></td>
                    <td><c:out value="${reportesaldoarticulo.nombre}"/></td>
                    <td><c:out value="${reportesaldoarticulo.cantidad}"/></td>		
                </tr>
            </c:forEach>
        </table>

    </body>
</html>