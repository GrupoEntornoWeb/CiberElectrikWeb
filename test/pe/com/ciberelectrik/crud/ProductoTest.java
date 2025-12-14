package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.ProductoDAO;
import pe.com.ciberelectrik.dao.impl.ProductoDAOImpl;
import pe.com.ciberelectrik.modelo.Producto;

public class ProductoTest {
    public static void main(String[] args) {
        //implementamos el DAO
        ProductoDAO dao = new ProductoDAOImpl();
        
        //mostrar todo
        System.out.println("********* Mostrar Producto Todo **********");
        List<Producto> lista = dao.findAll();
        
        //mostramos los datos
        for(Producto p : lista){
            System.out.println(p.getCodigo() + " - " + p.getNombre() + " - " + 
                p.getDescripcion() + " - " + p.getCantidad() + " - " + 
                p.getFechaingreso() + " - " + p.getMarca().getNombre() + " - " + 
                p.getCategoria().getNombre() + " - " + p.isEstado());
        }
    }
}