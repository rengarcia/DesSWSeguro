/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.kevin.controlador;

import ec.edu.kevin.dao.AuditoriaDAO;
import ec.edu.kevin.dao.UsuarioDAO;
import ec.edu.kevin.modelo.Auditoria;
import ec.edu.kevin.modelo.Ciudad;
import ec.edu.kevin.modelo.Usuario;
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
@WebServlet("/adminUsuario")
public class AdminUsuario extends HttpServlet {

    private static final long serialVersionUID = 1L;
    UsuarioDAO usuarioDAO;
    AuditoriaDAO auditoriaDAO;

    public void init() {
        try {
            usuarioDAO = new UsuarioDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
            auditoriaDAO = new AuditoriaDAO("jdbc:oracle:thin:@localhost:1521:orcl", "KMZURITA", "ROOT");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUsuario() {
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
                case "ingresar":
                    ingresar(request, response);
                    break;
                case "salir":
                    salir(request, response);
                    break;
                case "crear":
                    crear(request, response);
                    break;
                case "crearUsuario":
                    crearUsuario(request, response);
                    break;
                case "reporteAuditoria":
                    reporteAuditoria(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void ingresar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Usuario usuario = new Usuario(request.getParameter("user"), request.getParameter("pass"));
        int flag = usuarioDAO.ingresar(usuario);
        if (flag != -1) {
            if (flag <= 1) {
                HttpSession ses = request.getSession();
                ses.setAttribute("usuario", request.getParameter("user"));
                ses.setAttribute("password", request.getParameter("pass"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
                dispatcher.forward(request, response);
                auditoria(request, "Ingresar pantalla", "Ingresar", "Login");

            } else {
                HttpSession ses = request.getSession();
                usuarioDAO.cerrado(usuario);
                ses.invalidate();
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void salir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        Usuario user = new Usuario((String) session.getAttribute("usuario"), (String) session.getAttribute("password"));
        usuarioDAO.cerrado(user);
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        boolean flag = false;
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        String mail = request.getParameter("mail");
        if (pass1.equals(pass2)) {
            flag = true;
        }
        if (flag) {
            System.out.println("XD");
            Usuario usuario = new Usuario(request.getParameter("user"), pass1, mail);
            usuarioDAO.insertar(usuario);
            System.out.println("XD2");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("crearUsuario.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("crearUsuario.jsp");
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

    private void reporteAuditoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Reporte/reporteAuditoria.jsp");
        auditoria(request, "Reporte Auditoria", "Reporte", "Auditoria");
        List<Auditoria> listaAuditorias = auditoriaDAO.listarAuditoria();
        request.setAttribute("listaAuditoria", listaAuditorias);
        dispatcher.forward(request, response);
    }
}
