<%-- 
    Document   : mostrarCabeceraComprobante
    Created on : 29 nov. 2021, 22:42:19
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

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
        <h2 align="center">Lista Cabecera Comprobantes</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminComprobante?action=index" >Ir al menú</a> </td>
            </tr>
            <tr>
                <td><a href="adminComprobante?action=nuevo">Nuevo Comprobante</a> </td>
            </tr>
        </table>

        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> FECHA</td>
                <td> MOVIMIENTO</td>
                <td colspan=3>ACCIONES</td>
            </tr>
            <c:forEach var="cabeceracomprobante" items="${lista}">
                <tr>
                    <td><c:out value="${cabeceracomprobante.id}"/></td>
                    <td><c:out value="${cabeceracomprobante.fecha}"/></td>
                    <td value="<c:out value="${cabeceracomprobante.idMovimiento}"/>"><c:out value="${cabeceracomprobante.nombreMovimiento}"/></td>
                    <td><a href="adminComprobante?action=mostrarComprobante&id=<c:out value="${cabeceracomprobante.id}" />">Mostrar</a></td>
                    <td><a href="adminComprobante?action=showedit&id=<c:out value="${cabeceracomprobante.id}" />">Editar</a></td>
                    <td><a href="adminComprobante?action=eliminarCabecera&id=<c:out value="${cabeceracomprobante.id}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>

    </body>
</html>

