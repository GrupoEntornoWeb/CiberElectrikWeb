package pe.com.ciberelectrik.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.ciberelectrik.bd.Conexion;
import pe.com.ciberelectrik.dao.ProductoDAO;
import pe.com.ciberelectrik.modelo.Producto;
import pe.com.ciberelectrik.modelo.Marca;
import pe.com.ciberelectrik.modelo.Categoria;

public class ProductoDAOImpl implements ProductoDAO {

    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductoDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.codpro, p.nompro, p.despro, p.prepro, p.canpro, p.fecing, p.estpro, " +
                     "m.codmar, m.nommar, c.codcat, c.nomcat " +
                     "FROM producto p " +
                     "INNER JOIN marca m ON p.codmar=m.codmar " +
                     "INNER JOIN categoria c ON p.codcat=c.codcat";

        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Producto obj = new Producto();
                obj.setCodigo(rs.getInt("codpro"));
                obj.setNombre(rs.getString("nompro"));
                obj.setDescripcion(rs.getString("despro"));
                obj.setPrecio(rs.getDouble("prepro")); // AGREGADO: asignar precio
                obj.setCantidad(rs.getInt("canpro"));
                obj.setFechaingreso(rs.getDate("fecing"));
                obj.setEstado(rs.getBoolean("estpro"));
                
                // Crear objeto Marca
                Marca marca = new Marca();
                marca.setCodigo(rs.getInt("codmar"));
                marca.setNombre(rs.getString("nommar"));
                obj.setMarca(marca);
                
                // Crear objeto Categoria
                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("codcat"));
                categoria.setNombre(rs.getString("nomcat"));
                obj.setCategoria(categoria);
                
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
    public List<Producto> findAllCustom() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.codpro, p.nompro, p.despro, p.prepro, p.canpro, p.fecing, p.estpro, " +
                     "m.codmar, m.nommar, c.codcat, c.nomcat " +
                     "FROM producto p " +
                     "INNER JOIN marca m ON p.codmar=m.codmar " +
                     "INNER JOIN categoria c ON p.codcat=c.codcat " +
                     "WHERE p.estpro=1";

        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Producto obj = new Producto();
                obj.setCodigo(rs.getInt("codpro"));
                obj.setNombre(rs.getString("nompro"));
                obj.setDescripcion(rs.getString("despro"));
                obj.setPrecio(rs.getDouble("prepro")); // AGREGADO: asignar precio
                obj.setCantidad(rs.getInt("canpro"));
                obj.setFechaingreso(rs.getDate("fecing"));
                obj.setEstado(rs.getBoolean("estpro"));
                
                Marca marca = new Marca();
                marca.setCodigo(rs.getInt("codmar"));
                marca.setNombre(rs.getString("nommar"));
                obj.setMarca(marca);
                
                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("codcat"));
                categoria.setNombre(rs.getString("nomcat"));
                obj.setCategoria(categoria);
                
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
    public Producto findById(int id) {
        Producto obj = new Producto();
        String sql = "SELECT p.codpro, p.nompro, p.despro, p.prepro, p.canpro, p.fecing, p.estpro, " +
                     "m.codmar, m.nommar, c.codcat, c.nomcat " +
                     "FROM producto p " +
                     "INNER JOIN marca m ON p.codmar=m.codmar " +
                     "INNER JOIN categoria c ON p.codcat=c.codcat " +
                     "WHERE p.codpro=?";

        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                obj.setCodigo(rs.getInt("codpro"));
                obj.setNombre(rs.getString("nompro"));
                obj.setDescripcion(rs.getString("despro"));
                obj.setPrecio(rs.getDouble("prepro")); // AGREGADO: asignar precio
                obj.setCantidad(rs.getInt("canpro"));
                obj.setFechaingreso(rs.getDate("fecing"));
                obj.setEstado(rs.getBoolean("estpro"));
                
                Marca marca = new Marca();
                marca.setCodigo(rs.getInt("codmar"));
                marca.setNombre(rs.getString("nommar"));
                obj.setMarca(marca);
                
                Categoria categoria = new Categoria();
                categoria.setCodigo(rs.getInt("codcat"));
                categoria.setNombre(rs.getString("nomcat"));
                obj.setCategoria(categoria);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Producto obj) {
        boolean resultado = false;
        String sql = "INSERT INTO producto(nompro,despro,prepro,canpro,fecing,estpro,codmar,codcat) " +
                     "VALUES(?,?,?,?,?,?,?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getDescripcion());
            pst.setDouble(3, obj.getPrecio()); // CORREGIDO: asignar precio en lugar de cantidad
            pst.setInt(4, obj.getCantidad());
            pst.setDate(5, new java.sql.Date(obj.getFechaingreso().getTime()));
            pst.setBoolean(6, obj.isEstado());
            pst.setInt(7, obj.getMarca().getCodigo());
            pst.setInt(8, obj.getCategoria().getCodigo());
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
    public boolean update(Producto obj) {
        boolean resultado = false;
        String sql = "UPDATE producto SET nompro=?, despro=?, prepro=?, canpro=?, fecing=?, estpro=?, codmar=?, codcat=? " +
                     "WHERE codpro=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getDescripcion());
            pst.setDouble(3, obj.getPrecio()); // CORREGIDO: asignar precio en lugar de cantidad
            pst.setInt(4, obj.getCantidad());
            pst.setDate(5, new java.sql.Date(obj.getFechaingreso().getTime()));
            pst.setBoolean(6, obj.isEstado());
            pst.setInt(7, obj.getMarca().getCodigo());
            pst.setInt(8, obj.getCategoria().getCodigo());
            pst.setInt(9, obj.getCodigo());
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
    public boolean delete(Producto obj) {
        boolean resultado = false;
        String sql = "UPDATE producto SET estpro=0 WHERE codpro=?";
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
    public boolean enable(Producto obj) {
        boolean resultado = false;
        String sql = "UPDATE producto SET estpro=1 WHERE codpro=?";
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
    public boolean disable(Producto obj) {
        boolean resultado = false;
        String sql = "UPDATE producto SET estpro=0 WHERE codpro=?";
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