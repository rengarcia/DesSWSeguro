<%-- 
    Document   : mostrarFactura
    Created on : 27 nov. 2021, 20:18:15
    Author     : zurit
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administrar Factura</title>
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
        <h2 align="center">Factura Completa</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminFactura?action=index" >Ir al menú</a> </td>
            </tr>
            <tr>
                <td><a href="adminFactura?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> FECHA</td>
                <td> CIUDAD</td>
                <td> CLIENTE</td>
            </tr>
            <tr>
                <td><c:out value="${cabecerafactura.id}"/></td>
                <td><c:out value="${cabecerafactura.fecha}"/></td>
                <td><c:out value="${cabecerafactura.nombreCiudad}"/></td>
                <td><c:out value="${cabecerafactura.nombreCliente}"/></td>		
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
            <c:forEach var="detallefactura" items="${lista}">
                <tr>
                    <td><c:out value="${detallefactura.id}"/></td>
                    <td value="<c:out value="${detallefactura.idArticulo}"/>"><c:out value="${detallefactura.nArticulo}"/></td>
                    <td><c:out value="${detallefactura.cantidad}"/></td>
                    <td><c:out value="${detallefactura.precio}"/></td>
                    <td><a href="adminFactura?action=showeditdetalle&id=<c:out value="${detallefactura.id}" />">Editar</a></td>
                    <td><a href="adminFactura?action=eliminar&id=<c:out value="${detallefactura.id}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>

    </body>
</html>