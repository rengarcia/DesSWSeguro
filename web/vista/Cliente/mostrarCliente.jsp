<%-- 
    Document   : mostrar
    Created on : 24 nov. 2021, 21:34:29
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administrar Clientes</title>
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
        <h2 align="center">Lista  Clientes</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminCliente?action=index" >Ir al menú</a> </td>
            </tr>
            <tr>
                <td><a href="adminCliente?action=nuevo">Nuevo Cliente</a></td>
            </tr>
        </table>

        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> NOMBRE</td>
                <td> RUC</td>
                <td> DIRECCION</td>
                <td colspan=2>ACCIONES</td>
            </tr>
            <c:forEach var="cliente" items="${lista}">
                <tr>
                    <td><c:out value="${cliente.id}"/></td>
                    <td><c:out value="${cliente.nombre}"/></td>
                    <td><c:out value="${cliente.rucCliente}"/></td>
                    <td><c:out value="${cliente.direccion}"/></td>
                    <td><a href="adminCliente?action=showedit&id=<c:out value="${cliente.id}" />">Editar</a></td>
                    <td><a href="adminCliente?action=eliminar&id=<c:out value="${cliente.id}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>

    </body>
</html>