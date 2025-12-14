package pe.com.ciberelectrik.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pe.com.ciberelectrik.dao.DistritoDAO;
import pe.com.ciberelectrik.dao.EmpleadoDAO;
import pe.com.ciberelectrik.dao.RolDAO;
import pe.com.ciberelectrik.dao.SexoDAO;
import pe.com.ciberelectrik.dao.TipoDocumentoDAO;
import pe.com.ciberelectrik.dao.impl.DistritoDAOImpl;
import pe.com.ciberelectrik.dao.impl.EmpleadoDAOImpl;
import pe.com.ciberelectrik.dao.impl.RolDAOImpl;
import pe.com.ciberelectrik.dao.impl.SexoDAOImpl;
import pe.com.ciberelectrik.dao.impl.TipoDocumentoDAOImpl;
import pe.com.ciberelectrik.modelo.Distrito;
import pe.com.ciberelectrik.modelo.Empleado;
import pe.com.ciberelectrik.modelo.Rol;
import pe.com.ciberelectrik.modelo.Sexo;
import pe.com.ciberelectrik.modelo.TipoDocumento;
import pe.com.ciberelectrik.util.StringManager;

@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {

    // Declaramos los DAOs
    private EmpleadoDAO daoemp;
    private RolDAO daorol;
    private SexoDAO daosex;
    private DistritoDAO daodis;
    private TipoDocumentoDAO daotipdoc;
    
    // Declaramos variables para los atributos del Empleado
    private int cod = 0;
    private String nombres = "";
    private String apellidos = "";
    private String numeroDocumento = "";
    private String telefono = "";
    private String correo = "";
    private boolean est = false;
    private int codRol = 0;
    private int codSexo = 0;
    private int codDistrito = 0;
    private int codTipoDocumento = 0;

    @Override
    public void init() throws ServletException {
        super.init();
        // Instanciamos los DAOs
        daoemp = new EmpleadoDAOImpl();
        daorol = new RolDAOImpl();
        daosex = new SexoDAOImpl();
        daodis = new DistritoDAOImpl();
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
                    cargarCombos(request);
                    request.getRequestDispatcher("/empleado/registrarempleado.jsp").forward(request, response);
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
                    request.getRequestDispatcher("mainmenu.jsp").forward(request, response);
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
            System.out.println("Error en doGet EmpleadoServlet: " + ex.toString());
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
            System.out.println("Error en doPost EmpleadoServlet: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestión de Empleados";
    }

    // Método auxiliar para cargar combos (reutilizable)
    private void cargarCombos(HttpServletRequest request) {
        List<Rol> roles = daorol.findAllCustom();
        List<Sexo> sexos = daosex.findAllCustom();
        List<Distrito> distritos = daodis.findAllCustom();
        List<TipoDocumento> tiposDocumento = daotipdoc.findAllCustom();
        
        request.setAttribute("roles", roles);
        request.setAttribute("sexos", sexos);
        request.setAttribute("distritos", distritos);
        request.setAttribute("tiposDocumento", tiposDocumento);
    }

    // Método para listar empleados habilitados (findAllCustom)
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Empleado> empleados = daoemp.findAllCustom();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/empleado/listarempleado.jsp").forward(request, response);
    }

    // Método para listar todos los empleados (incluyendo deshabilitados)
    private void findAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Empleado> empleados = daoemp.findAll();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/empleado/habilitarempleado.jsp").forward(request, response);
    }

    // Método para buscar un empleado por ID
    private void find(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado obj = daoemp.findById(id);
        
        cargarCombos(request);
        request.setAttribute("empleado", obj);
        request.getRequestDispatcher("/empleado/actualizarempleado.jsp").forward(request, response);
    }

    // Método para registrar un nuevo empleado
    private void add(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            // Capturamos valores del formulario
            nombres = StringManager.convertUTF8(request.getParameter("txtNombres"));
            apellidos = StringManager.convertUTF8(request.getParameter("txtApellidos"));
            numeroDocumento = StringManager.convertUTF8(request.getParameter("txtNumeroDocumento"));
            telefono = StringManager.convertUTF8(request.getParameter("txtTelefono"));
            correo = StringManager.convertUTF8(request.getParameter("txtCorreo"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            codRol = Integer.parseInt(request.getParameter("cboRol"));
            codSexo = Integer.parseInt(request.getParameter("cboSexo"));
            codDistrito = Integer.parseInt(request.getParameter("cboDistrito"));
            codTipoDocumento = Integer.parseInt(request.getParameter("cboTipoDocumento"));
            
            // Creamos los objetos relacionados
            Rol objrol = new Rol();
            Sexo objsex = new Sexo();
            Distrito objdis = new Distrito();
            TipoDocumento objtipdoc = new TipoDocumento();
            
            objrol.setCodigo(codRol);
            objsex.setCodigo(codSexo);
            objdis.setCodigo(codDistrito);
            objtipdoc.setCodigo(codTipoDocumento);
            
            // Creamos y configuramos el objeto Empleado
            Empleado obj = new Empleado();
            obj.setNombres(nombres);
            obj.setApellidos(apellidos);
            obj.setNumeroDocumento(numeroDocumento);
            obj.setTelefono(telefono);
            obj.setCorreo(correo);
            obj.setEstado(est);
            obj.setRol(objrol);
            obj.setSexo(objsex);
            obj.setDistrito(objdis);
            obj.setTipoDocumento(objtipdoc);
            
            // Registramos el empleado
            boolean res = daoemp.add(obj);
            if (res) {
                response.sendRedirect("EmpleadoServlet");
            } else {
                response.sendRedirect("EmpleadoServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println("Error en add EmpleadoServlet: " + ex.toString());
        }
    }

    // Método para actualizar un empleado existente
    private void update(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            // Capturamos valores del formulario
            cod = Integer.parseInt(request.getParameter("txtCod"));
            nombres = StringManager.convertUTF8(request.getParameter("txtNombres"));
            apellidos = StringManager.convertUTF8(request.getParameter("txtApellidos"));
            numeroDocumento = StringManager.convertUTF8(request.getParameter("txtNumeroDocumento"));
            telefono = StringManager.convertUTF8(request.getParameter("txtTelefono"));
            correo = StringManager.convertUTF8(request.getParameter("txtCorreo"));
            est = Boolean.parseBoolean(request.getParameter("chkEst"));
            codRol = Integer.parseInt(request.getParameter("cboRol"));
            codSexo = Integer.parseInt(request.getParameter("cboSexo"));
            codDistrito = Integer.parseInt(request.getParameter("cboDistrito"));
            codTipoDocumento = Integer.parseInt(request.getParameter("cboTipoDocumento"));
            
            // Creamos los objetos relacionados
            Rol objrol = new Rol();
            Sexo objsex = new Sexo();
            Distrito objdis = new Distrito();
            TipoDocumento objtipdoc = new TipoDocumento();
            
            objrol.setCodigo(codRol);
            objsex.setCodigo(codSexo);
            objdis.setCodigo(codDistrito);
            objtipdoc.setCodigo(codTipoDocumento);
            
            // Creamos y configuramos el objeto Empleado
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            obj.setNombres(nombres);
            obj.setApellidos(apellidos);
            obj.setNumeroDocumento(numeroDocumento);
            obj.setTelefono(telefono);
            obj.setCorreo(correo);
            obj.setEstado(est);
            obj.setRol(objrol);
            obj.setSexo(objsex);
            obj.setDistrito(objdis);
            obj.setTipoDocumento(objtipdoc);
            
            // Actualizamos el empleado
            boolean res = daoemp.update(obj);
            if (res) {
                response.sendRedirect("EmpleadoServlet");
            } else {
                response.sendRedirect("EmpleadoServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println("Error en update EmpleadoServlet: " + ex.toString());
        }
    }

    // Método para eliminar (deshabilitar) un empleado
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            
            boolean res = daoemp.delete(obj);
            if (res) {
                response.sendRedirect("EmpleadoServlet");
            } else {
                response.sendRedirect("EmpleadoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en delete EmpleadoServlet: " + ex.toString());
        }
    }

    // Método para habilitar un empleado
    private void enable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            
            boolean res = daoemp.enable(obj);
            if (res) {
                response.sendRedirect("EmpleadoServlet?accion=habilita");
            } else {
                response.sendRedirect("EmpleadoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en enable EmpleadoServlet: " + ex.toString());
        }
    }

    // Método para deshabilitar un empleado
    private void disable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Empleado obj = new Empleado();
            obj.setCodigo(cod);
            
            boolean res = daoemp.disable(obj);
            if (res) {
                response.sendRedirect("EmpleadoServlet?accion=habilita");
            } else {
                response.sendRedirect("EmpleadoServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en disable EmpleadoServlet: " + ex.toString());
        }
    }
}