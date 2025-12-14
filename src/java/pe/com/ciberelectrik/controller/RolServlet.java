package pe.com.ciberelectrik.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pe.com.ciberelectrik.dao.RolDAO;
import pe.com.ciberelectrik.dao.impl.RolDAOImpl;
import pe.com.ciberelectrik.modelo.Rol;
import pe.com.ciberelectrik.util.StringManager;

@WebServlet(name = "RolServlet", urlPatterns = {"/RolServlet"})
public class RolServlet extends HttpServlet {

    // Declaramos el DAO
    private RolDAO daorol;
    // Declaramos variables
    private int cod = 0;
    private String nom = "";
    private boolean est = false;

    @Override
    public void init() throws ServletException {
        super.init();
        // Instanciamos el DAO
        daorol = new RolDAOImpl();
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
                    request.getRequestDispatcher("/rol/registrarrol.jsp").forward(request, response);
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
            System.out.println("Error en doGet RolServlet: " + ex.toString());
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
            System.out.println("Error en doPost RolServlet: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestión de Roles";
    }

    // Método para listar roles habilitados (findAllCustom)
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Rol> roles = daorol.findAllCustom();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/rol/listarrol.jsp").forward(request, response);
    }

    // Método para listar todos los roles (incluyendo deshabilitados)
    private void findAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Rol> roles = daorol.findAll();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/rol/habilitarrol.jsp").forward(request, response);
    }

    // Método para buscar un rol por ID
    private void find(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Rol obj = daorol.findById(id);
        request.setAttribute("rol", obj);
        request.getRequestDispatcher("/rol/actualizarrol.jsp").forward(request, response);
    }

    // Método para registrar un nuevo rol
    private void add(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            Rol obj = new Rol();
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daorol.add(obj);
            if (res) {
                response.sendRedirect("RolServlet");
            } else {
                response.sendRedirect("RolServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println("Error en add RolServlet: " + ex.toString());
        }
    }

    // Método para actualizar un rol existente
    private void update(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            cod = Integer.parseInt(request.getParameter("txtCod"));
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            Rol obj = new Rol();
            obj.setCodigo(cod);
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daorol.update(obj);
            if (res) {
                response.sendRedirect("RolServlet");
            } else {
                response.sendRedirect("RolServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println("Error en update RolServlet: " + ex.toString());
        }
    }

    // Método para eliminar (deshabilitar) un rol
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Rol obj = new Rol();
            obj.setCodigo(cod);
            
            boolean res = daorol.delete(obj);
            if (res) {
                response.sendRedirect("RolServlet");
            } else {
                response.sendRedirect("RolServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en delete RolServlet: " + ex.toString());
        }
    }

    // Método para habilitar un rol
    private void enable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Rol obj = new Rol();
            obj.setCodigo(cod);
            
            boolean res = daorol.enable(obj);
            if (res) {
                response.sendRedirect("RolServlet?accion=habilita");
            } else {
                response.sendRedirect("RolServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en enable RolServlet: " + ex.toString());
        }
    }

    // Método para deshabilitar un rol
    private void disable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Rol obj = new Rol();
            obj.setCodigo(cod);
            
            boolean res = daorol.disable(obj);
            if (res) {
                response.sendRedirect("RolServlet?accion=habilita");
            } else {
                response.sendRedirect("RolServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en disable RolServlet: " + ex.toString());
        }
    }
}