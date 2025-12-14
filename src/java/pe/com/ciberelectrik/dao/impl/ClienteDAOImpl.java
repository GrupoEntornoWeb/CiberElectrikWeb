package pe.com.ciberelectrik.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.ciberelectrik.bd.Conexion;
import pe.com.ciberelectrik.dao.ClienteDAO;
import pe.com.ciberelectrik.modelo.Cliente;
import pe.com.ciberelectrik.modelo.Sexo;
import pe.com.ciberelectrik.modelo.Distrito;
import pe.com.ciberelectrik.modelo.TipoDocumento;

public class ClienteDAOImpl implements ClienteDAO {

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ClienteDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT c.codcli, c.nomcli, c.apepcli, c.apemcli, c.doccli, c.feccli, " +
                     "c.dircli, c.telcli, c.celcli, c.corcli, c.estcli, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM cliente c " +
                     "INNER JOIN sexo s ON c.codsex=s.codsex " +
                     "INNER JOIN distrito d ON c.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON c.codtipd=t.codtipd";

        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setCodigo(rs.getInt("codcli"));
                obj.setNombres(rs.getString("nomcli"));
                obj.setApellidos(rs.getString("apepcli") + " " + rs.getString("apemcli"));
                obj.setNumeroDocumento(rs.getString("doccli"));
                obj.setTelefono(rs.getString("telcli"));
                obj.setCorreo(rs.getString("corcli"));
                obj.setEstado(rs.getBoolean("estcli"));
                
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
    public List<Cliente> findAllCustom() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT c.codcli, c.nomcli, c.apepcli, c.apemcli, c.doccli, c.feccli, " +
                     "c.dircli, c.telcli, c.celcli, c.corcli, c.estcli, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM cliente c " +
                     "INNER JOIN sexo s ON c.codsex=s.codsex " +
                     "INNER JOIN distrito d ON c.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON c.codtipd=t.codtipd " +
                     "WHERE c.estcli=1";

        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setCodigo(rs.getInt("codcli"));
                obj.setNombres(rs.getString("nomcli"));
                obj.setApellidos(rs.getString("apepcli") + " " + rs.getString("apemcli"));
                obj.setNumeroDocumento(rs.getString("doccli"));
                obj.setTelefono(rs.getString("telcli"));
                obj.setCorreo(rs.getString("corcli"));
                obj.setEstado(rs.getBoolean("estcli"));
                
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
    public Cliente findById(int id) {
        Cliente obj = new Cliente();
        String sql = "SELECT c.codcli, c.nomcli, c.apepcli, c.apemcli, c.doccli, c.feccli, " +
                     "c.dircli, c.telcli, c.celcli, c.corcli, c.estcli, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM cliente c " +
                     "INNER JOIN sexo s ON c.codsex=s.codsex " +
                     "INNER JOIN distrito d ON c.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON c.codtipd=t.codtipd " +
                     "WHERE c.codcli=?";

        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                obj.setCodigo(rs.getInt("codcli"));
                obj.setNombres(rs.getString("nomcli"));
                obj.setApellidos(rs.getString("apepcli") + " " + rs.getString("apemcli"));
                obj.setNumeroDocumento(rs.getString("doccli"));
                obj.setTelefono(rs.getString("telcli"));
                obj.setCorreo(rs.getString("corcli"));
                obj.setEstado(rs.getBoolean("estcli"));
                
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
    public Cliente findByDocumento(String documento) {
        Cliente obj = new Cliente();
        String sql = "SELECT c.codcli, c.nomcli, c.apepcli, c.apemcli, c.doccli, c.feccli, " +
                     "c.dircli, c.telcli, c.celcli, c.corcli, c.estcli, " +
                     "s.codsex, s.nomsex, " +
                     "d.coddis, d.nomdis, " +
                     "t.codtipd, t.nomtipd " +
                     "FROM cliente c " +
                     "INNER JOIN sexo s ON c.codsex=s.codsex " +
                     "INNER JOIN distrito d ON c.coddis=d.coddis " +
                     "INNER JOIN tipodocumento t ON c.codtipd=t.codtipd " +
                     "WHERE c.doccli=?";

        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, documento);
            rs = pst.executeQuery();
            while (rs.next()) {
                obj.setCodigo(rs.getInt("codcli"));
                obj.setNombres(rs.getString("nomcli"));
                obj.setApellidos(rs.getString("apepcli") + " " + rs.getString("apemcli"));
                obj.setNumeroDocumento(rs.getString("doccli"));
                obj.setTelefono(rs.getString("telcli"));
                obj.setCorreo(rs.getString("corcli"));
                obj.setEstado(rs.getBoolean("estcli"));
                
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
    public boolean add(Cliente obj) {
        boolean resultado = false;
        String sql = "INSERT INTO cliente(nomcli,apepcli,apemcli,doccli,feccli,dircli,telcli,celcli,corcli,estcli,coddis,codsex,codtipd) " +
                     "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            
            // Separar apellidos
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
            pst.setBoolean(10, obj.isEstado());
            pst.setInt(11, obj.getDistrito().getCodigo());
            pst.setInt(12, obj.getSexo().getCodigo());
            pst.setInt(13, obj.getTipoDocumento().getCodigo());
            
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
    public boolean update(Cliente obj) {
        boolean resultado = false;
        String sql = "UPDATE cliente SET nomcli=?, apepcli=?, apemcli=?, doccli=?, telcli=?, corcli=?, estcli=?, coddis=?, codsex=?, codtipd=? " +
                     "WHERE codcli=?";
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
            pst.setInt(9, obj.getSexo().getCodigo());
            pst.setInt(10, obj.getTipoDocumento().getCodigo());
            pst.setInt(11, obj.getCodigo());
            
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
    public boolean delete(Cliente obj) {
        boolean resultado = false;
        String sql = "UPDATE cliente SET estcli=0 WHERE codcli=?";
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
    public boolean enable(Cliente obj) {
        boolean resultado = false;
        String sql = "UPDATE cliente SET estcli=1 WHERE codcli=?";
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
    public boolean disable(Cliente obj) {
        boolean resultado = false;
        String sql = "UPDATE cliente SET estcli=0 WHERE codcli=?";
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