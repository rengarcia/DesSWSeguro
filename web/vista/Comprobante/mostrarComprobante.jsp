<%-- 
    Document   : mostrarComprobante
    Created on : 29 nov. 2021, 22:42:46
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administrar Cabecera Comprobantes</title>
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
        <h2 align="center">Comprobante Completo</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminComprobante?action=index" >Ir al menú</a> </td>
            </tr>
            <tr>
                <td><a href="adminComprobante?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> FECHA</td>
                <td> Movimiento</td>
            </tr>
            <tr>
                <td><c:out value="${cabeceracomprobante.id}"/></td>
                <td><c:out value="${cabeceracomprobante.fecha}"/></td>
                <td><c:out value="${cabeceracomprobante.nombreMovimiento}"/></td>	
            </tr>
        </table>
        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> ARTICULO</td>
                <td> CANTIDAD</td>
                <td> PRECIO</td>
                <td colspan=2>ACCIONES</td>
            </tr>
            <c:forEach var="detallecomprobante" items="${lista}">
                <tr>
                    <td><c:out value="${detallecomprobante.id}"/></td>
                    <td value="<c:out value="${detallecomprobante.idArticulo}"/>"><c:out value="${detallecomprobante.nArticulo}"/></td>
                    <td><c:out value="${detallecomprobante.cantidad}"/></td>
                    <td><c:out value="${detallecomprobante.precio}"/></td>
                    <td><a href="adminComprobante?action=showeditdetalle&id=<c:out value="${detallecomprobante.id}" />">Editar</a></td>
                    <td><a href="adminComprobante?action=eliminar&id=<c:out value="${detallecomprobante.id}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
