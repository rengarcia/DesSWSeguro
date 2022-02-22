<%-- 
    Document   : index
    Created on : 24 nov. 2021, 21:30:01
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <%/*
            HttpSession sesion_kmzh = request.getSession();
            Boolean hora_kmzh = (Boolean)sesion_kmzh.getAttribute("hora_kmzh");
            if(hora_kmzh==null){
            }else if(!hora_kmzh){
                out.print("<script>window.alert('Hora de acceso invalida')</script>");
            }
        */%>
        <h2 align="center">Sistema de facturación e inventario</h2>
        <h3 align="center">Ingreso de credenciales</h3>
        <form action="adminUsuario?action=ingresar" method="post">
            <table align="center">
                <tr>
                    <td><label>Usuario: </label></td>
                    <td><input type="text" name="user"  pattern="^[a-zA-Z0-9_.-]*$" value='<c:out value="${usuario.user}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Contraseña: </label></td>
                        <td><input type="password" name="pass" value='<c:out value="${usuario.pass}"></c:out>' > 

                    </td>

                </tr>
            </table> 
            <div align="center"> 
                <div class="g-recaptcha" data-sitekey="6LdIfB8eAAAAAOoC8wh0larTz4qr7BZbTWAlAe1o"></div>
            </div>
            <p align="center">
                <input width="20%" type="submit" name="ingresar" value="Ingresar"> 
            </p>
        </form>
        <form action="adminUsuario?action=crearUsuario" method="post" >
            <p align="center">
                <input width="20%" type="submit" name="crear" value="Crear usuario"> 
            </p>
        </form>
    </body>
</html>
