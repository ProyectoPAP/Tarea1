package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import interfaces.ICtrlListarPrestActXLector;
import datatypes.DtPrestamo;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtMaterial;
import datatypes.EstadoPrestamo;

public class ListarPrestamosActivosLector extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlListarPrestActXLector ctrlListarPreActLector;
    
    // Componentes de entrada
    private JTextField txtCorreo;
    private JLabel lblCorreo;
    
    // Tabla de préstamos
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    
    // Botones
    private JButton btnBuscar;
    private JButton btnLimpiar;
    
    // Formato de fecha
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public ListarPrestamosActivosLector(ICtrlListarPrestActXLector ctrlListarPreActLector) {
        this.ctrlListarPreActLector = ctrlListarPreActLector;
        
        setTitle("Listar Préstamos Activos por Lector");
        setSize(800, 400);
        setClosable(true);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(true);
        initComponents();
        setupLayout();
        setupListeners();
    }
    
    private void initComponents() {
        // Campo de entrada
        lblCorreo = new JLabel("Correo del Lector:");
        txtCorreo = new JTextField(20);
        txtCorreo.setSize(100, 20);
        
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
        btnBuscar = new JButton("Buscar Préstamos Activos");
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
        add(new JLabel("Listado de Préstamos Activos por Lector"), gbc);
        
        // Campo de correo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblCorreo, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtCorreo, gbc);
        
        // Botones
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnLimpiar, gbc);
        
        gbc.gridx = 1;
        add(btnBuscar, gbc);
        
        // Tabla
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);
    }
    
    private void setupListeners() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buscarPrestamosActivos();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pack();
                limpiarTabla();
            }
        });
    }
    
    private void buscarPrestamosActivos() {
        String correo = txtCorreo.getText().trim();
        
        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese el correo del lector", 
                "Campo requerido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Limpiar tabla antes de buscar
            limpiarTabla();
            
            // Obtener préstamos activos del lector
            List<DtPrestamo> prestamos = ctrlListarPreActLector.listarPreActXLector(correo);
            
            if (prestamos.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontraron préstamos activos para el lector: " + correo, 
                    "Sin resultados", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Llenar tabla con los resultados
            for (DtPrestamo prestamo : prestamos) {
                Object[] fila = {
                    prestamo.getLector().getNombre(),
                    prestamo.getBibliotecario().getNombre(),
                    prestamo.getMaterial().getId(),
                    dateFormat.format(prestamo.getFechaPrestamo()),
                    prestamo.getFechaDevolucion() != null ? dateFormat.format(prestamo.getFechaDevolucion()) : "Pendiente",
                    prestamo.getEstado().toString(),
                    obtenerInformacionMaterial(prestamo.getMaterial())
                };
                modeloTabla.addRow(fila);
            }
            
            JOptionPane.showMessageDialog(this, 
                "Se encontraron " + prestamos.size() + " préstamos activos para el lector: " + correo, 
                "Búsqueda exitosa", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar préstamos activos: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private String obtenerInformacionMaterial(DtMaterial material) {
        if (material instanceof datatypes.DtLibro) {
            datatypes.DtLibro libro = (datatypes.DtLibro) material;
            return "Libro - Título: " + libro.getTitulo() + ", Páginas: " + libro.getCantidadPaginas();
        } else if (material instanceof datatypes.DtArticulo) {
            datatypes.DtArticulo articulo = (datatypes.DtArticulo) material;
            return "Artículo - Descripción: " + articulo.getDescripcion() + ", Peso: " + articulo.getPesoKg() + "kg";
        }
        return material.getId();
    }
    
    private void limpiarTabla() {
        modeloTabla.setRowCount(0);
        txtCorreo.setText("");
    }
}
