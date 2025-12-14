package pe.com.ciberelectrik.dao;

import java.util.List;
import pe.com.ciberelectrik.modelo.Empleado;

public interface EmpleadoDAO {
    List<Empleado> findAll();
    List<Empleado> findAllCustom();
    Empleado findById(int id);
    Empleado findByDocumento(String documento);
    Empleado login(String usuario, String clave);
    boolean add(Empleado obj);
    boolean update(Empleado obj);
    boolean delete(Empleado obj);
    boolean enable(Empleado obj);
    boolean disable(Empleado obj);
}