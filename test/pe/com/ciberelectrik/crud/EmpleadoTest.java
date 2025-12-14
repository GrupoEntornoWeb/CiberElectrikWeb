package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.EmpleadoDAO;
import pe.com.ciberelectrik.dao.impl.EmpleadoDAOImpl;
import pe.com.ciberelectrik.modelo.Empleado;

public class EmpleadoTest {
    public static void main(String[] args) {
        //implementamos el DAO
        EmpleadoDAO dao = new EmpleadoDAOImpl();
        
        //mostrar todo
        System.out.println("********* Mostrar Empleado Todo **********");
        List<Empleado> lista = dao.findAll();
        
        //mostramos los datos
        for(Empleado e : lista){
            System.out.println(e.getCodigo() + " - " + e.getNombres() + " - " + 
                e.getApellidos() + " - " + e.getNumeroDocumento() + " - " + 
                e.getTelefono() + " - " + e.getCorreo() + " - " + 
                e.getRol().getNombre() + " - " + e.getSexo().getNombre() + " - " + 
                e.getDistrito().getNombre() + " - " + e.getTipoDocumento().getNombre() + " - " + 
                e.isEstado());
        }
    }
}