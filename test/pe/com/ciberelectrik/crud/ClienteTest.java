package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.ClienteDAO;
import pe.com.ciberelectrik.dao.impl.ClienteDAOImpl;
import pe.com.ciberelectrik.modelo.Cliente;

public class ClienteTest {
    public static void main(String[] args) {
        //implementamos el DAO
        ClienteDAO dao = new ClienteDAOImpl();
        
        //mostrar todo
        System.out.println("********* Mostrar Cliente Todo **********");
        List<Cliente> lista = dao.findAll();
        
        //mostramos los datos
        for(Cliente c : lista){
            System.out.println(c.getCodigo() + " - " + c.getNombres() + " - " + 
                c.getApellidos() + " - " + c.getNumeroDocumento() + " - " + 
                c.getTelefono() + " - " + c.getCorreo() + " - " + 
                c.getSexo().getNombre() + " - " + c.getDistrito().getNombre() + " - " + 
                c.getTipoDocumento().getNombre() + " - " + c.isEstado());
        }
    }
}