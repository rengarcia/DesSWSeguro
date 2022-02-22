<%-- 
    Document   : registerArticulo
    Created on : 25-nov-2021, 1:24:03
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registrar Articulo</title>
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
        <h3>Registrar Articulo</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
            <table>
                <tr>
                    <td><a href="adminArticulo?action=mostrar" >Regresar</a> </td>
                </tr>
            </table>
                <br>
        <form action="adminArticulo?action=register" method="post">
            <table border="1" align="center">
                <tr>
                    <td>Codigo</a></td>		
                    <td><input type="text" pattern="^(\d{13})?$"  name="codigo"/></td>	
                </tr>
                <tr>
                    <td>Nombre:</a></td>		
                    <td><input pattern="^[a-zA-Z\s]*$" type="text" name="nombre"/></td>	
                </tr>
                <tr>
                    <td>Precio</a></td>		
                    <td><input type="text" pattern="^\d+(,\d{1,2})?$" name="precioArticulo"/></td>	
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