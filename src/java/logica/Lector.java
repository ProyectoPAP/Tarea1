package logica;
import datatypes.estadoUsuario;
import datatypes.DtFecha;
import datatypes.zona;

public class Lector extends usuario {
    private String direccion;
    private DtFecha fechaRegistro;
    private estadoUsuario estado;
    
    public Lector(String nombre, String correo, String contraseña, String direccion, DtFecha fechaRegistro, estadoUsuario estado, zona zona) {
        super(nombre, correo, contraseña, zona);
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public DtFecha getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(DtFecha fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public estadoUsuario getEstado() {
        return estado;
    }
    
    public void setEstado(estadoUsuario estado) { 
        this.estado = estado;
    }

    public zona getZona() {
        return super.getZona();
    }

    public void setZona(zona zona) {
        super.setZona(zona);
    }
}
