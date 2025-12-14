package pe.com.ciberelectrik.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pe.com.ciberelectrik.dao.CategoriaDAO;
import pe.com.ciberelectrik.dao.impl.CategoriaDAOImpl;
import pe.com.ciberelectrik.modelo.Categoria;
import pe.com.ciberelectrik.util.StringManager;

// name = "CategoriaServlet" -> define el nombre del Servlet
// urlPatterns = {"/CategoriaServlet"} -> define la ruta de acceso
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
public class CategoriaServlet extends HttpServlet {

    //declaramos el DAO
    private CategoriaDAO daocat;
    //declaramos variables
    private int cod = 0;
    private String nom = "";
    private boolean est = false;

    //generamos el metodo init
    @Override
    public void init() throws ServletException {
        super.init();
        //instanciamos el DAO
        daocat = new CategoriaDAOImpl();
    }

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CategoriaServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CategoriaServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
    //GET -> se utiliza para rutas y algunas acciones
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //controlamos errores
        try {
            //creamos una variable para poder capturar la accion
            String accion = request.getParameter("accion");
            //evaluamos la accion
            if (accion == null) {
                accion = "listar";
            }
            //evaluamos el valor de la accion
            switch (accion) {
                //rutas
                case "registro":
                    request.getRequestDispatcher("/categoria/registrarcategoria.jsp").forward(request, response);
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
                //acciones
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
                    //llamamos al metodo para listar
                    findAllCustom(request, response);
                    break;
            }
        } catch (ServletException | IOException ex) {
            System.out.println("Error: " + ex.toString());
        }

    }

    //POST -> se utiliza para ejecutar acciones
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "registrar":
                    //llamamos al metodo para registrar
                    request.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=UTF-8");
                    add(request, response);
                    break;
                case "actualizar":
                    //llamamos al metodo para actualizar
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
            System.out.println("Error: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //creamos un procedimiento para listar
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //creamos una llista que almacena las categorias
        List<Categoria> categorias = daocat.findAllCustom();
        //enviamos las categorias como un variable
        request.setAttribute("categorias", categorias);
        //enviamos los valores al formularios
        request.getRequestDispatcher("/categoria/listarcategoria.jsp").forward(request, response);
    }

    //creamos un procedmiento para mostrar todo
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //creamos una llista que almacena las categorias
        List<Categoria> categorias = daocat.findAll();
        //enviamos las categorias como un variable
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/categoria/habilitarcategoria.jsp").forward(request, response);
    }

    //creamos un procedmiento para mostrar todo
    private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //capturamos el codigo de la ruta
        int id = Integer.parseInt(request.getParameter("id"));
        //realizamos la busqueda
        Categoria obj = daocat.findById(id);
        //enviamos los datos por una variable
        request.setAttribute("categorias", obj);
        request.getRequestDispatcher("/categoria/actualizarcategoria.jsp").forward(request, response);
    }

    //creamos un proceimiento para registrar
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //hay que codificar en formato UTF8
            request.setCharacterEncoding("UTF-8");
            //especificamos el tipo de contenido
            response.setContentType("text/html; charset=UTF-8");
            //capturamos valores
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            //creamos un objeto de la clase
            Categoria obj = new Categoria();
            //le asignamos los valores al objeto
            obj.setNombre(nom);
            obj.setEstado(est);
            //registramos la categoria
            boolean res = daocat.add(obj);
            if (res == true) {
                response.sendRedirect("CategoriaServlet");
            } else {
                response.sendRedirect("CategoriaServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    //creamos un proceimiento para actualizar
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //hay que codificar en formato UTF8
            request.setCharacterEncoding("UTF-8");
            //especificamos el tipo de contenido
            response.setContentType("text/html; charset=UTF-8");
            //capturamos valores
            cod = Integer.parseInt(request.getParameter("txtCod"));
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            //creamos un objeto de la clase
            Categoria obj = new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            obj.setNombre(nom);
            obj.setEstado(est);
            //registramos la categoria
            boolean res = daocat.update(obj);
            if (res == true) {
                response.sendRedirect("CategoriaServlet");
            } else {
                response.sendRedirect("CategoriaServlet?accion=actualiza&id=" + obj.getCodigo());
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    //creamos un proceimiento para eliminar
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //hay que codificar en formato UTF8
            request.setCharacterEncoding("UTF-8");
            //especificamos el tipo de contenido
            response.setContentType("text/html; charset=UTF-8");
            //capturamos valores
            cod = Integer.parseInt(request.getParameter("id"));
            Categoria obj = new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            //registramos la categoria
            boolean res = daocat.delete(obj);
            if (res == true) {
                response.sendRedirect("CategoriaServlet");
            } else {
                response.sendRedirect("CategoriaServlet");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    //creamos un proceimiento para habilitar
    private void enable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //hay que codificar en formato UTF8
            request.setCharacterEncoding("UTF-8");
            //especificamos el tipo de contenido
            response.setContentType("text/html; charset=UTF-8");
            //capturamos valores
            cod = Integer.parseInt(request.getParameter("id"));
            Categoria obj = new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            //registramos la categoria
            boolean res = daocat.enable(obj);
            if (res == true) {
                response.sendRedirect("CategoriaServlet?accion=habilita");
            } else {
                response.sendRedirect("CategoriaServlet");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    //creamos un proceimiento para deshabilitar
    private void disable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //hay que codificar en formato UTF8
            request.setCharacterEncoding("UTF-8");
            //especificamos el tipo de contenido
            response.setContentType("text/html; charset=UTF-8");
            //capturamos valores
            cod = Integer.parseInt(request.getParameter("id"));
            Categoria obj = new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            //registramos la categoria
            boolean res = daocat.disable(obj);
            if (res == true) {
                response.sendRedirect("CategoriaServlet?accion=habilita");
            } else {
                response.sendRedirect("CategoriaServlet");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}


