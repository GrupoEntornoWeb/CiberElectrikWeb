package pe.com.ciberelectrik.crud;

import java.util.List;
import pe.com.ciberelectrik.dao.CategoriaDAO;
import pe.com.ciberelectrik.dao.impl.CategoriaDAOImpl;
import pe.com.ciberelectrik.modelo.Categoria;

public class CategoriaTest {
  public static void main(String[] args) {
    //implementamos el DAO
    CategoriaDAO dao=new CategoriaDAOImpl();
    //mostrar todo
    System.out.println("********* Mostrar Categoria Todo **********");
    List<Categoria> lista=dao.findAll();
    //mostramos los datos
    for(Categoria c : lista){
      System.out.println(c.getCodigo()+" - "+c.getNombre()+" - "+
          c.isEstado());
    }
  }
}