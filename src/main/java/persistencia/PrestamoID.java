package persistencia;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
    
@Embeddable
public class PrestamoID implements Serializable{
    private String idMaterial;
    private String nombreLector;
    private String idBibliotecario;

    // Constructor por defecto requerido por JPA
    public PrestamoID() {
    }

    public PrestamoID(String idMaterial, String nombreLector, String idBibliotecario) {
        this.idMaterial = idMaterial;
        this.nombreLector = nombreLector;
        this.idBibliotecario = idBibliotecario;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombreLector() {
        return nombreLector;
    }

    public void setNombreLector(String nombreLector) {
        this.nombreLector = nombreLector;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PrestamoID that = (PrestamoID) obj;
        return Objects.equals(idMaterial, that.idMaterial) && 
               Objects.equals(nombreLector, that.nombreLector) && 
               Objects.equals(idBibliotecario, that.idBibliotecario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMaterial, nombreLector, idBibliotecario);
    }

    @Override
    public String toString() {
        return "PrestamoID{" +
                "idMaterial='" + idMaterial + '\'' +
                ", nombreLector='" + nombreLector + '\'' +
                ", idBibliotecario='" + idBibliotecario + '\'' +
                '}';
    }
}

//consultar en clase por esta Clase