<%-- 
    Document   : editar
    Created on : 24 nov. 2021, 22:17:03
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actualizar Cliente</title>
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
        <h3 align="center">Actualizar Cliente</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table align="center">
            <tr>
                <td><a href="adminCliente?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <form action="adminCliente?action=editar" method="post" >
            <table align="center">
                <tr>
                    <td><label>Id</label></td>
                    <td><input type="text" name="id" value="<c:out value="${cliente.id}"></c:out>" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Nombre</label></td>
                        <td><input pattern="^[a-zA-Z\s]*$" type="text" name="nombre" value='<c:out value="${cliente.nombre}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>RUC</label></td>
                        <td><input type="text" name="ruc" value='<c:out value="${cliente.rucCliente}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Direccion</label></td>
                        <td><input type="text" name="direccion" value='<c:out value="${cliente.direccion}"></c:out>' ></td>
                </tr>
            </table>
            <p align="center">
                <input type="submit" name="registrar" value="Guardar">                     
            </p>
        </form>

    </body>
</html>