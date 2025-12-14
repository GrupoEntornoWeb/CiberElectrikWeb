package pe.com.ciberelectrik.dao.impl;

import java.util.List;
import pe.com.ciberelectrik.dao.TicketPedidoDAO;
import pe.com.ciberelectrik.modelo.DetalleTicketPedido;
import pe.com.ciberelectrik.modelo.TicketPedido;
import java.sql.*;
import java.util.ArrayList;
import pe.com.ciberelectrik.bd.Conexion;
import pe.com.ciberelectrik.modelo.Cliente;
import pe.com.ciberelectrik.modelo.Empleado;


public class TicketPedidoDAOImpl implements TicketPedidoDAO{
    
    private final Conexion objconexion;
    private Connection xcon;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public TicketPedidoDAOImpl() {
        this.objconexion = new Conexion();
    }

    @Override
    public List<TicketPedido> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public List<TicketPedido> findAllCustom() {
        List<TicketPedido> lista = new ArrayList<>();
        String sql = "select t.nrotic,t.fectic,c.nomcli,c.apepcli,c.apemcli,"
                + "e.nomemp,e.apepemp, e.apememp, t.esttic,"
                + "sum(dt.pretic*dt.cantic) as subtotal from ticketpedido t "
                + "inner join  detalleticketpedido dt on t.nrotic=dt.nrotic "
                + "inner join cliente c on t.codcli=c.codcli inner join "
                + "empleado e on t.codemp=e.codemp where t.esttic=1 group by "
                + "t.nrotic,t.fectic,c.nomcli,c.apepcli,c.apemcli,e.nomemp,"
                + "e.apepemp,e.apememp,e.estemp";
        try {
            xcon = objconexion.obtenerConexion();
            st = xcon.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                TicketPedido obj = new TicketPedido();
                Empleado objemp=new Empleado();
                Cliente objcli=new Cliente();
                
                obj.setNumero(rs.getInt("nrotic"));
                obj.setFecha(rs.getDate("fectic"));
                obj.setEstado(rs.getBoolean("esttic"));
                obj.setSubtotal(rs.getDouble("subtotal"));
                
                objcli.setNombre(rs.getString("nomcli"));
                objcli.setApellidopaterno(rs.getString("apepcli"));
                objcli.setApellidomaterno(rs.getString("apemcli"));
                obj.setCliente(objcli);
                
                objemp.setNombre(rs.getString("nomemp"));
                objemp.setApellidopaterno(rs.getString("apepemp"));
                objemp.setApellidomaterno(rs.getString("apememp"));
                obj.setEmpleado(objemp);
                
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
    public boolean add(TicketPedido obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean enable(TicketPedido obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean disable(TicketPedido obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int setNumber() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNumber() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addDetails(DetalleTicketPedido obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
