package pe.com.ciberelectrik.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pe.com.ciberelectrik.bd.Conexion;
import pe.com.ciberelectrik.dao.CategoriaDAO;
import pe.com.ciberelectrik.modelo.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

    //creamos un objeto de la clase Conexion
    private final Conexion objconexion;
    //creamos un objeto de tipo connection
    private Connection xcon;
    //creamos un Statement -> sentencias SQL
    private Statement st;
    //creamos un PreparedStatement -> sentencias SQL con parametros
    private PreparedStatement pst;
    //creamos un ResulSet rs -> almacenar resultados de una consulta
    private ResultSet rs;

    public CategoriaDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<Categoria> findAll() {
        //creamos una lista de tipo Categioria
        List<Categoria> lista = new ArrayList<>();
        //consulta SQL
        String sql = "select * from categoria";

        try {
            //conectamos a la base de datos
            xcon = objconexion.obtenerConexion();
            //creamos el Statment para realizar la consulta
            st = xcon.createStatement();
            //ejecutamos el Statment y los resultados los guardo en el ResultSet
            rs = st.executeQuery(sql);
            //rs.next() -> es un bucle que repite mientras el proximo campo no sea nulo
            while (rs.next()) {
                //creamos un objeto de la clase Categoria
                Categoria obj = new Categoria();
                //asignamos los valores del ResulSet
                obj.setCodigo(rs.getInt("codcat"));
                obj.setNombre(rs.getString("nomcat"));
                obj.setEstado(rs.getBoolean("estcat"));
                //agregamos el objeto a la lista
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
    public List<Categoria> findAllCustom() {
        //creamos una lista de tipo Categioria
        List<Categoria> lista = new ArrayList<>();
        //consulta SQL
        String sql = "select * from categoria where estcat=1";

        try {
            //conectamos a la base de datos
            xcon = objconexion.obtenerConexion();
            //creamos el Statment para realizar la consulta
            st = xcon.createStatement();
            //ejecutamos el Statment y los resultados los guardo en el ResultSet
            rs = st.executeQuery(sql);
            //rs.next() -> es un bucle que repite mientras el proximo campo no sea nulo
            while (rs.next()) {
                //creamos un objeto de la clase Categoria
                Categoria obj = new Categoria();
                //asignamos los valores del ResulSet
                obj.setCodigo(rs.getInt("codcat"));
                obj.setNombre(rs.getString("nomcat"));
                obj.setEstado(rs.getBoolean("estcat"));
                //agregamos el objeto a la lista
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
    public Categoria findById(int id) {
        //creamos un objeto de la clase Categoria
        Categoria obj = new Categoria();
        //consulta SQL - no deberiamos de usarlo
        //String sql="select * from categoria where codcat="+id;
        //consulta SQL correcta
        //? -> parametro -> que es un valor que puede cambiar
        String sql = "select * from categoria where codcat=?";

        try {
            xcon = objconexion.obtenerConexion();
            //creamos el PreparedStatement
            pst = xcon.prepareStatement(sql);
            //asignamos el valor al parametro
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                //asignamos los valores del ResulSet
                obj.setCodigo(rs.getInt("codcat"));
                obj.setNombre(rs.getString("nomcat"));
                obj.setEstado(rs.getBoolean("estcat"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            objconexion.cerrarConexion(xcon);
        }
        return obj;
    }

    @Override
    public boolean add(Categoria obj) {
        boolean resultado = false;
        String sql = "insert into categoria(nomcat,estcat) values(?,?)";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setBoolean(2, obj.isEstado());
            //ejecutamos la sentencia
            //executeUpdate() -> porque se va a realizar una actualizacion en la tabla
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
    public boolean update(Categoria obj) {
        boolean resultado = false;
        String sql = "update categoria set nomcat=?, estcat=? where codcat=?";
        try {
            xcon = objconexion.obtenerConexion();
            pst = xcon.prepareStatement(sql);
            pst.setString(1, obj.getNombre());
            pst.setBoolean(2, obj.isEstado());
            pst.setInt(3, obj.getCodigo());
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
    public boolean delete(Categoria obj) {
        boolean resultado = false;
        //Eliminacion Fisica -> borra el dato y no permite recuperarlo
        //String sql="delete from categoria where codcat=?";
        //Eliminacion Logica -> cambiar el estado deshabilitado(0)
        String sql = "update categoria set estcat=0 where codcat=?";
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
    public boolean enable(Categoria obj) {
        boolean resultado = false;
        String sql = "update categoria set estcat=1 where codcat=?";
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
    public boolean disable(Categoria obj) {
        boolean resultado = false;
        String sql = "update categoria set estcat=0 where codcat=?";
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


