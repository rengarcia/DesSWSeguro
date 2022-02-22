<%-- 
    Document   : editarCiudad
    Created on : 24 nov. 2021, 22:33:53
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actualizar Ciudad</title>
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
        <h3 align="center">Actualizar Ciudad</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <form action="adminCiudad?action=editar" method="post" >
        <table align="center">
                <tr>
                    <td><a href="adminCiudad?action=mostrar" >Regresar</a> </td>
                </tr>
            </table>
            <br>
            <table align="center">
                <tr>
                    <td><label>Id</label></td>
                    <td><input type="text" name="id" value="<c:out value="${ciudad.id}"></c:out>" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Codigo</label></td>
                        <td><input type="text"  name="codigo" value='<c:out value="${ciudad.codigo}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Nombre</label></td>
                        <td><input type="text" pattern="^[a-zA-Z\s]*$" name="nombre" value='<c:out value="${ciudad.nombre}"></c:out>' ></td>
                </tr>
            </table>
            <p align="center">
                <input type="submit" name="registrar" value="Guardar"> 
            </p>
        </form>

    </body>
</html>
