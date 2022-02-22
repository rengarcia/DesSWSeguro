<%-- 
    Document   : editarCabecera
    Created on : 27 nov. 2021, 20:50:08
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actualizar Cabecera</title>
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
        <h3 align="center">Actualizar Cabecera</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table align="center">
            <tr>
                <td><a href="adminFactura?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <form action="adminFactura?action=editar" method="post" >
            <table align="center">
                <tr>
                    <td><label>Id</label></td>
                    <td><input type="text" name="id" value="<c:out value="${cabecerafactura.id}"></c:out>" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Fecha</label></td>
                        <td><input type="date" name="fecha" value='<c:out value="${cabecerafactura.fecha}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Ciudad</label></td>
                        <td>
                            <select name="ciudad" id="selectCiudad">
                            <c:forEach var="ciudad" items="${listaCiudad}">
                                <option value="<c:out value="${ciudad.id}"/>"><c:out value="${ciudad.nombre}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label>Nombre</label></td>
                    <td>
                        <select name="cliente" id="selectCliente">
                            <c:forEach var="cliente" items="${listaCliente}">
                                <option value="<c:out value="${cliente.id}"/>"><c:out value="${cliente.nombre}"/></option>
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