package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.DistritoDAO;
import pe.com.ciberelectrik.dao.impl.DistritoDAOImpl;
import pe.com.ciberelectrik.modelo.Distrito;

public class DistritoTest {
    public static void main(String[] args) {
        //implementamos el DAO
        DistritoDAO dao = new DistritoDAOImpl();
        
        //mostrar todo
        System.out.println("********* Mostrar Distrito Todo **********");
        List<Distrito> lista = dao.findAll();
        
        //mostramos los datos
        for(Distrito d : lista){
            System.out.println(d.getCodigo() + " - " + d.getNombre() + " - " + 
                d.isEstado());
        }
    }
}