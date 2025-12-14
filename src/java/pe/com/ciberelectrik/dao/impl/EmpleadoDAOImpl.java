package pe.com.ciberelectrik.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.ciberelectrik.bd.Conexion;
import pe.com.ciberelectrik.dao.EmpleadoDAO;
import pe.com.ciberelectrik.modelo.Empleado;
import pe.com.ciberelectrik.modelo.Rol;
import pe.com.ciberelectrik.modelo.Sexo;
import pe.com.ciberelectrik.modelo.Distrito;
import pe.com.ciberelectrik.modelo.TipoDocumento;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public EmpleadoDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT e.codemp, e.nomemp, e.apepemp, e.apememp, e.docemp, e.fecemp, " +
                     "e.diremp, e.telemp, e.celemp, e.coremp, e.usuemp, e.claemp, e.estemp, " +
                     "r.codrol, r.nomrol, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM empleado e " +
                     "INNER JOIN rol r ON e.codrol=r.codrol " +
                     "INNER JOIN sexo s ON e.codsex=s.codsex " +
                     "INNER JOIN distrito d ON e.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON e.codtipd=t.codtipd";

        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Empleado obj = new Empleado();
                obj.setCodigo(rs.getInt("codemp"));
                obj.setNombres(rs.getString("nomemp"));
                obj.setApellidos(rs.getString("apepemp") + " " + rs.getString("apememp"));
                obj.setNumeroDocumento(rs.getString("docemp"));
                obj.setTelefono(rs.getString("telemp"));
                obj.setCorreo(rs.getString("coremp"));
                obj.setEstado(rs.getBoolean("estemp"));
                
                // Crear objeto Rol
                Rol rol = new Rol();
                rol.setCodigo(rs.getInt("codrol"));
                rol.setNombre(rs.getString("nomrol"));
                obj.setRol(rol);
                
                // Crear objeto Sexo
                Sexo sexo = new Sexo();
                sexo.setCodigo(rs.getInt("codsex"));
                sexo.setNombre(rs.getString("nomsex"));
                obj.setSexo(sexo);
                
                // Crear objeto Distrito
                Distrito distrito = new Distrito();
                distrito.setCodigo(rs.getInt("coddis"));
                distrito.setNombre(rs.getString("nomdis"));
                obj.setDistrito(distrito);
                
                // Crear objeto TipoDocumento
                TipoDocumento tipoDoc = new TipoDocumento();
                tipoDoc.setCodigo(rs.getInt("codtipd"));
                tipoDoc.setNombre(rs.getString("nomtipd"));
                obj.setTipoDocumento(tipoDoc);
                
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public List<Empleado> findAllCustom() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT e.codemp, e.nomemp, e.apepemp, e.apememp, e.docemp, e.fecemp, " +
                     "e.diremp, e.telemp, e.celemp, e.coremp, e.usuemp, e.claemp, e.estemp, " +
                     "r.codrol, r.nomrol, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM empleado e " +
                     "INNER JOIN rol r ON e.codrol=r.codrol " +
                     "INNER JOIN sexo s ON e.codsex=s.codsex " +
                     "INNER JOIN distrito d ON e.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON e.codtipd=t.codtipd " +
                     "WHERE e.estemp=1";

        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Empleado obj = new Empleado();
                obj.setCodigo(rs.getInt("codemp"));
                obj.setNombres(rs.getString("nomemp"));
                obj.setApellidos(rs.getString("apepemp") + " " + rs.getString("apememp"));
                obj.setNumeroDocumento(rs.getString("docemp"));
                obj.setTelefono(rs.getString("telemp"));
                obj.setCorreo(rs.getString("coremp"));
                obj.setEstado(rs.getBoolean("estemp"));
                
                Rol rol = new Rol();
                rol.setCodigo(rs.getInt("codrol"));
                rol.setNombre(rs.getString("nomrol"));
                obj.setRol(rol);
                
                Sexo sexo = new Sexo();
                sexo.setCodigo(rs.getInt("codsex"));
                sexo.setNombre(rs.getString("nomsex"));
                obj.setSexo(sexo);
                
                Distrito distrito = new Distrito();
                distrito.setCodigo(rs.getInt("coddis"));
                distrito.setNombre(rs.getString("nomdis"));
                obj.setDistrito(distrito);
                
                TipoDocumento tipoDoc = new TipoDocumento();
                tipoDoc.setCodigo(rs.getInt("codtipd"));
                tipoDoc.setNombre(rs.getString("nomtipd"));
                obj.setTipoDocumento(tipoDoc);
                
                lista.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return lista;
    }

    @Override
    public Empleado findById(int id) {
        Empleado obj = new Empleado();
        String sql = "SELECT e.codemp, e.nomemp, e.apepemp, e.apememp, e.docemp, e.fecemp, " +
                     "e.diremp, e.telemp, e.celemp, e.coremp, e.usuemp, e.claemp, e.estemp, " +
                     "r.codrol, r.nomrol, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM empleado e " +
                     "INNER JOIN rol r ON e.codrol=r.codrol " +
                     "INNER JOIN sexo s ON e.codsex=s.codsex " +
                     "INNER JOIN distrito d ON e.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON e.codtipd=t.codtipd " +
                     "WHERE e.codemp=?";

        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                obj.setCodigo(rs.getInt("codemp"));
                obj.setNombres(rs.getString("nomemp"));
                obj.setApellidos(rs.getString("apepemp") + " " + rs.getString("apememp"));
                obj.setNumeroDocumento(rs.getString("docemp"));
                obj.setTelefono(rs.getString("telemp"));
                obj.setCorreo(rs.getString("coremp"));
                obj.setEstado(rs.getBoolean("estemp"));
                
                Rol rol = new Rol();
                rol.setCodigo(rs.getInt("codrol"));
                rol.setNombre(rs.getString("nomrol"));
                obj.setRol(rol);
                
                Sexo sexo = new Sexo();
                sexo.setCodigo(rs.getInt("codsex"));
                sexo.setNombre(rs.getString("nomsex"));
                obj.setSexo(sexo);
                
                Distrito distrito = new Distrito();
                distrito.setCodigo(rs.getInt("coddis"));
                distrito.setNombre(rs.getString("nomdis"));
                obj.setDistrito(distrito);
                
                TipoDocumento tipoDoc = new TipoDocumento();
                tipoDoc.setCodigo(rs.getInt("codtipd"));
                tipoDoc.setNombre(rs.getString("nomtipd"));
                obj.setTipoDocumento(tipoDoc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public Empleado findByDocumento(String documento) {
        Empleado obj = new Empleado();
        String sql = "SELECT e.codemp, e.nomemp, e.apepemp, e.apememp, e.docemp, e.fecemp, " +
                     "e.diremp, e.telemp, e.celemp, e.coremp, e.usuemp, e.claemp, e.estemp, " +
                     "r.codrol, r.nomrol, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM empleado e " +
                     "INNER JOIN rol r ON e.codrol=r.codrol " +
                     "INNER JOIN sexo s ON e.codsex=s.codsex " +
                     "INNER JOIN distrito d ON e.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON e.codtipd=t.codtipd " +
                     "WHERE e.docemp=?";

        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, documento);
            rs = pst.executeQuery();
            while (rs.next()) {
                obj.setCodigo(rs.getInt("codemp"));
                obj.setNombres(rs.getString("nomemp"));
                obj.setApellidos(rs.getString("apepemp") + " " + rs.getString("apememp"));
                obj.setNumeroDocumento(rs.getString("docemp"));
                obj.setTelefono(rs.getString("telemp"));
                obj.setCorreo(rs.getString("coremp"));
                obj.setEstado(rs.getBoolean("estemp"));
                
                Rol rol = new Rol();
                rol.setCodigo(rs.getInt("codrol"));
                rol.setNombre(rs.getString("nomrol"));
                obj.setRol(rol);
                
                Sexo sexo = new Sexo();
                sexo.setCodigo(rs.getInt("codsex"));
                sexo.setNombre(rs.getString("nomsex"));
                obj.setSexo(sexo);
                
                Distrito distrito = new Distrito();
                distrito.setCodigo(rs.getInt("coddis"));
                distrito.setNombre(rs.getString("nomdis"));
                obj.setDistrito(distrito);
                
                TipoDocumento tipoDoc = new TipoDocumento();
                tipoDoc.setCodigo(rs.getInt("codtipd"));
                tipoDoc.setNombre(rs.getString("nomtipd"));
                obj.setTipoDocumento(tipoDoc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public Empleado login(String usuario, String clave) {
        Empleado obj = new Empleado();
        String sql = "SELECT e.codemp, e.nomemp, e.apepemp, e.apememp, e.docemp, e.fecemp, " +
                     "e.diremp, e.telemp, e.celemp, e.coremp, e.usuemp, e.claemp, e.estemp, " +
                     "r.codrol, r.nomrol, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM empleado e " +
                     "INNER JOIN rol r ON e.codrol=r.codrol " +
                     "INNER JOIN sexo s ON e.codsex=s.codsex " +
                     "INNER JOIN distrito d ON e.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON e.codtipd=t.codtipd " +
                     "WHERE e.usuemp=? AND e.claemp=? AND e.estemp=1";

        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, clave);
            rs = pst.executeQuery();
            while (rs.next()) {
                obj.setCodigo(rs.getInt("codemp"));
                obj.setNombres(rs.getString("nomemp"));
                obj.setApellidos(rs.getString("apepemp") + " " + rs.getString("apememp"));
                obj.setNumeroDocumento(rs.getString("docemp"));
                obj.setTelefono(rs.getString("telemp"));
                obj.setCorreo(rs.getString("coremp"));
                obj.setEstado(rs.getBoolean("estemp"));
                
                Rol rol = new Rol();
                rol.setCodigo(rs.getInt("codrol"));
                rol.setNombre(rs.getString("nomrol"));
                obj.setRol(rol);
                
                Sexo sexo = new Sexo();
                sexo.setCodigo(rs.getInt("codsex"));
                sexo.setNombre(rs.getString("nomsex"));
                obj.setSexo(sexo);
                
                Distrito distrito = new Distrito();
                distrito.setCodigo(rs.getInt("coddis"));
                distrito.setNombre(rs.getString("nomdis"));
                obj.setDistrito(distrito);
                
                TipoDocumento tipoDoc = new TipoDocumento();
                tipoDoc.setCodigo(rs.getInt("codtipd"));
                tipoDoc.setNombre(rs.getString("nomtipd"));
                obj.setTipoDocumento(tipoDoc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Empleado obj) {
        boolean resultado = false;
        String sql = "INSERT INTO empleado(nomemp,apepemp,apememp,docemp,fecemp,diremp,telemp,celemp,coremp,usuemp,claemp,estemp,coddis,codrol,codsex,codtipd) " +
                     "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            
            String[] apellidos = obj.getApellidos().split(" ", 2);
            String apellidoPaterno = apellidos.length > 0 ? apellidos[0] : "";
            String apellidoMaterno = apellidos.length > 1 ? apellidos[1] : "";
            
            pst.setString(1, obj.getNombres());
            pst.setString(2, apellidoPaterno);
            pst.setString(3, apellidoMaterno);
            pst.setString(4, obj.getNumeroDocumento());
            pst.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            pst.setString(6, ""); // dirección
            pst.setString(7, obj.getTelefono());
            pst.setString(8, ""); // celular
            pst.setString(9, obj.getCorreo());
            pst.setString(10, obj.getNumeroDocumento()); // usuario por defecto
            pst.setString(11, "123456"); // clave por defecto
            pst.setBoolean(12, obj.isEstado());
            pst.setInt(13, obj.getDistrito().getCodigo());
            pst.setInt(14, obj.getRol().getCodigo());
            pst.setInt(15, obj.getSexo().getCodigo());
            pst.setInt(16, obj.getTipoDocumento().getCodigo());
            
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean update(Empleado obj) {
        boolean resultado = false;
        String sql = "UPDATE empleado SET nomemp=?, apepemp=?, apememp=?, docemp=?, telemp=?, coremp=?, estemp=?, coddis=?, codrol=?, codsex=?, codtipd=? " +
                     "WHERE codemp=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            
            String[] apellidos = obj.getApellidos().split(" ", 2);
            String apellidoPaterno = apellidos.length > 0 ? apellidos[0] : "";
            String apellidoMaterno = apellidos.length > 1 ? apellidos[1] : "";
            
            pst.setString(1, obj.getNombres());
            pst.setString(2, apellidoPaterno);
            pst.setString(3, apellidoMaterno);
            pst.setString(4, obj.getNumeroDocumento());
            pst.setString(5, obj.getTelefono());
            pst.setString(6, obj.getCorreo());
            pst.setBoolean(7, obj.isEstado());
            pst.setInt(8, obj.getDistrito().getCodigo());
            pst.setInt(9, obj.getRol().getCodigo());
            pst.setInt(10, obj.getSexo().getCodigo());
            pst.setInt(11, obj.getTipoDocumento().getCodigo());
            pst.setInt(12, obj.getCodigo());
            
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean delete(Empleado obj) {
        boolean resultado = false;
        String sql = "UPDATE empleado SET estemp=0 WHERE codemp=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean enable(Empleado obj) {
        boolean resultado = false;
        String sql = "UPDATE empleado SET estemp=1 WHERE codemp=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }

    @Override
    public boolean disable(Empleado obj) {
        boolean resultado = false;
        String sql = "UPDATE empleado SET estemp=0 WHERE codemp=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, obj.getCodigo());
            int res = pst.executeUpdate();
            resultado = res == 1;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return resultado;
    }
}