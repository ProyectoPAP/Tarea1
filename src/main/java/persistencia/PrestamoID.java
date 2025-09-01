package persistencia;

import java.io.Serializable;

//No es una entidad, debe implementar serializable
public class PrestamoID implements Serializable{

    private static final long serialVersionUID = 1L;

    private String lector;
    private String bibliotecario;
    private String material;

    public PrestamoID() {
        super();
    }

    public String getLector() {
        return lector;
    }

    public String getBibliotecario() {
        return bibliotecario;
    }

    public String getMaterial() {
        return material;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public void setBibliotecario(String bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lector == null) ? 0 : lector.hashCode());
        result = prime * result + ((bibliotecario == null) ? 0 : bibliotecario.hashCode());
        result = prime * result + ((material == null) ? 0 : material.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrestamoID other = (PrestamoID) obj;
        if (lector == null) {
            if (other.lector != null)
                return false;
        } else if (!lector.equals(other.lector))
            return false;
        if (bibliotecario == null) {
            return false;
        } else if (!bibliotecario.equals(other.bibliotecario))
            return false;
        if (material == null) {
            return false;
        } else if (!material.equals(other.material))
            return false;
        return true;
    }
}
