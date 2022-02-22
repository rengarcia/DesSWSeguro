<%-- 
    Document   : crearUsuario
    Created on : 30 nov. 2021, 14:43:15
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Crear usuario</title>
    </head>
    <body>
        <h3 align="center">Creación de usuario</h3>
        <form action="adminUsuario?action=crear" method="post">
            <table align="center">
                <tr>
                    <td><label>Usuario: </label></td>
                    <td><input type="text" pattern="^[a-zA-Z0-9_.-]*$" name="user" value='<c:out value="${usuario.user}"></c:out>' ></td>
                </tr>
                 <tr>
                    <td><label>Correo: </label></td>
                    <td><input type="mail"  name="mail" value='<c:out value="${usuario.correo}"></c:out>' ></td>
                </tr>

                    <tr>
                        <td><label>Contraseña: </label></td>
                        <td><input type="password" name="pass1" value='<c:out value="${usuario.pass1}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Repita Contraseña: </label></td>
                        <td><input type="password" name="pass2" value='<c:out value="${usuario.pass2}"></c:out>' ></td>
                </tr>
            </table>
            <p align="center">
                <input width="20%" type="submit" name="registrar" value="Registrar"> 
            </p>
        </form>
        <form action="adminUsuario?action=index" method="post" >
            <p align="center">
                <input width="20%" type="submit" name="regresar" value="Regresar"> 
            </p>
        </form>
    </body>
</html>
