package pe.com.ciberelectrik.dao;

import java.util.List;
import pe.com.ciberelectrik.modelo.Cliente;

public interface ClienteDAO {
    List<Cliente> findAll();
    List<Cliente> findAllCustom();
    Cliente findById(int id);
    Cliente findByDocumento(String documento);
    boolean add(Cliente obj);
    boolean update(Cliente obj);
    boolean delete(Cliente obj);
    boolean enable(Cliente obj);
    boolean disable(Cliente obj);
}