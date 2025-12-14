package pe.com.ciberelectrik.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pe.com.ciberelectrik.dao.DistritoDAO;
import pe.com.ciberelectrik.dao.impl.DistritoDAOImpl;
import pe.com.ciberelectrik.modelo.Distrito;
import pe.com.ciberelectrik.util.StringManager;

@WebServlet(name = "DistritoServlet", urlPatterns = {"/DistritoServlet"})
public class DistritoServlet extends HttpServlet {

    // Declaramos el DAO
    private DistritoDAO daodis;
    // Declaramos variables
    private int cod = 0;
    private String nom = "";
    private boolean est = false;

    @Override
    public void init() throws ServletException {
        super.init();
        // Instanciamos el DAO
        daodis = new DistritoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) {
                accion = "listar";
            }
            
            switch (accion) {
                // Rutas
                case "registro":
                    request.getRequestDispatcher("/distrito/registrardistrito.jsp").forward(request, response);
                    break;
                case "actualiza":
                    find(request, response);
                    break;
                case "habilita":
                    findAll(request, response);
                    break;
                case "regresar":
                    findAllCustom(request, response);
                    break;
                case "menu":
                    request.getRequestDispatcher("/menuprincipal/mainmenu.jsp").forward(request, response);
                    break;
                // Acciones
                case "eliminar":
                    delete(request, response);
                    break;
                case "habilitar":
                    enable(request, response);
                    break;
                case "deshabilitar":
                    disable(request, response);
                    break;
                default:
                    findAllCustom(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            System.out.println("Error en doGet DistritoServlet: " + ex.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "registrar":
                    request.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=UTF-8");
                    add(request, response);
                    break;
                case "actualizar":
                    request.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=UTF-8");
                    update(request, response);
                    break;
                case "regresar":
                    break;
                default:
                    break;
            }
        } catch (ServletException | IOException ex) {
            System.out.println("Error en doPost DistritoServlet: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestión de Distritos";
    }

    // Método para listar distritos habilitados (findAllCustom)
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Distrito> distritos = daodis.findAllCustom();
        request.setAttribute("distritos", distritos);
        request.getRequestDispatcher("/distrito/listardistrito.jsp").forward(request, response);
    }

    // Método para listar todos los distritos (incluyendo deshabilitados)
    private void findAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Distrito> distritos = daodis.findAll();
        request.setAttribute("distritos", distritos);
        request.getRequestDispatcher("/distrito/habilitardistrito.jsp").forward(request, response);
    }

    // Método para buscar un distrito por ID
    private void find(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Distrito obj = daodis.findById(id);
        request.setAttribute("distrito", obj);
        request.getRequestDispatcher("/distrito/actualizardistrito.jsp").forward(request, response);
    }

    // Método para registrar un nuevo distrito
    private void add(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            Distrito obj = new Distrito();
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daodis.add(obj);
            if (res) {
                response.sendRedirect("DistritoServlet");
            } else {
                response.sendRedirect("DistritoServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println("Error en add DistritoServlet: " + ex.toString());
        }
    }

    // Método para actualizar un distrito existente
    private void update(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            cod = Integer.parseInt(request.getParameter("txtCod"));
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            Distrito obj = new Distrito();
            obj.setCodigo(cod);
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daodis.update(obj);
            if (res) {
                response.sendRedirect("DistritoServlet");
            } else {
                response.sendRedirect("DistritoServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println("Error en update DistritoServlet: " + ex.toString());
        }
    }

    // Método para eliminar (deshabilitar) un distrito
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Distrito obj = new Distrito();
            obj.setCodigo(cod);
            
            boolean res = daodis.delete(obj);
            if (res) {
                response.sendRedirect("DistritoServlet");
            } else {
                response.sendRedirect("DistritoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en delete DistritoServlet: " + ex.toString());
        }
    }

    // Método para habilitar un distrito
    private void enable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Distrito obj = new Distrito();
            obj.setCodigo(cod);
            
            boolean res = daodis.enable(obj);
            if (res) {
                response.sendRedirect("DistritoServlet?accion=habilita");
            } else {
                response.sendRedirect("DistritoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en enable DistritoServlet: " + ex.toString());
        }
    }

    // Método para deshabilitar un distrito
    private void disable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Distrito obj = new Distrito();
            obj.setCodigo(cod);
            
            boolean res = daodis.disable(obj);
            if (res) {
                response.sendRedirect("DistritoServlet?accion=habilita");
            } else {
                response.sendRedirect("DistritoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en disable DistritoServlet: " + ex.toString());
        }
    }
}