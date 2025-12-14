package pe.com.ciberelectrik.dao;

import java.util.List;
import pe.com.ciberelectrik.modelo.Distrito;

public interface DistritoDAO {
    List<Distrito> findAll();
    List<Distrito> findAllCustom();
    Distrito findById(int id);
    boolean add(Distrito obj);
    boolean update(Distrito obj);
    boolean delete(Distrito obj);
    boolean enable(Distrito obj);
    boolean disable(Distrito obj);
}