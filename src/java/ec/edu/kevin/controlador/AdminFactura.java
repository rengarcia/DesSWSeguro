/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.kevin.dao.*;
import ec.edu.kevin.modelo.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zurit
 */
@WebServlet("/adminFactura")
public class AdminFactura extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ClienteDAO clienteDAO;
    CiudadDAO ciudadDAO;
    FacturaDAO facturaDAO;
    ArticuloDAO articuloDAO;
    AuditoriaDAO auditoriaDAO;

    public void init() {
        try {
            clienteDAO = new ClienteDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            facturaDAO = new FacturaDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            ciudadDAO = new CiudadDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            articuloDAO = new ArticuloDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            auditoriaDAO = new AuditoriaDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFactura() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Hola Servlet..");
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            switch (action) {
                case "index":
                    index(request, response);
                    break;
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "register":
                    registrar(request, response);
                    break;
                case "mostrar":
                    mostrar(request, response);
                    break;
                case "showedit":
                    showEditar(request, response);
                    break;
                case "showeditdetalle":
                    showEditarDetalle(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "editarDetalle":
                    editarDetalle(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
                case "eliminarCabecera":
                    eliminarCabecera(request, response);
                    break;
                case "mostrarFactura":
                    mostrarFactura(request, response);
                    break;
                case "reporteventas":
                    reporteventas(request, response);
                    break;
                case "reportecruzado":
                    reportecruzado(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Hola Servlet..");
        doGet(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        //mostrar(request, response);
        auditoria(request, "Regresar menu", "Regresar", "Factura");
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String[] idArticulos = request.getParameterValues("articuloSeleccionado");
        String[] cantidadArticulos = request.getParameterValues("cantidadSeleccionada");
        String[] precioArticulos = request.getParameterValues("precioSeleccionado");
        CabeceraFactura cabecera = new CabeceraFactura(request.getParameter("fecha"), Integer.parseInt(request.getParameter("ciudad")), Integer.parseInt(request.getParameter("cliente")));
        int id = facturaDAO.insertarCabecera(cabecera);
        System.out.println(id);
        for (int i = 0; i < idArticulos.length; i++) {
            DetalleFactura detalle = new DetalleFactura(i, id, Integer.parseInt(idArticulos[i]), Integer.parseInt(cantidadArticulos[i]), Double.parseDouble(precioArticulos[i]));
            System.out.println(detalle.toString());
            facturaDAO.insertarDetalle(id, detalle);
        }
        auditoria(request, "Registrar Factura", "Registrar", "Factura");
        mostrar(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Cliente> listaClientes = clienteDAO.listarClientes();
        request.setAttribute("listaCliente", listaClientes);
        List<Ciudad> listaCiudades = ciudadDAO.listarCiudades();
        request.setAttribute("listaCiudad", listaCiudades);
        List<Articulo> listaArticulos = articuloDAO.listarArticulos();
        request.setAttribute("listaArticulo", listaArticulos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Factura/registerFactura.jsp");
        auditoria(request, "Crear Factura", "Registrar", "Factura");
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<CabeceraFactura> listaCabecera = facturaDAO.listarCabeceras();
        request.setAttribute("lista", listaCabecera);
        auditoria(request, "Mostrar Factura", "Mostrar", "Factura");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Factura/mostrarCabecera.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFactura(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        CabeceraFactura cabecera = facturaDAO.obtenerPorIdCabecera(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("cabecerafactura", cabecera);
        List<DetalleFactura> listaDetalles = facturaDAO.obtenerPorIdDetalle(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("lista", listaDetalles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Factura/mostrarFactura.jsp");
        auditoria(request, "Mostrar Factura detalle", "Mostrar", "Factura");
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        CabeceraFactura cabecera = facturaDAO.obtenerPorIdCabecera(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("cabecerafactura", cabecera);
        List<Cliente> listaClientes = clienteDAO.listarClientes();
        request.setAttribute("listaCliente", listaClientes);
        List<Ciudad> listaCiudades = ciudadDAO.listarCiudades();
        request.setAttribute("listaCiudad", listaCiudades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Factura/editarCabecera.jsp");
        auditoria(request, "Editar Factura", "Editar", "Factura");
        dispatcher.forward(request, response);
    }

    private void showEditarDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        DetalleFactura detalle = facturaDAO.obtenerPorDetalle(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("detalle", detalle);
        List<Articulo> listaArticulos = articuloDAO.listarArticulos();
        request.setAttribute("listaArticulo", listaArticulos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Factura/editarDetalle.jsp");
        auditoria(request, "Editar Factura detalle", "Editar", "Factura");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        CabeceraFactura cabecera = new CabeceraFactura(Integer.parseInt(request.getParameter("id")), request.getParameter("fecha"), Integer.parseInt(request.getParameter("ciudad")), Integer.parseInt(request.getParameter("cliente")));
        System.out.println(cabecera.getId());
        auditoria(request, "Actualizar Factura", "Actualizar", "Factura");
        facturaDAO.actualizarCabecera(cabecera);
        mostrar(request, response);
    }

    private void editarDetalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        System.out.println("Ingreso a editar detalle");
        DetalleFactura detalle = new DetalleFactura(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("idFactura")), Integer.parseInt(request.getParameter("articuloSeleccionado")), Integer.parseInt(request.getParameter("cantidad")), Double.parseDouble(request.getParameter("precio")));
        facturaDAO.actualizarDetalle(detalle);
        auditoria(request, "Actualizar Factura detalle", "Actualizar", "Factura");
        mostrar(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        facturaDAO.eliminarDetalle(Integer.parseInt(request.getParameter("id")));
        auditoria(request, "Eliminar Factura", "Eliminar", "Factura");
        mostrar(request, response);
    }

    private void eliminarCabecera(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        facturaDAO.eliminarCabecera(Integer.parseInt(request.getParameter("id")));
        facturaDAO.eliminarDetalle(Integer.parseInt(request.getParameter("id")));
        List<CabeceraFactura> listaCabecera = facturaDAO.listarCabeceras();
        request.setAttribute("lista", listaCabecera);
        auditoria(request, "Eliminar Factura detalle", "Eliminar", "Factura");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Factura/mostrarCabecera.jsp");
        dispatcher.forward(request, response);
    }

    private void reporteventas(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Reporte/reporteVentasCiudad.jsp");
        List<ReporteVentaCiudad> listaReporte = facturaDAO.reporteVentas();
        auditoria(request, "Reporte Factura", "Reporte", "Factura");
        request.setAttribute("lista", listaReporte);
        dispatcher.forward(request, response);
    }

    private void reportecruzado(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Reporte/reporteCruzado.jsp");
        String[][] matriz = facturaDAO.reportecruzado();
        auditoria(request, "Reporte  cruzado Factura", "Reporte", "Factura");
        request.setAttribute("lista", matriz);
        dispatcher.forward(request, response);
    }

    private void auditoria(HttpServletRequest request, String actividad, String operacion, String pantalla) throws SQLException {
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);
        String IP = getIpAddr(request);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        String fecha = localDate.format(formatter);
        HttpSession ses = request.getSession();
        String horaActual = String.valueOf(hora) + ":" + String.valueOf(minutos) + ":" + String.valueOf(segundos);
        Auditoria auditoria = new Auditoria(IP, fecha, actividad, operacion, (String) ses.getAttribute("usuario"), pantalla, horaActual);
        auditoriaDAO.insertar(auditoria);
    }
}
