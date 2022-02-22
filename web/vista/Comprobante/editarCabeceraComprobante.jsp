<%-- 
    Document   : editarCabeceraComprobante
    Created on : 29 nov. 2021, 22:43:57
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actualizar Cabecera Comprobante</title>
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
        <h3 align="center">Actualizar Cabecera Comprobante</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table align="center">
            <tr>
                <td><a href="adminComprobante?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <form action="adminComprobante?action=editar" method="post" >
            <table align="center">
                <tr>
                    <td><label>Id</label></td>
                    <td><input type="text" name="id" value="<c:out value="${cabeceracomprobante.id}"></c:out>" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Fecha</label></td>
                        <td><input type="date" name="fecha" value='<c:out value="${cabeceracomprobante.fecha}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Movimiento</label></td>
                        <td>
                            <select name="movimiento" id="selectMovimiento">
                            <c:forEach var="movimiento" items="${listaMovimiento}">
                                <option value="<c:out value="${movimiento.id}"/>"><c:out value="${movimiento.nombre}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <p align="center">
                <input type="submit" name="registrar" value="Guardar"> 
            </p>
        </form>

    </body>
</html>