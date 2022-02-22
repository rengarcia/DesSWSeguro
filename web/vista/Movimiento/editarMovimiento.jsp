<%-- 
    Document   : editarMovimiento
    Created on : 25-nov-2021, 10:54:48
    Author     : andre
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actualizar Movimiento</title>
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
        <h3 align="center">Actualizar Movimiento</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table align="center">
            <tr>
                <td><a href="adminMovimiento?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <form action="adminMovimiento?action=editar" method="post" >
            <table align="center">
                <tr>
                    <td><label>Id</label></td>
                    <td><input type="text" name="id" value="<c:out value="${movimiento.id}"></c:out>" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Codigo </label></td>
                        <td><input type="text" name="codigo" value='<c:out value="${movimiento.codigo}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Nombre </label></td>
                        <td><input type="text" name="nombre" value='<c:out value="${movimiento.nombre}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Signo Movimiento</label></td>
                        <td><input type="text" name="precio" value='<c:out value="${movimiento.signoMovimiento}"></c:out>' ></td>
                </tr>
            </table>
            <p align="center">
                <input type="submit" name="registrar" value="Guardar"> 
            </p>
        </form>

    </body>
</html>
