package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.RolDAO;
import pe.com.ciberelectrik.dao.impl.RolDAOImpl;
import pe.com.ciberelectrik.modelo.Rol;

public class RolTest {
    public static void main(String[] args) {
        //implementamos el DAO
        RolDAO dao = new RolDAOImpl();
        
        //mostrar todo
        System.out.println("********* Mostrar Rol Todo **********");
        List<Rol> lista = dao.findAll();
        
        //mostramos los datos
        for(Rol r : lista){
            System.out.println(r.getCodigo() + " - " + r.getNombre() + " - " + 
                r.isEstado());
        }
    }
}