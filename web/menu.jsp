<%-- 
    Document   : menu
    Created on : 24 nov. 2021, 22:43:09
    Author     : zurit
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Proyecto Desarrollo Seguro</title>
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
        <h2 align="center">Sistema de facturación e inventario</h2>
        <table border="1" width="30%" align="center">
            <tr>
                <td align="center"><a href="adminCliente?action=mostrar">Administrar Clientes</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminCiudad?action=mostrar">Administrar Ciudad</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminArticulo?action=mostrar">Administrar Articulo</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminMovimiento?action=mostrar">Administrar Movimiento</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminFactura?action=mostrar">Administrar Facturas</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminComprobante?action=mostrar">Administrar Comprobantes</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminFactura?action=reporteventas">Reporte de ventas Facturacion</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminComprobante?action=reportesaldos">Reporte de saldos comprobante</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminFactura?action=reportecruzado">Reporte de venta de articulos por cliente</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminComprobante?action=reportecruzado">Reporte de movimientos de saldos por articulos</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminUsuario?action=reporteAuditoria">Auditoria de la aplicacion</a></td>
            </tr>
            <tr>
                <td align="center"><a href="adminUsuario?action=salir">Salir</a></td>
            </tr>
        </table>

    </body>
</html>