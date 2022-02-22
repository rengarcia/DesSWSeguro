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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zurit
 */
@WebServlet("/adminCiudad")
public class AdminCiudad extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CiudadDAO ciudadDAO;
    AuditoriaDAO auditoriaDAO;

    public void init() {
        try {
            ciudadDAO = new CiudadDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            auditoriaDAO = new AuditoriaDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCiudad() {
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
                    System.out.println("entro");
                    registrar(request, response);
                    break;
                case "mostrar":
                    mostrar(request, response);
                    break;
                case "showedit":
                    showEditar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
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
        auditoria(request, "Regresar menu", "Regresar", "Ciudad");
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Ciudad ciudad = new Ciudad(request.getParameter("codigo"), request.getParameter("nombre"));
        ciudadDAO.insertar(ciudad);
        auditoria(request, "Registrar ciudad", "Registrar", "Ciudad");
        mostrar(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {Calendar calendario = Calendar.getInstance();
        auditoria(request, "Crear ciudad", "Crear", "Ciudad");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Ciudad/registerCiudad.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Ciudad/mostrarCiudad.jsp");
        auditoria(request, "Mostrar ciudad", "Mostar", "Ciudad");
        List<Ciudad> listaCiudades = ciudadDAO.listarCiudades();
        request.setAttribute("lista", listaCiudades);
        /*List<Auditoria> listaAuditorias = auditoriaDAO.listarAuditoria();
        request.setAttribute("listaAuditoria", listaAuditorias);*/
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Ciudad ciudad = ciudadDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("ciudad", ciudad);
        auditoria(request, "Editar ciudad", "Editar", "Ciudad");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Ciudad/editarCiudad.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Ciudad ciudad = new Ciudad(Integer.parseInt(request.getParameter("id")), request.getParameter("codigo"), request.getParameter("nombre"));
        auditoria(request, "Actualizar ciudad", "Actualizar", "Ciudad");
        ciudadDAO.actualizar(ciudad);
        mostrar(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Ciudad ciudad = ciudadDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        ciudadDAO.eliminar(ciudad);
        List<Ciudad> listaCiudades = ciudadDAO.listarCiudades();
        auditoria(request, "Eliminar ciudad", "Eliminar", "Ciudad");
        request.setAttribute("lista", listaCiudades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Ciudad/mostrarCiudad.jsp");
        dispatcher.forward(request, response);
    }

    private void auditoria(HttpServletRequest request, String actividad, String operacion, String pantalla)throws SQLException{
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
