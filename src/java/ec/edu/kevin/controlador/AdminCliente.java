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
@WebServlet("/adminCliente")
public class AdminCliente extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ClienteDAO clienteDAO;
    AuditoriaDAO auditoriaDAO;

    public void init() {
        try {
            clienteDAO = new ClienteDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            auditoriaDAO = new AuditoriaDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCliente() {
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
        auditoria(request, "Regresar menu", "Regresar", "Cliente");
        RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Cliente cliente = new Cliente(request.getParameter("nombre"), request.getParameter("ruc"), request.getParameter("direccion"));
        boolean flag = clienteDAO.insertar(cliente);
        if (flag) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
            dispatcher.forward(request, response);
        }
        String error = "Formato de RUC invalido";
        request.setAttribute("message", error);
        auditoria(request, "Registrar Cliente", "Registrar", "Cliente");
        mostrar(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Cliente/registerCliente.jsp");
        request.setAttribute("message", "");
        auditoria(request, "Crear Cliente", "Crear", "Cliente");
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Cliente/mostrarCliente.jsp");
        auditoria(request, "Mostrar cliente", "Mostar", "Cliente");
        List<Cliente> listaCliente = clienteDAO.listarClientes();
        request.setAttribute("lista", listaCliente);
        /*List<Auditoria> listaAuditorias = auditoriaDAO.listarAuditoria();
        request.setAttribute("listaAuditoria", listaAuditorias);*/
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Cliente cliente = clienteDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("cliente", cliente);
        auditoria(request, "Editar Cliente", "Editar", "Cliente");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Cliente/editarCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Cliente cliente = new Cliente(Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"), request.getParameter("ruc"), request.getParameter("direccion"));
        auditoria(request, "Actualizar Cliente", "Actualizar", "Cliente");
        clienteDAO.actualizar(cliente);
        mostrar(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Cliente cliente = clienteDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        clienteDAO.eliminar(cliente);
        List<Cliente> listaClientes = clienteDAO.listarClientes();
        auditoria(request, "Eliminar Cliente", "Eliminar", "Cliente");
        request.setAttribute("lista", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Cliente/mostrarCliente.jsp");
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
