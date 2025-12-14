package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.TipoDocumentoDAO;
import pe.com.ciberelectrik.dao.impl.TipoDocumentoDAOImpl;
import pe.com.ciberelectrik.modelo.TipoDocumento;

public class TipoDocumentoTest {
    public static void main(String[] args) {
        //implementamos el DAO
        TipoDocumentoDAO dao = new TipoDocumentoDAOImpl();
        
        //mostrar todo
        System.out.println("********* Mostrar TipoDocumento Todo **********");
        List<TipoDocumento> lista = dao.findAll();
        
        //mostramos los datos
        for(TipoDocumento t : lista){
            System.out.println(t.getCodigo() + " - " + t.getNombre() + " - " + 
                t.isEstado());
        }
    }
}