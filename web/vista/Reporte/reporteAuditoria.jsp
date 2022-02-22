<%-- 
    Document   : reporteAuditoria
    Created on : 20 ene. 2022, 15:50:50
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reporte Auditoria</title>
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
        <h2 align="center">Reporte de auditoria</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminMovimiento?action=index" >Ir al menú</a> </td>
            </tr>
        </table>
        <table border="1" width="100%">
            <tr>
                <td> Ip</td>
                <td> Usuario</td>
                <td> Fecha</td>
                <td> Hora </td>
                <td> Lugar </td>
                <td> Operacion </td>
                <td> Actividad </td>
            </tr>
            <c:forEach var="auditoria" items="${listaAuditoria}">
                <tr>
                    <td><c:out value="${auditoria.IP}"/></td>
                    <td><c:out value="${auditoria.usuario}"/></td>
                    <td><c:out value="${auditoria.fecha}"/></td>
                    <td><c:out value="${auditoria.hora}"/></td>	
                    <td><c:out value="${auditoria.pantalla}"/></td>
                    <td><c:out value="${auditoria.operacion}"/></td>
                    <td><c:out value="${auditoria.actividad}"/></td>				
                </tr>
            </c:forEach>
        </table>
    </body>
</html>