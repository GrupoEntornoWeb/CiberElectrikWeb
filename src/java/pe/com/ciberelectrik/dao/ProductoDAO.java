package pe.com.ciberelectrik.dao;

import java.util.List;
import pe.com.ciberelectrik.modelo.Producto;

public interface ProductoDAO {
    List<Producto> findAll();
    //listar solo los habilitados
    List<Producto> findAllCustom();
    //buscar por codigo
    Producto findById(int id);
    //registrar
    boolean add(Producto obj);
    //actualizar
    boolean update(Producto obj);
    //eliminar
    boolean delete(Producto obj);
    //habilitar
    boolean enable(Producto obj);
    //deshabilitar
    boolean disable(Producto obj);
}
