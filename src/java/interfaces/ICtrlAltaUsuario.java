package interfaces;

public interface ICtrlAltaUsuario {
    public void altaUsuario(String nombre, String correo, String tipo);
    public void crearLector(String direccion, DtFecha fechaRegistro, estadoUsuario estado, zona zona);
    public void crearBibliotecario(int numEmpleado, zona zona);    
}