package pe.com.ciberelectrik.conexion;

import java.sql.*;
import pe.com.ciberelectrik.bd.Conexion;
public class ConexionTest {

    public static void main(String[] args) {
        //creamos una instancia de la Conexion
        Conexion objconexion=new Conexion();
        Connection xcon=null;
        
        try {
            xcon=objconexion.obtenerConexion();
            if(xcon!=null){
                System.out.println("Conexion exitosa");
            }else{
                System.out.println("No se puede conextar");
            }
        } catch (Exception ex) {
            System.out.println("Error de conexion: "+ex.toString());
        }finally{
        objconexion.cerrarConexion(xcon);
        System.out.println("Conexion cerrada");
    }
          
    }
    
}
