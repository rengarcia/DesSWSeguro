/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.controlador;

import ec.edu.kevin.dao.ArticuloDAO;
import ec.edu.kevin.dao.AuditoriaDAO;
import ec.edu.kevin.dao.MovimientoDAO;
import ec.edu.kevin.dao.ComprobanteDAO;
import ec.edu.kevin.modelo.Articulo;
import ec.edu.kevin.modelo.Auditoria;
import ec.edu.kevin.modelo.CabeceraComprobante;
import ec.edu.kevin.modelo.Movimiento;
import ec.edu.kevin.modelo.DetalleComprobante;
import ec.edu.kevin.modelo.ReporteSaldoArticulo;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zurit
 */
@WebServlet("/adminComprobante")
public class AdminComprobante extends HttpServlet {

    private static final long serialVersionUID = 1L;
    MovimientoDAO movimientoDAO;
    ComprobanteDAO comprobanteDAO;
    ArticuloDAO articuloDAO;
    AuditoriaDAO auditoriaDAO;

    public void init() {
        try {
            comprobanteDAO = new ComprobanteDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            movimientoDAO = new MovimientoDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            articuloDAO = new ArticuloDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            auditoriaDAO = new AuditoriaDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminComprobante() {
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
                case "mostrarComprobante":
                    mostrarComprobante(request, response);
                    break;
                case "reportesaldos":
                    reportesaldos(request, response);
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
        auditoria(request, "Regresar menu", "Regresar", "Comprobante");
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String[] idArticulos = request.getParameterValues("articuloSeleccionado");
        String[] cantidadArticulos = request.getParameterValues("cantidadSeleccionada");
        String[] precioArticulos = request.getParameterValues("precioSeleccionado");
        CabeceraComprobante cabecera = new CabeceraComprobante(request.getParameter("fecha"), Integer.parseInt(request.getParameter("movimiento")));
        int id = comprobanteDAO.insertarCabecera(cabecera);
        for (int i = 0; i < idArticulos.length; i++) {
            DetalleComprobante detalle = new DetalleComprobante(i, id, Integer.parseInt(idArticulos[i]), Integer.parseInt(cantidadArticulos[i]), Double.parseDouble(precioArticulos[i]));
            comprobanteDAO.insertarDetalle(id, detalle);
        }
        auditoria(request, "Registrar comprobamte", "Registrar", "Comprobante");
        mostrar(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Movimiento> listaMovimientos = movimientoDAO.listarMovimientos();
        request.setAttribute("listaMovimiento", listaMovimientos);
        List<Articulo> listaArticulos = articuloDAO.listarArticulos();
        request.setAttribute("listaArticulo", listaArticulos);
        auditoria(request, "Crear comprobamte", "Crear", "Comprobante");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Comprobante/registerComprobante.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Comprobante/mostrarCabeceraComprobante.jsp");
        List<CabeceraComprobante> listaComprobante = comprobanteDAO.listarCabeceras();
        auditoria(request, "Mostrar comprobamte", "Mostrar", "Comprobante");
        request.setAttribute("lista", listaComprobante);
        dispatcher.forward(request, response);
    }

    private void mostrarComprobante(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        CabeceraComprobante cabecera = comprobanteDAO.obtenerPorIdCabecera(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("cabeceracomprobante", cabecera);
        List<DetalleComprobante> listaDetalles = comprobanteDAO.obtenerPorIdDetalle(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("lista", listaDetalles);
        auditoria(request, "Mostrar comprobamte completo", "Mostrar", "Comprobante");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Comprobante/mostrarComprobante.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        CabeceraComprobante cabecera = comprobanteDAO.obtenerPorIdCabecera(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("cabeceracomprobante", cabecera);
        List<Movimiento> listaMovimientos = movimientoDAO.listarMovimientos();
        request.setAttribute("listaMovimiento", listaMovimientos);
        auditoria(request, "Editar comprobamte", "Editar", "Comprobante");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Comprobante/editarCabeceraComprobante.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditarDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        DetalleComprobante detalle = comprobanteDAO.obtenerPorDetalle(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("detalle", detalle);
        List<Articulo> listaArticulos = articuloDAO.listarArticulos();
        request.setAttribute("listaArticulo", listaArticulos);
        auditoria(request, "Editar comprobamte detalle", "Editar", "Comprobante");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Comprobante/editarDetalleComprobante.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        CabeceraComprobante cabecera = new CabeceraComprobante(Integer.parseInt(request.getParameter("id")), request.getParameter("fecha"), Integer.parseInt(request.getParameter("movimiento")));
        comprobanteDAO.actualizarCabecera(cabecera);
        auditoria(request, "Actualizar comprobamte", "Actualizar", "Comprobante");
        mostrar(request, response);
    }

    private void editarDetalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        DetalleComprobante detalle = new DetalleComprobante(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("idComprobante")), Integer.parseInt(request.getParameter("articuloSeleccionado")), Integer.parseInt(request.getParameter("cantidad")), Double.parseDouble(request.getParameter("precio")));
        auditoria(request, "Actualizar detalle comprobamte", "Actualizar", "Comprobante");
        comprobanteDAO.actualizarDetalle(detalle);
        mostrar(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        comprobanteDAO.eliminarDetalle(Integer.parseInt(request.getParameter("id")));
        auditoria(request, "Eliminar detalle comprobamte", "Eliminar", "Comprobante");
        mostrar(request, response);
    }

    private void eliminarCabecera(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        comprobanteDAO.eliminarCabecera(Integer.parseInt(request.getParameter("id")));
        comprobanteDAO.eliminarDetalle(Integer.parseInt(request.getParameter("id")));
        List<CabeceraComprobante> listaComprobante = comprobanteDAO.listarCabeceras();
        request.setAttribute("lista", listaComprobante);
        auditoria(request, "Eliminar comprobamte", "Eliminar", "Comprobante");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Comprobante/mostrarCabeceraComprobante.jsp");
        dispatcher.forward(request, response);
    }

    private void reportesaldos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Reporte/reporteSaldoArticulo.jsp");
        List<ReporteSaldoArticulo> listaReporte = comprobanteDAO.reporteVentas();
        auditoria(request, "Reporte saldos", "Reporte", "Comprobante");
        request.setAttribute("lista", listaReporte);
        dispatcher.forward(request, response);
    }

    private void reportecruzado(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Reporte/reporteCruzado.jsp");
        auditoria(request, "Reporte cruzado comprobamte", "Reporte", "Comprobante");
        String[][] matriz = comprobanteDAO.reportecruzado();
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
