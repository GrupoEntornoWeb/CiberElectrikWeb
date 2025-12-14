package pe.com.ciberelectrik.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.com.ciberelectrik.dao.CategoriaDAO;
import pe.com.ciberelectrik.dao.MarcaDAO;
import pe.com.ciberelectrik.dao.ProductoDAO;
import pe.com.ciberelectrik.dao.impl.CategoriaDAOImpl;
import pe.com.ciberelectrik.dao.impl.MarcaDAOImpl;
import pe.com.ciberelectrik.dao.impl.ProductoDAOImpl;
import pe.com.ciberelectrik.modelo.Categoria;
import pe.com.ciberelectrik.modelo.Marca;
import pe.com.ciberelectrik.modelo.Producto;
import pe.com.ciberelectrik.util.StringManager;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    //declaramos el DAO
    private ProductoDAO daopro;
    private MarcaDAO daomar;
    private CategoriaDAO daocat;
    //declarando variables
    private int cod = 0, can = 0, codcat = 0, codmar = 0;
    private String nom = "", des = "";
    private double pre = 0;
    private Date fec;
    private boolean est = false;

    @Override
    public void init() throws ServletException {
        super.init();
        //instanciamos el DAO
        daopro = new ProductoDAOImpl();
        daomar = new MarcaDAOImpl();
        daocat = new CategoriaDAOImpl();
    }

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ProductoServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ProductoServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("accion");
            if (accion == null) {
                accion = "listar";
            }
            switch (accion) {
                //rutas
                case "registro":
                    List<Marca> marcas = daomar.findAllCustom();
                    List<Categoria> categorias = daocat.findAllCustom();
                    request.setAttribute("marcas", marcas);
                    request.setAttribute("categorias", categorias);
                    request.getRequestDispatcher("/producto/registrarproducto.jsp").forward(request, response);
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
        } catch (ParseException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
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
        } catch (ServletException | IOException | ParseException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //creamos un procedimiento para listar
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = daopro.findAllCustom();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("/producto/listarproducto.jsp").forward(request, response);
    }

    //creamos un procedmiento para mostrar todo
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = daopro.findAll();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("/producto/habilitarproducto.jsp").forward(request, response);
    }
    //creamos un procedmiento para mostrar todo

    private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto obj = daopro.findById(id);
        List<Marca> marcas = daomar.findAllCustom();
        List<Categoria> categorias = daocat.findAllCustom();
        request.setAttribute("marcas", marcas);
        request.setAttribute("categorias", categorias);
        request.setAttribute("productos", obj);
        request.getRequestDispatcher("/producto/actualizarproducto.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            des = StringManager.convertUTF8(request.getParameter("txtDes"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            pre = Double.parseDouble(request.getParameter("txtPre"));
            can = Integer.parseInt(request.getParameter("txtCan"));
            //para la fecha
            String fecha = request.getParameter("txtFec");
            //le damos formato a la fecha
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            //asignamos el valor a la variables
            fec = formato.parse(fecha);
            codmar = Integer.parseInt(request.getParameter("cboMarca"));
            codcat = Integer.parseInt(request.getParameter("cboCategoria"));
            //creamos un objeto de la clase
            Producto obj = new Producto();
            Marca objmar = new Marca();
            Categoria objcat = new Categoria();
            //le asignamos los valores al objeto
            obj.setNombre(nom);
            obj.setDescripcion(des);
            obj.setPrecio(pre);
            obj.setCantidad(can);
            obj.setFechaingreso(fec);
            obj.setEstado(est);

            //para las claves foraneas
            objmar.setCodigo(codmar);
            obj.setMarca(objmar);

            objcat.setCodigo(codcat);
            obj.setCategoria(objcat);

            //registramos la categoria
            boolean res = daopro.add(obj);
            if (res == true) {
                response.sendRedirect("ProductoServlet");
            } else {
                response.sendRedirect("ProductoServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            cod = Integer.parseInt(request.getParameter("txtCod"));
            nom = StringManager.convertUTF8(request.getParameter("txtNom"));
            des = StringManager.convertUTF8(request.getParameter("txtDes"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            pre = Double.parseDouble(request.getParameter("txtPre"));
            can = Integer.parseInt(request.getParameter("txtCan"));
            //para la fecha
            String fecha = request.getParameter("txtFec");
            //le damos formato a la fecha
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            //asignamos el valor a la variables
            fec = formato.parse(fecha);
            codmar = Integer.parseInt(request.getParameter("cboMarca"));
            codcat = Integer.parseInt(request.getParameter("cboCategoria"));
            //creamos un objeto de la clase
            Producto obj = new Producto();
            Marca objmar = new Marca();
            Categoria objcat = new Categoria();
            //le asignamos los valores al objeto
            obj.setCodigo(cod);
            obj.setNombre(nom);
            obj.setDescripcion(des);
            obj.setPrecio(pre);
            obj.setCantidad(can);
            obj.setFechaingreso(fec);
            obj.setEstado(est);

            //para las claves foraneas
            objmar.setCodigo(codmar);
            obj.setMarca(objmar);

            objcat.setCodigo(codcat);
            obj.setCategoria(objcat);

            //registramos la categoria
            boolean res = daopro.update(obj);
            if (res == true) {
                response.sendRedirect("ProductoServlet");
            } else {
                response.sendRedirect("ProductoServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));

            //creamos un objeto de la clase
            Producto obj = new Producto();

            //le asignamos los valores al objeto
            obj.setCodigo(cod);

            //registramos la categoria
            boolean res = daopro.delete(obj);
            if (res == true) {
                response.sendRedirect("ProductoServlet");
            } else {
                response.sendRedirect("ProductoServlet?accion=eliminar&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private void enable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));

            //creamos un objeto de la clase
            Producto obj = new Producto();

            //le asignamos los valores al objeto
            obj.setCodigo(cod);

            //registramos la categoria
            boolean res = daopro.enable(obj);
            if (res == true) {
                response.sendRedirect("ProductoServlet?accion=habilita");
            } else {
                response.sendRedirect("ProductoServlet?accion=habilita");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
        private void disable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));

            //creamos un objeto de la clase
            Producto obj = new Producto();

            //le asignamos los valores al objeto
            obj.setCodigo(cod);

            //registramos la categoria
            boolean res = daopro.disable(obj);
            if (res == true) {
                response.sendRedirect("ProductoServlet?accion=habilita");
            } else {
                response.sendRedirect("ProductoServlet?accion=habilita");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

}
