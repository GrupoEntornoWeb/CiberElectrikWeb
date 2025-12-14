package pe.com.ciberelectrik.bd;
//importamos biblioteca para trabajar con BD
import java.sql.*;

public class Conexion {
    //para realizar una conexion a una BD es necesario: 
    //usuario de la BD (MySQL)
    private final String usuario="root";
    //clave de la BD (MySQL)
    private final String clave="root";
    //cadena de conexion 
    private final String cadena="jdbc:mysql://localhost:3306/bdciberelectrik2026?useSSL=false&allowPublicKeyRetrieval=true";
    //driver para la conexion a la B (MySQL)
    private final String driver="com.mysql.cj.jdbc.Driver";
    
    //creamos una funcion de tipo connection para la conexion a la BD
    public Connection obtenerConexion (){
        Connection xcon=null;
        try{
            //cargamos el driver
            Class.forName(driver);
            //establecemos la conexixon a la BD
            xcon=DriverManager.getConnection(cadena,usuario,clave);
        } catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error de conexion: "+ex.toString());
        }
        return xcon;
    }
    //creamos un procedimiento para cerrar la conexion de la BD
    public void cerrarConexion(Connection xcon){
        if(xcon!=null){
            try{
                xcon.close();
            } catch(SQLException ex) {
                System.out.println("Error al cerrar la conexion: "+ex.toString());
            }
        }
    }
}
