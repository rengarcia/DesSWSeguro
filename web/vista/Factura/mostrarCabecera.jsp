<%-- 
    Document   : mostrarCabecera
    Created on : 27 nov. 2021, 20:04:28
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administrar Cabecera Facturas</title>
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
        <h2 align="center">Lista Cabecera Facturas</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminFactura?action=index" >Ir al menú</a> </td>
            </tr>
            <tr>
                <td><a href="adminFactura?action=nuevo">Nueva Factura</a> </td>
            </tr>
        </table>

        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> FECHA</td>
                <td> CIUDAD</td>
                <td> CLIENTE</td>
                <td colspan=3>ACCIONES</td>
            </tr>
            <c:forEach var="cabecerafactura" items="${lista}">
                <tr>
                    <td><c:out value="${cabecerafactura.id}"/></td>
                    <td><c:out value="${cabecerafactura.fecha}"/></td>
                    <td value="<c:out value="${cabecerafactura.idCiudad}"/>"><c:out value="${cabecerafactura.nombreCiudad}"/></td>
                    <td value="<c:out value="${cabecerafactura.idCliente}"/>"><c:out value="${cabecerafactura.nombreCliente}"/></td>
                    <td><a href="adminFactura?action=mostrarFactura&id=<c:out value="${cabecerafactura.id}" />">Mostrar</a></td>
                    <td><a href="adminFactura?action=showedit&id=<c:out value="${cabecerafactura.id}" />">Editar</a></td>
                    <td><a href="adminFactura?action=eliminarCabecera&id=<c:out value="${cabecerafactura.id}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
