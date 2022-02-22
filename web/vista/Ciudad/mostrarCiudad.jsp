<%-- 
    Document   : mostrarCiudad
    Created on : 24 nov. 2021, 22:34:27
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administrar Ciudades</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            String usuario = (String) sesion.getAttribute("usuario");
            String rol;
            if (usuario == null) {
                out.print("<script>location.replace('index.jsp');</script>");
            }
        %>
        <h2 align="center">Lista  Ciudad</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminCiudad?action=index" >Ir al menú</a> </td>
            </tr>
            <tr>
                <td><a href="adminCiudad?action=nuevo">Nuevo Ciudad</a></td>
            </tr>
        </table>

        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> CODIGO</td>
                <td> NOMBRE</td>
                <td colspan=2>ACCIONES</td>
            </tr>
            <c:forEach var="ciudad" items="${lista}">
                <tr>
                    <td><c:out value="${ciudad.id}"/></td>
                    <td><c:out value="${ciudad.codigo}"/></td>
                    <td><c:out value="${ciudad.nombre}"/></td>
                    <td><a href="adminCiudad?action=showedit&id=<c:out value="${ciudad.id}" />">Editar</a></td>
                    <td><a href="adminCiudad?action=eliminar&id=<c:out value="${ciudad.id}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>
        <!--       
              <br>
              <br>
              <h2>Tabla Auditoria</h2>
              
              <table border="1" width="100%">
                      <tr>
                       <td> USUARIO</td>
                       <td> PANTALLA</td>
                       <td> HORA</td>
                      </tr>
        <c:forEach var="auditoria" items="${listaAuditoria}">
                <tr>
                        <td><c:out value="${auditoria.usuario}"/></td>
                        <td><c:out value="${auditoria.pantalla}"/></td>
                        <td><c:out value="${auditoria.hora}"/></td>			
                </tr>
        </c:forEach>
    </table>-->
    </body>
</html>