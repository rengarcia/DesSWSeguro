<%-- 
    Document   : registerMovimiento
    Created on : 25-nov-2021, 11:15:21
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registrar Movimiento</title>
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
        <h3 align="center">Registrar Movimiento</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminMovimiento?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <form action="adminMovimiento?action=register" method="post">
            <table border="1" align="center">
                <tr>
                    <td>Codigo</a></td>		
                    <td><input type="text" name="codigo"/></td>	
                </tr>
                <tr>
                    <td>Nombre:</a></td>		
                    <td><input type="text" name="nombre"/></td>	
                </tr>
                <tr>
                    <td>Signo</a></td>		
                    <td><input type="text" name="signoMovimiento"/></td>	
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