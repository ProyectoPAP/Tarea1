package logica;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
    @Id
    private String correo;
    private String nombre;
    

    public Usuario(String correo, String nombre) {
        this.correo = correo;
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override // para imprimir el objeto, por si se necesita
    public String toString() {
        return "Usuario [correo=" + correo + ", nombre=" + nombre + "]";
    }
}
