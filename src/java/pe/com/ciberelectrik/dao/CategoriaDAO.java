package pe.com.ciberelectrik.dao;

import java.util.List;
import pe.com.ciberelectrik.modelo.Categoria;

public interface CategoriaDAO {
    //definimos las operaciones o metodos a trabajar
    //listar todo
    List<Categoria> findAll();
    //listar solo los habilitados
    List<Categoria> findAllCustom();
    //buscar por codigo
    Categoria findById(int id);
    //registrar
    boolean add(Categoria obj);
    //actualizar
    boolean update(Categoria obj);
    //eliminar
    boolean delete(Categoria obj);
    //habilitar
    boolean enable(Categoria obj);
    //deshabilitar
    boolean disable(Categoria obj);
    
}