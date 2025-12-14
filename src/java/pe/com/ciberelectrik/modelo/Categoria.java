package pe.com.ciberelectrik.modelo;

public class Categoria {
    private int Codigo;
    private String nombre;
    private boolean estado;   

//metodos constructores
//metodo constructor vacío     
    public Categoria() {
    }
   
//metodo constructor con parametros 
    public Categoria(int Codigo, String nombre, boolean estado) {
        this.Codigo = Codigo;
        this.nombre = nombre;
        this.estado = estado;
    }
//metodos get y set 
    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}

