package datatypes;

import java.util.Date;

public class DtLibro extends DtMaterial {
    private String titulo;
    private String cantidadPaginas;

    public DtLibro(String id, Date fechaIngreso, String titulo, String cantidadPaginas) {
        super(id, fechaIngreso);
        this.titulo = titulo;
        this.cantidadPaginas = cantidadPaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCantidadPaginas() {
        return cantidadPaginas;
    }

    @Override
    public String toString() {
        return "DtLibro [titulo=" + titulo + ", cantidadPaginas=" + cantidadPaginas + "]";
    }
}
