<%-- 
    Document   : registerCiudad
    Created on : 24 nov. 2021, 22:34:36
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registrar Ciudad</title>
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
        <h3 align="center">Registrar Ciudad</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminCiudad?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <form action="adminCiudad?action=register" method="post">
            <table border="1" align="center">
                <tr>
                    <td>Codigo</a></td>		
                    <td><input type="text" name="codigo"/></td>	
                </tr>
                <tr>
                    <td>Nombre</a></td>		
                    <td><input pattern="^[a-zA-Z\s]*$" type="text" name="nombre"/></td>	
                </tr>

            </table>
            <br>
            <table border="0" align="center">
                <tr>
                    <td><input type="submit" value="Agregar" name="agregar"></td>	
                </tr>

        </form>
    </body>
</html>
