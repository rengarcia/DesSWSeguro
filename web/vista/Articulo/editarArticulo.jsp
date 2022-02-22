<%-- 
    Document   : editarArticulo
    Created on : 25-nov-2021, 1:36:07
    Author     : andre
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Actualizar Articulo</title>
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
        <h3 align="center">Actualizar Articulo</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table align="center">
            <tr>
                <td><a href="adminArticulo?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <form action="adminArticulo?action=editar" method="post" >
            <table align="center">
                <tr>
                    <td><label>Id</label></td>
                    <td><input type="text" name="id" value="<c:out value="${articulo.id}"></c:out>" readonly></td>
                    </tr>
                    <tr>
                        <td><label>Codigo </label></td>
                        <td><input type="text" name="codigo" pattern="^(\d{13})?$" value='<c:out value="${articulo.codigo}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Nombre </label></td>
                        <td><input type="text" name="nombre" pattern="^[a-zA-Z\s]*$" value='<c:out value="${articulo.nombre}"></c:out>' ></td>
                    </tr>
                    <tr>
                        <td><label>Precio</label></td>
                        <td><input type="text" pattern="^\d+(,\d{1,2})?$" name="precio" value='<c:out value="${articulo.precioArticulo}"></c:out>' ></td>
                </tr>
            </table>
            <p align="center">
                <input type="submit" name="registrar" value="Guardar"> 

            </p>
        </form>

    </body>
</html>