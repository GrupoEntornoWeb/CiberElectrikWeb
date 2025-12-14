
package pe.com.ciberelectrik.dao;

import java.util.List;
import pe.com.ciberelectrik.modelo.Marca;

public interface MarcaDAO {
    List<Marca> findAll();
    //listar solo los habilitados
    List<Marca> findAllCustom();
    //buscar por codigo
    Marca findById(int id);
    //registrar
    boolean add(Marca obj);
    //actualizar
    boolean update(Marca obj);
    //eliminar
    boolean delete(Marca obj);
    //habilitar
    boolean enable(Marca obj);
    //deshabilitar
    boolean disable(Marca obj);
    
}
