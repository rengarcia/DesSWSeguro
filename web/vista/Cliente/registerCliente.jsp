<%-- 
    Document   : register
    Created on : 24 nov. 2021, 21:32:46
    Author     : zurit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registrar Cliente</title>
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
        <h2 align="center">Registrar Cliente</h2>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminCliente?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>

        <p>
        </p>
        <form action="adminCliente?action=register" method="post">
            <table border="1" align="center">
                <tr>
                    <td>Nombre:</a></td>		
                    <td><input type="text" pattern="^[a-zA-Z\s]*$" name="nombre"/></td>	
                </tr>
                <tr>
                    <td>Ruc:</a></td>		
                    <td><input type="text" name="ruc" pattern="[0-9]+"/></td>	
                </tr>
                <tr>
                    <td>Direccion:</a></td>		
                    <td><input type="text" name="direccion"/></td>	
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