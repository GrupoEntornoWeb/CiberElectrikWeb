package pe.com.ciberelectrik.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pe.com.ciberelectrik.dao.SexoDAO;
import pe.com.ciberelectrik.dao.impl.SexoDAOImpl;
import pe.com.ciberelectrik.modelo.Sexo;
import pe.com.ciberelectrik.util.StringManager;

@WebServlet(name = "SexoServlet", urlPatterns = {"/SexoServlet"})
public class SexoServlet extends HttpServlet {

    // Declaramos el DAO
    private SexoDAO daosex;
    // Declaramos variables
    private int cod = 0;
    private String nom = "";
    private boolean est = false;

    @Override
    public void init() throws ServletException {
        super.init();
        // Instanciamos el DAO
        daosex = new SexoDAOImpl();
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
                    request.getRequestDispatcher("/sexo/registrarsexo.jsp").forward(request, response);
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
            System.out.println("Error en doGet SexoServlet: " + ex.toString());
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
            System.out.println("Error en doPost SexoServlet: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestión de Sexos";
    }

    // Método para listar sexos habilitados (findAllCustom)
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Sexo> sexos = daosex.findAllCustom();
        request.setAttribute("sexos", sexos);
        request.getRequestDispatcher("/sexo/listarsexo.jsp").forward(request, response);
    }

    // Método para listar todos los sexos (incluyendo deshabilitados)
    private void findAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Sexo> sexos = daosex.findAll();
        request.setAttribute("sexos", sexos);
        request.getRequestDispatcher("/sexo/habilitarsexo.jsp").forward(request, response);
    }

    // Método para buscar un sexo por ID
    private void find(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Sexo obj = daosex.findById(id);
        request.setAttribute("sexo", obj);
        request.getRequestDispatcher("/sexo/actualizarsexo.jsp").forward(request, response);
    }

    // Método para registrar un nuevo sexo
    private void add(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            Sexo obj = new Sexo();
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daosex.add(obj);
            if (res) {
                response.sendRedirect("SexoServlet");
            } else {
                response.sendRedirect("SexoServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println("Error en add SexoServlet: " + ex.toString());
        }
    }

    // Método para actualizar un sexo existente
    private void update(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            cod = Integer.parseInt(request.getParameter("txtCod"));
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            Sexo obj = new Sexo();
            obj.setCodigo(cod);
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daosex.update(obj);
            if (res) {
                response.sendRedirect("SexoServlet");
            } else {
                response.sendRedirect("SexoServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println("Error en update SexoServlet: " + ex.toString());
        }
    }

    // Método para eliminar (deshabilitar) un sexo
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Sexo obj = new Sexo();
            obj.setCodigo(cod);
            
            boolean res = daosex.delete(obj);
            if (res) {
                response.sendRedirect("SexoServlet");
            } else {
                response.sendRedirect("SexoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en delete SexoServlet: " + ex.toString());
        }
    }

    // Método para habilitar un sexo
    private void enable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Sexo obj = new Sexo();
            obj.setCodigo(cod);
            
            boolean res = daosex.enable(obj);
            if (res) {
                response.sendRedirect("SexoServlet?accion=habilita");
            } else {
                response.sendRedirect("SexoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en enable SexoServlet: " + ex.toString());
        }
    }

    // Método para deshabilitar un sexo
    private void disable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Sexo obj = new Sexo();
            obj.setCodigo(cod);
            
            boolean res = daosex.disable(obj);
            if (res) {
                response.sendRedirect("SexoServlet?accion=habilita");
            } else {
                response.sendRedirect("SexoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en disable SexoServlet: " + ex.toString());
        }
    }
}