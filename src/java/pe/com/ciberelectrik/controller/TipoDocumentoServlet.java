package pe.com.ciberelectrik.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pe.com.ciberelectrik.dao.TipoDocumentoDAO;
import pe.com.ciberelectrik.dao.impl.TipoDocumentoDAOImpl;
import pe.com.ciberelectrik.modelo.TipoDocumento;
import pe.com.ciberelectrik.util.StringManager;

@WebServlet(name = "TipoDocumentoServlet", urlPatterns = {"/TipoDocumentoServlet"})
public class TipoDocumentoServlet extends HttpServlet {

    // Declaramos el DAO
    private TipoDocumentoDAO daotipdoc;
    // Declaramos variables
    private int cod = 0;
    private String nom = "";
    private boolean est = false;

    @Override
    public void init() throws ServletException {
        super.init();
        // Instanciamos el DAO
        daotipdoc = new TipoDocumentoDAOImpl();
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
                    request.getRequestDispatcher("/tipodocumento/registrartipodocumento.jsp").forward(request, response);
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
            System.out.println("Error en doGet TipoDocumentoServlet: " + ex.toString());
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
            System.out.println("Error en doPost TipoDocumentoServlet: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestión de Tipos de Documento";
    }

    // Método para listar tipos de documento habilitados (findAllCustom)
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<TipoDocumento> tiposDocumento = daotipdoc.findAllCustom();
        request.setAttribute("tiposDocumento", tiposDocumento);
        request.getRequestDispatcher("/tipodocumento/listartipodocumento.jsp").forward(request, response);
    }

    // Método para listar todos los tipos de documento (incluyendo deshabilitados)
    private void findAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<TipoDocumento> tiposDocumento = daotipdoc.findAll();
        request.setAttribute("tiposDocumento", tiposDocumento);
        request.getRequestDispatcher("/tipodocumento/habilitartipodocumento.jsp").forward(request, response);
    }

    // Método para buscar un tipo de documento por ID
    private void find(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TipoDocumento obj = daotipdoc.findById(id);
        request.setAttribute("tipoDocumento", obj);
        request.getRequestDispatcher("/tipodocumento/actualizartipodocumento.jsp").forward(request, response);
    }

    // Método para registrar un nuevo tipo de documento
    private void add(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            TipoDocumento obj = new TipoDocumento();
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daotipdoc.add(obj);
            if (res) {
                response.sendRedirect("TipoDocumentoServlet");
            } else {
                response.sendRedirect("TipoDocumentoServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println("Error en add TipoDocumentoServlet: " + ex.toString());
        }
    }

    // Método para actualizar un tipo de documento existente
    private void update(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            cod = Integer.parseInt(request.getParameter("txtCod"));
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            
            TipoDocumento obj = new TipoDocumento();
            obj.setCodigo(cod);
            obj.setNombre(nom);
            obj.setEstado(est);
            
            boolean res = daotipdoc.update(obj);
            if (res) {
                response.sendRedirect("TipoDocumentoServlet");
            } else {
                response.sendRedirect("TipoDocumentoServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println("Error en update TipoDocumentoServlet: " + ex.toString());
        }
    }

    // Método para eliminar (deshabilitar) un tipo de documento
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            TipoDocumento obj = new TipoDocumento();
            obj.setCodigo(cod);
            
            boolean res = daotipdoc.delete(obj);
            if (res) {
                response.sendRedirect("TipoDocumentoServlet");
            } else {
                response.sendRedirect("TipoDocumentoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en delete TipoDocumentoServlet: " + ex.toString());
        }
    }

    // Método para habilitar un tipo de documento
    private void enable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            TipoDocumento obj = new TipoDocumento();
            obj.setCodigo(cod);
            
            boolean res = daotipdoc.enable(obj);
            if (res) {
                response.sendRedirect("TipoDocumentoServlet?accion=habilita");
            } else {
                response.sendRedirect("TipoDocumentoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en enable TipoDocumentoServlet: " + ex.toString());
        }
    }

    // Método para deshabilitar un tipo de documento
    private void disable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            TipoDocumento obj = new TipoDocumento();
            obj.setCodigo(cod);
            
            boolean res = daotipdoc.disable(obj);
            if (res) {
                response.sendRedirect("TipoDocumentoServlet?accion=habilita");
            } else {
                response.sendRedirect("TipoDocumentoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en disable TipoDocumentoServlet: " + ex.toString());
        }
    }
}