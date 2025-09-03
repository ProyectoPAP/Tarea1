package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.ICtrlListarPrestamos;
import datatypes.DtPrestamo;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtMaterial;
import datatypes.EstadoPrestamo;

public class ListarPrestamos extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlListarPrestamos ctrlListarPrestamos;
    
    // Tabla de préstamos
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    
    // Botones
    private JButton btnListar;
    private JButton btnLimpiar;
    
    // Formato de fecha
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public ListarPrestamos(ICtrlListarPrestamos ctrlListarPrestamos) {
        this.ctrlListarPrestamos = ctrlListarPrestamos;
        
        setTitle("Listar Préstamos");
        setSize(1000, 600);
        setClosable(true);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(true);
        
        initComponents();
        setupLayout();
        setupListeners();
    }
    
    private void initComponents() {
        // Tabla de préstamos
        String[] columnas = {"Lector", "Bibliotecario", "Material", "Fecha Préstamo", "Fecha Devolución", "Estado", "Información Material"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        
        tablaPrestamos = new JTable(modeloTabla);
        tablaPrestamos.setRowHeight(25);
        tablaPrestamos.getTableHeader().setReorderingAllowed(false);
        
        scrollPane = new JScrollPane(tablaPrestamos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Botones
        btnListar = new JButton("Listar Préstamos");
        btnLimpiar = new JButton("Limpiar");
    }
    
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Listado de Préstamos"), gbc);
        
        // Botones
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnLimpiar, gbc);
        
        gbc.gridx = 1;
        add(btnListar, gbc);
        
        // Tabla de resultados
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);
    }
    
    private void setupListeners() {
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPrestamos();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTabla();
            }
        });
    }
    
    private void listarPrestamos() {
        try {
            DtPrestamo[] prestamos = ctrlListarPrestamos.listarPrestamos();
            mostrarPrestamosEnTabla(prestamos);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al listar préstamos: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarPrestamosEnTabla(DtPrestamo[] prestamos) {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        if (prestamos == null || prestamos.length == 0) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron préstamos.", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Agregar filas a la tabla
        for (DtPrestamo prestamo : prestamos) {
            String lector = obtenerInfoLector(prestamo.getLector());
            String bibliotecario = obtenerInfoBibliotecario(prestamo.getBibliotecario());
            String material = obtenerInfoMaterial(prestamo.getMaterial());
            String fechaPrestamo = formatearFecha(prestamo.getFechaPrestamo());
            String fechaDevolucion = formatearFecha(prestamo.getFechaDevolucion());
            String estado = prestamo.getEstado().toString();
            String infoMaterial = obtenerInformacionAdicionalMaterial(prestamo.getMaterial());
            
            Object[] fila = {
                lector, 
                bibliotecario, 
                material, 
                fechaPrestamo, 
                fechaDevolucion, 
                estado, 
                infoMaterial
            };
            modeloTabla.addRow(fila);
        }
        
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, 
            "Se encontraron " + prestamos.length + " préstamos.", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private String obtenerInfoLector(DtLector lector) {
        if (lector != null) {
            return lector.getEmail() + " (" + lector.getNombre() + ")";
        }
        return "N/A";
    }
    
    private String obtenerInfoBibliotecario(DtBibliotecario bibliotecario) {
        if (bibliotecario != null) {
            return bibliotecario.getEmail() + " (" + bibliotecario.getNombre() + ")";
        }
        return "N/A";
    }
    
    private String obtenerInfoMaterial(DtMaterial material) {
        if (material != null) {
            return material.getId();
        }
        return "N/A";
    }
    
    private String formatearFecha(Date fecha) {
        if (fecha != null) {
            return dateFormat.format(fecha);
        }
        return "N/A";
    }
    
    private String obtenerInformacionAdicionalMaterial(DtMaterial material) {
        if (material instanceof datatypes.DtLibro) {
            datatypes.DtLibro libro = (datatypes.DtLibro) material;
            return "Libro: " + libro.getTitulo() + " | Páginas: " + libro.getCantidadPaginas();
        } else if (material instanceof datatypes.DtArticulo) {
            datatypes.DtArticulo articulo = (datatypes.DtArticulo) material;
            return "Artículo: " + articulo.getDescripcion() + " | Peso: " + articulo.getPesoKg() + " kg";
        } else {
            return "Sin información adicional";
        }
    }
    
    private void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}
