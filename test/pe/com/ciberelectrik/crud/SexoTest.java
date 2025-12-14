package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.SexoDAO;
import pe.com.ciberelectrik.dao.impl.SexoDAOImpl;
import pe.com.ciberelectrik.modelo.Sexo;

public class SexoTest {
    public static void main(String[] args) {
        //implementamos el DAO
        SexoDAO dao = new SexoDAOImpl();
        
        //mostrar todo
        System.out.println("********* Mostrar Sexo Todo **********");
        List<Sexo> lista = dao.findAll();
        
        //mostramos los datos
        for(Sexo s : lista){
            System.out.println(s.getCodigo() + " - " + s.getNombre() + " - " + 
                s.isEstado());
        }
    }
}