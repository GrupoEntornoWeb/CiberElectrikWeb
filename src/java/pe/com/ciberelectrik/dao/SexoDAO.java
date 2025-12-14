package pe.com.ciberelectrik.dao;

import java.util.List;
import pe.com.ciberelectrik.modelo.Sexo;

public interface SexoDAO {
    List<Sexo> findAll();
    List<Sexo> findAllCustom();
    Sexo findById(int id);
    boolean add(Sexo obj);
    boolean update(Sexo obj);
    boolean delete(Sexo obj);
    boolean enable(Sexo obj);
    boolean disable(Sexo obj);
}