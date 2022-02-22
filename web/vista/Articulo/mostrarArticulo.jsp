<%-- 
    Document   : mostrarArticulo
    Created on : 25-nov-2021, 0:57:12
    Author     : andre
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administrar Articulos</title>
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
        <h2 align="center">Lista  Articulos</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminArticulo?action=index" >Ir al menú</a> </td>
            </tr>
            <tr>
                <td><a href="adminArticulo?action=nuevo">Nuevo Articulo</a></td>
            </tr>

        </table>

        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> Codigo Articulo</td>
                <td> Nombre</td>
                <td> Precio</td>
                <td> Lista</td>
                <td colspan=2>Acciones</td>
            </tr>
            <c:forEach var="articulo" items="${lista}">
                <tr>
                    <td><c:out value="${articulo.id}"/></td>
                    <td><c:out value="${articulo.codigo}"/></td>
                    <td><c:out value="${articulo.nombre}"/></td>
                    <td><c:out value="${articulo.precioArticulo}"/></td>
                    <td><a href="adminArticulo?action=showedit&id=<c:out value="${articulo.id}" />">Editar</a></td>
                    <td><a href="adminArticulo?action=eliminar&id=<c:out value="${articulo.id}"/>">Eliminar</a> </td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>