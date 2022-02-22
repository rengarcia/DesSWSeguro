<%-- 
    Document   : mostrarMovimiento
    Created on : 25-nov-2021, 10:39:41
    Author     : andre
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administrar Movimientos</title>
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
        <h2 align="center">Lista  Movimientos</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminMovimiento?action=index" >Ir al menú</a> </td>
            </tr>
            <tr>
                <td><a href="adminMovimiento?action=nuevo">Nuevo Movimiento</a> </td>
            </tr>
        </table>
        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> Codigo Movimientos</td>
                <td> Nombre Movimiento</td>
                <td> Signo Movimiento </td>
                <td colspan=2>Acciones</td>
            </tr>
            <c:forEach var="movimiento" items="${lista}">
                <tr>
                    <td><c:out value="${movimiento.id}"/></td>
                    <td><c:out value="${movimiento.codigo}"/></td>
                    <td><c:out value="${movimiento.nombre}"/></td>
                    <td><c:out value="${movimiento.signoMovimiento}"/></td>
                    <td><a href="adminMovimiento?action=showedit&id=<c:out value="${movimiento.id}" />">Editar</a></td>
                           <td><a href="adminMovimiento?action=eliminar&id=<c:out value="${movimiento.id}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>
    </body>
</html>