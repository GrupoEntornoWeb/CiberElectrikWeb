package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.MarcaDAO;
import pe.com.ciberelectrik.dao.impl.MarcaDAOImpl;
import pe.com.ciberelectrik.modelo.Marca;

public class MarcaTest {
    public static void main(String[] args) {
        //implementamos el DAO
        MarcaDAO dao = new MarcaDAOImpl();
        
        //mostrar todo
        System.out.println("********* Mostrar Marca Todo **********");
        List<Marca> lista = dao.findAll();
        
        //mostramos los datos
        for(Marca m : lista){
            System.out.println(m.getCodigo() + " - " + m.getNombre() + " - " + 
                m.isEstado());
        }
    }
}