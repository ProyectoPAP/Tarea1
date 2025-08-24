package logica;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.IdClass;
import java.util.Date;
import persistencia.PrestamoID;

@Entity
@IdClass(PrestamoID.class)
public class Prestamo {
    @Id
    private String idMaterial;
    
    @Id
    private String nombreLector;
    
    @Id
    private String idBibliotecario;
    
    @ManyToOne
    @JoinColumn(name = "lector_nombre", insertable = false, updatable = false)
    private Lector lector;
    
    @ManyToOne
    @JoinColumn(name = "material_id", insertable = false, updatable = false)
    private Material material;
    
    @ManyToOne
    @JoinColumn(name = "bibliotecario_nombre")
    private Bibliotecario bibliotecario;
    
    private Date fechaSolicitud;
    private Date fechaDevolucion;
    private String estado;
    
    public Prestamo() {
        // Default constructor for JPA
    }
    
    public Prestamo(String idMaterial, String nombreLector, String idBibliotecario, 
                   Lector lector, Material material, Bibliotecario bibliotecario, 
                   Date fechaSolicitud, Date fechaDevolucion, String estado) {
        this.idMaterial = idMaterial;
        this.nombreLector = nombreLector;
        this.idBibliotecario = idBibliotecario;
        this.lector = lector;
        this.material = material;
        this.bibliotecario = bibliotecario;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }
    
    // Getters and setters for ID fields
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
    
    public Lector getLector() {
        return lector;
    }
    
    public void setLector(Lector lector) {
        this.lector = lector;
    }
    
    public Material getMaterial() {
        return material;
    }
    
    public void setMaterial(Material material) {
        this.material = material;
    }
    
    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }
    
    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }
    
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }
    
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Prestamo{" +
                "idMaterial='" + idMaterial + '\'' +
                ", nombreLector='" + nombreLector + '\'' +
                ", idBibliotecario='" + idBibliotecario + '\'' +
                ", lector=" + (lector != null ? lector.getNombre() : "null") +
                ", material=" + (material != null ? material.getId() : "null") +
                ", bibliotecario=" + (bibliotecario != null ? bibliotecario.getNombre() : "null") +
                ", fechaSolicitud=" + fechaSolicitud +
                ", fechaDevolucion=" + fechaDevolucion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
