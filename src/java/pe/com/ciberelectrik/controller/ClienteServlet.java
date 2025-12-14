package pe.com.ciberelectrik.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import pe.com.ciberelectrik.dao.ClienteDAO;
import pe.com.ciberelectrik.dao.DistritoDAO;
import pe.com.ciberelectrik.dao.SexoDAO;
import pe.com.ciberelectrik.dao.TipoDocumentoDAO;
import pe.com.ciberelectrik.dao.impl.ClienteDAOImpl;
import pe.com.ciberelectrik.dao.impl.DistritoDAOImpl;
import pe.com.ciberelectrik.dao.impl.SexoDAOImpl;
import pe.com.ciberelectrik.dao.impl.TipoDocumentoDAOImpl;
import pe.com.ciberelectrik.modelo.Cliente;
import pe.com.ciberelectrik.modelo.Distrito;
import pe.com.ciberelectrik.modelo.Sexo;
import pe.com.ciberelectrik.modelo.TipoDocumento;
import pe.com.ciberelectrik.util.StringManager;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

    // Declaramos los DAOs
    private ClienteDAO daocli;
    private SexoDAO daosex;
    private DistritoDAO daodis;
    private TipoDocumentoDAO daotipdoc;
    
    // Declaramos variables para los atributos del Cliente
    private int cod = 0;
    private String nombres = "";
    private String apellidos = "";
    private String numeroDocumento = "";
    private String telefono = "";
    private String correo = "";
    private boolean est = false;
    private int codSexo = 0;
    private int codDistrito = 0;
    private int codTipoDocumento = 0;

    @Override
    public void init() throws ServletException {
        super.init();
        // Instanciamos los DAOs
        daocli = new ClienteDAOImpl();
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
                    List<Sexo> sexos = daosex.findAllCustom();
                    List<Distrito> distritos = daodis.findAllCustom();
                    List<TipoDocumento> tiposDocumento = daotipdoc.findAllCustom();
                    request.setAttribute("sexos", sexos);
                    request.setAttribute("distritos", distritos);
                    request.setAttribute("tiposDocumento", tiposDocumento);
                    request.getRequestDispatcher("/cliente/registrarcliente.jsp").forward(request, response);
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
            System.out.println("Error en doGet ClienteServlet: " + ex.toString());
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
            System.out.println("Error en doPost ClienteServlet: " + ex.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestión de Clientes";
    }

    // Método para listar clientes habilitados (findAllCustom)
    private void findAllCustom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Cliente> clientes = daocli.findAllCustom();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/cliente/listarcliente.jsp").forward(request, response);
    }

    // Método para listar todos los clientes (incluyendo deshabilitados)
    private void findAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Cliente> clientes = daocli.findAll();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/cliente/habilitarcliente.jsp").forward(request, response);
    }

    // Método para buscar un cliente por ID
    private void find(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente obj = daocli.findById(id);
        
        // Cargamos las listas para los combos
        List<Sexo> sexos = daosex.findAllCustom();
        List<Distrito> distritos = daodis.findAllCustom();
        List<TipoDocumento> tiposDocumento = daotipdoc.findAllCustom();
        
        request.setAttribute("sexos", sexos);
        request.setAttribute("distritos", distritos);
        request.setAttribute("tiposDocumento", tiposDocumento);
        request.setAttribute("cliente", obj);
        request.getRequestDispatcher("/cliente/actualizarcliente.jsp").forward(request, response);
    }

    // Método para registrar un nuevo cliente
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
            codSexo = Integer.parseInt(request.getParameter("cboSexo"));
            codDistrito = Integer.parseInt(request.getParameter("cboDistrito"));
            codTipoDocumento = Integer.parseInt(request.getParameter("cboTipoDocumento"));
            
            // Creamos los objetos relacionados
            Sexo objsex = new Sexo();
            Distrito objdis = new Distrito();
            TipoDocumento objtipdoc = new TipoDocumento();
            
            objsex.setCodigo(codSexo);
            objdis.setCodigo(codDistrito);
            objtipdoc.setCodigo(codTipoDocumento);
            
            // Creamos y configuramos el objeto Cliente
            Cliente obj = new Cliente();
            obj.setNombres(nombres);
            obj.setApellidos(apellidos);
            obj.setNumeroDocumento(numeroDocumento);
            obj.setTelefono(telefono);
            obj.setCorreo(correo);
            obj.setEstado(est);
            obj.setSexo(objsex);
            obj.setDistrito(objdis);
            obj.setTipoDocumento(objtipdoc);
            
            // Registramos el cliente
            boolean res = daocli.add(obj);
            if (res) {
                response.sendRedirect("ClienteServlet");
            } else {
                response.sendRedirect("ClienteServlet?accion=registro");
            }
        } catch (IOException ex) {
            System.out.println("Error en add ClienteServlet: " + ex.toString());
        }
    }

    // Método para actualizar un cliente existente
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
            codSexo = Integer.parseInt(request.getParameter("cboSexo"));
            codDistrito = Integer.parseInt(request.getParameter("cboDistrito"));
            codTipoDocumento = Integer.parseInt(request.getParameter("cboTipoDocumento"));
            
            // Creamos los objetos relacionados
            Sexo objsex = new Sexo();
            Distrito objdis = new Distrito();
            TipoDocumento objtipdoc = new TipoDocumento();
            
            objsex.setCodigo(codSexo);
            objdis.setCodigo(codDistrito);
            objtipdoc.setCodigo(codTipoDocumento);
            
            // Creamos y configuramos el objeto Cliente
            Cliente obj = new Cliente();
            obj.setCodigo(cod);
            obj.setNombres(nombres);
            obj.setApellidos(apellidos);
            obj.setNumeroDocumento(numeroDocumento);
            obj.setTelefono(telefono);
            obj.setCorreo(correo);
            obj.setEstado(est);
            obj.setSexo(objsex);
            obj.setDistrito(objdis);
            obj.setTipoDocumento(objtipdoc);
            
            // Actualizamos el cliente
            boolean res = daocli.update(obj);
            if (res) {
                response.sendRedirect("ClienteServlet");
            } else {
                response.sendRedirect("ClienteServlet?accion=actualiza&id=" + cod);
            }
        } catch (IOException ex) {
            System.out.println("Error en update ClienteServlet: " + ex.toString());
        }
    }

    // Método para eliminar (deshabilitar) un cliente
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Cliente obj = new Cliente();
            obj.setCodigo(cod);
            
            boolean res = daocli.delete(obj);
            if (res) {
                response.sendRedirect("ClienteServlet");
            } else {
                response.sendRedirect("ClienteServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en delete ClienteServlet: " + ex.toString());
        }
    }

    // Método para habilitar un cliente
    private void enable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Cliente obj = new Cliente();
            obj.setCodigo(cod);
            
            boolean res = daocli.enable(obj);
            if (res) {
                response.sendRedirect("ClienteServlet?accion=habilita");
            } else {
                response.sendRedirect("ClienteServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en enable ClienteServlet: " + ex.toString());
        }
    }

    // Método para deshabilitar un cliente
    private void disable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            cod = Integer.parseInt(request.getParameter("id"));
            
            Cliente obj = new Cliente();
            obj.setCodigo(cod);
            
            boolean res = daocli.disable(obj);
            if (res) {
                response.sendRedirect("ClienteServlet?accion=habilita");
            } else {
                response.sendRedirect("ClienteServlet");
            }
        } catch (IOException ex) {
            System.out.println("Error en disable ClienteServlet: " + ex.toString());
        }
    }
}