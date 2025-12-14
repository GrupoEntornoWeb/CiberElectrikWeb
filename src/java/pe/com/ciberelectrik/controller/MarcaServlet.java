package pe.com.ciberelectrik.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pe.com.ciberelectrik.dao.MarcaDAO;
import pe.com.ciberelectrik.dao.impl.MarcaDAOImpl;
import pe.com.ciberelectrik.modelo.Marca;
import pe.com.ciberelectrik.util.StringManager;

@WebServlet(name = "MarcaServlet", urlPatterns = {"/MarcaServlet"})
public class MarcaServlet extends HttpServlet {

    // Declaramos el DAO
    private MarcaDAO daomar;
    // Declaramos variables
    private int cod = 0;
    private String nom = "";
    private boolean est = false;

    @Override
    public void init() throws ServletException {
        super.init();
        // Instanciamos el DAO
        daomar = new MarcaDAOImpl();
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
                    request.getRequestDispatcher("/marca/registrarmarca.jsp").forward(request, response);
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
            System.out.println("Error en doGet MarcaServlet: " + ex.toString());
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
            System.out.println("Error en doPost MarcaServlet: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestión de Marcas";
    }

    // Método para listar marcas habilitadas (findAllCustom)
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Marca> marcas = daomar.findAllCustom();
        request.setAttribute("marcas", marcas);
        request.getRequestDispatcher("/marca/listarmarca.jsp").forward(request, response);
    }

    // Método para listar todas las marcas (incluyendo deshabilitadas)
    private void findAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Marca> marcas = daomar.findAll();
        request.setAttribute("marcas", marcas);
        request.getRequestDispatcher("/marca/habilitarmarca.jsp").forward(request, response);
    }

    // Método para buscar una marca por ID
    private void find(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Marca obj = daomar.findById(id);
        request.setAttribute("marca", obj);
        request.getRequestDispatcher("/marca/actualizarmarca.jsp").forward(request, response);
    }

    // Método para registrar una nueva marca
    private void add(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            Marca obj = new Marca();
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daomar.add(obj);
            if (res) {
                response.sendRedirect("MarcaServlet");
            } else {
                response.sendRedirect("MarcaServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println("Error en add MarcaServlet: " + ex.toString());
        }
    }

    // Método para actualizar una marca existente
    private void update(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            cod = Integer.parseInt(request.getParameter("txtCod"));
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            Marca obj = new Marca();
            obj.setCodigo(cod);
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daomar.update(obj);
            if (res) {
                response.sendRedirect("MarcaServlet");
            } else {
                response.sendRedirect("MarcaServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println("Error en update MarcaServlet: " + ex.toString());
        }
    }

    // Método para eliminar (deshabilitar) una marca
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Marca obj = new Marca();
            obj.setCodigo(cod);
            
            boolean res = daomar.delete(obj);
            if (res) {
                response.sendRedirect("MarcaServlet");
            } else {
                response.sendRedirect("MarcaServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en delete MarcaServlet: " + ex.toString());
        }
    }

    // Método para habilitar una marca
    private void enable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Marca obj = new Marca();
            obj.setCodigo(cod);
            
            boolean res = daomar.enable(obj);
            if (res) {
                response.sendRedirect("MarcaServlet?accion=habilita");
            } else {
                response.sendRedirect("MarcaServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en enable MarcaServlet: " + ex.toString());
        }
    }

    // Método para deshabilitar una marca
    private void disable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Marca obj = new Marca();
            obj.setCodigo(cod);
            
            boolean res = daomar.disable(obj);
            if (res) {
                response.sendRedirect("MarcaServlet?accion=habilita");
            } else {
                response.sendRedirect("MarcaServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en disable MarcaServlet: " + ex.toString());
        }
    }
}