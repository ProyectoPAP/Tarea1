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
import java.util.ArrayList;
import java.util.Map;

import interfaces.ICtrlListarCantidadVecesPrestados;
import logica.Material;
import datatypes.DtMaterial;

public class ListarCantidadVecesPrestados extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlListarCantidadVecesPrestados ctrlListarCantidadVecesPrestados;
    
    // Tabla de materiales
    private JTable tablaMateriales;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    
    // Botones
    private JButton btnListar;
    private JButton btnLimpiar;
    
    // Formato de fecha
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public ListarCantidadVecesPrestados(ICtrlListarCantidadVecesPrestados ctrlListarCantidadVecesPrestados) {
        this.ctrlListarCantidadVecesPrestados = ctrlListarCantidadVecesPrestados;
        
        setTitle("Listar Cantidad de Veces Prestados");
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
        // Tabla de materiales
        String[] columnas = {"ID Material", "Tipo", "Fecha Ingreso", "Cantidad Préstamos", "Información Adicional"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        
        tablaMateriales = new JTable(modeloTabla);
        tablaMateriales.setRowHeight(25);
        tablaMateriales.getTableHeader().setReorderingAllowed(false);
        
        scrollPane = new JScrollPane(tablaMateriales);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Botones
        btnListar = new JButton("Listar Materiales");
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
        add(new JLabel("Listado de Materiales - Cantidad de Veces Prestados"), gbc);
        
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
                listarMateriales();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTabla();
            }
        });
    }
    
    private void listarMateriales() {
        try {
            ArrayList<Map.Entry<Integer, Material>> materiales = ctrlListarCantidadVecesPrestados.listarCantidadVecesPrestadoCompleto();
            mostrarMaterialesEnTabla(materiales);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al listar materiales: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarMaterialesEnTabla(ArrayList<Map.Entry<Integer, Material>> materiales) {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        if (materiales == null || materiales.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron materiales.", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Agregar filas a la tabla
        for (Map.Entry<Integer, Material> entrada : materiales) {
            Integer cantidadPrestamos = entrada.getKey();
            Material material = entrada.getValue();
            DtMaterial dtMaterial = material.getDtMaterial();
            
            String idMaterial = material.getId();
            String tipo = obtenerTipoMaterial(material);
            String fechaIngreso = formatearFecha(material.getFechaIngreso());
            String cantidadPrestamosStr = cantidadPrestamos.toString();
            String infoAdicional = obtenerInformacionAdicionalMaterial(dtMaterial);
            
            Object[] fila = {
                idMaterial, 
                tipo, 
                fechaIngreso, 
                cantidadPrestamosStr, 
                infoAdicional
            };
            modeloTabla.addRow(fila);
        }
        
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, 
            "Se encontraron " + materiales.size() + " materiales.", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private String obtenerTipoMaterial(Material material) {
        if (material instanceof logica.Libro) {
            return "Libro";
        } else if (material instanceof logica.Articulo) {
            return "Artículo";
        } else {
            return "Material";
        }
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
            return "Título: " + libro.getTitulo() + " | Páginas: " + libro.getCantidadPaginas();
        } else if (material instanceof datatypes.DtArticulo) {
            datatypes.DtArticulo articulo = (datatypes.DtArticulo) material;
            return "Descripción: " + articulo.getDescripcion() + " | Peso: " + articulo.getPesoKg() + " kg";
        } else {
            return "Sin información adicional";
        }
    }
    
    private void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
}
