<%-- 
    Document   : registerComprobante
    Created on : 29 nov. 2021, 22:23:14
    Author     : zurit
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registrar Comprobante</title>
        <script>
            function addRow() {
                var tableRef = document.getElementById("tablaDetalles");
                var newRow = tableRef.insertRow(-1);
                var newCell = newRow.insertCell(-1);
                newCell.innerHTML = document.getElementById("celdaArticulo").innerHTML;
                var newCell = newRow.insertCell(-1);
                newCell.innerHTML = document.getElementById("inputCantidad").innerHTML;
                var newCell = newRow.insertCell(-1);
                newCell.innerHTML = document.getElementById("inputPrecio").innerHTML;
                var newCell = newRow.insertCell(-1);
                newCell.innerHTML = document.getElementById("EliminarFila").innerHTML;
            }
            function deleteRow(btn) {
                var row = btn.parentNode.parentNode;
                row.parentNode.removeChild(row);
            }
            function calcularPrecio(sel){
                var row = sel.parentNode.parentNode;
                var cantidad = row.cells[1].children[0].value;
                var valor = sel.options[sel.selectedIndex].getAttribute("name");
                row.cells[2].children[0].value=valor*cantidad;
            }
            function cambiarPrecio(sel){
                var row = sel.parentNode.parentNode;
                var index = row.cells[0].children[0].selectedIndex;
                var valor = row.cells[0].children[0].children[index].getAttribute("name");
                var cantidad = sel.value;
                var total = valor*cantidad;
                row.cells[2].children[0].value=total;
            }
        </script>
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
        <h3 align="center">Registrar Comprobante</h3>
        <a href="adminUsuario?action=salir" style="justify-content: flex-end; display: flex;">Salir</a>
        <table>
            <tr>
                <td><a href="adminComprobante?action=mostrar" >Regresar</a> </td>
            </tr>
        </table>
        <br>
        <form action="adminComprobante?action=register" method="post">
            <table border="1" align="center">
                <tr>
                    <td>Fecha:</a></td>		
                    <td><input type="date" name="fecha"/></td>	
                </tr>
                <tr>
                    <td>Movimiento</a></td>		
                    <td> 
                        <select name="movimiento">
                            <c:forEach var="movimiento" items="${listaMovimiento}">
                                <option value="<c:out value="${movimiento.id}"/>"><c:out value="${movimiento.nombre}"/></option>
                            </c:forEach>
                        </select>
                    </td>	
                </tr>

            </table>
            <br>
            <button type="button" onclick='addRow();'>Añadir otro articulo</button>
            <table border="1" width="100%" name="tablaDetalles" id="tablaDetalles">
                <tr>
                    <td> ARTICULO</td>
                    <td> CANTIDAD</td>
                    <td> PRECIO</td>
                    <td> ACCIONES</td>
                </tr>
                <tr>
                    <td id="celdaArticulo" value="<c:out value="${articulo.idArticulo}"/>">
                        <select name="articuloSeleccionado" id="selectArticulo" onchange="calcularPrecio(this);">
                            <c:forEach var="articulo" items="${listaArticulo}">
                                <option name="<c:out value="${articulo.precioArticulo}"/>" value="<c:out value="${articulo.id}"/>"><c:out value="${articulo.nombre}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                    <td id="inputCantidad"><input id="cSeleccionada" type="number" name="cantidadSeleccionada" value='1' min="0" onchange="cambiarPrecio(this);"></td>
                    <td id="inputPrecio"> <input id="pSeleccionada" type="number" name="precioSeleccionado" readonly></td>	
                    <td id="EliminarFila"><button type="button" onclick='deleteRow(this)'>Borrar</button> </td>	
                </tr>
            </table>
            <br>
            <table border="0" align="center">
                <tr>
                    <td><input type="submit" value="Agregar" name="agregar"></td>	
                </tr>
            </table>
        </form>
    </body>
</html>
