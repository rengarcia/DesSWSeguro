<%-- 
    Document   : reporteCruzadoComprobante
    Created on : 30 nov. 2021, 11:11:41
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reporte Cruzado Movimientos Articulos</title>
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
        <h2 align="center">Reporte de movimientos de saldos por articulos</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminComprobacion?action=index" >Ir al menú</a> </td>
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
            <c:forEach var="matriz" items="${lista}"> 
                <tr>
                    <c:forEach var="fila" items="${matriz}">
                        <td><c:out value="${fila}"/></td>
                    </c:forEach>	
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
