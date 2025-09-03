package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.ICtrlListarDonaciones;
import datatypes.DtMaterial;
import datatypes.DtLibro;
import datatypes.DtArticulo;

public class ListarDonaciones extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlListarDonaciones ctrlListarDonaciones;
    
    private JSpinner spnFechaIni;
    private JSpinner spnFechaFin;
    private JButton btnListarTodas;
    private JButton btnListarPorFecha;
    private JButton btnLimpiar;
    private JTable tablaDonaciones;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    
    // Formato de fecha para mostrar
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public ListarDonaciones(ICtrlListarDonaciones ctrlListarDonaciones) {
        this.ctrlListarDonaciones = ctrlListarDonaciones;
        
        setTitle("Listar Donaciones");
        setSize(800, 500);
        setClosable(true);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(true);
        
        initComponents();
        setupLayout();
        setupListeners();
    }
    
    private void initComponents() {
        // Crear spinners de fecha
        SpinnerDateModel dateModelIni = new SpinnerDateModel();
        spnFechaIni = new JSpinner(dateModelIni);
        JSpinner.DateEditor dateEditorIni = new JSpinner.DateEditor(spnFechaIni, "dd/MM/yyyy");
        spnFechaIni.setEditor(dateEditorIni);
        
        SpinnerDateModel dateModelFin = new SpinnerDateModel();
        spnFechaFin = new JSpinner(dateModelFin);
        JSpinner.DateEditor dateEditorFin = new JSpinner.DateEditor(spnFechaFin, "dd/MM/yyyy");
        spnFechaFin.setEditor(dateEditorFin);
        
        btnListarTodas = new JButton("Listar Todas");
        btnListarPorFecha = new JButton("Listar por Fecha");
        btnLimpiar = new JButton("Limpiar");
        
        // Crear tabla
        String[] columnas = {"ID", "Tipo", "Fecha Ingreso", "Información Adicional"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        tablaDonaciones = new JTable(modeloTabla);
        tablaDonaciones.setRowHeight(25);
        tablaDonaciones.getTableHeader().setReorderingAllowed(false);
        
        scrollPane = new JScrollPane(tablaDonaciones);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
        add(new JLabel("Listado de Donaciones"), gbc);
        
        // Fecha Inicio
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Fecha Inicio:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(spnFechaIni, gbc);
        
        // Fecha Fin
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Fecha Fin:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(spnFechaFin, gbc);
        
        // Botones
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnListarTodas, gbc);
        
        gbc.gridx = 1;
        add(btnListarPorFecha, gbc);
        
        gbc.gridx = 2;
        add(btnLimpiar, gbc);
        
        // Tabla de resultados
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);
    }
    
    private void setupListeners() {
        btnListarTodas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTodasLasDonaciones();
            }
        });
        
        btnListarPorFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarDonacionesPorFecha();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarResultado();
            }
        });
    }
    
    private void listarTodasLasDonaciones() {
        try {
            DtMaterial[] donaciones = ctrlListarDonaciones.listarDonaciones();
            mostrarResultadoEnTabla(donaciones, "Todas las donaciones");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al listar donaciones: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void listarDonacionesPorFecha() {
        Date fechaIni = (Date) spnFechaIni.getValue();
        Date fechaFin = (Date) spnFechaFin.getValue();
        
        if (fechaIni.after(fechaFin)) {
            JOptionPane.showMessageDialog(this, 
                "La fecha de inicio debe ser anterior a la fecha de fin", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            DtMaterial[] donaciones = ctrlListarDonaciones.listarDonacionesPorFecha(fechaIni, fechaFin);
            mostrarResultadoEnTabla(donaciones, "Donaciones del " + dateFormat.format(fechaIni) + " al " + dateFormat.format(fechaFin));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al listar donaciones por fecha: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarResultadoEnTabla(DtMaterial[] donaciones, String titulo) {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        
        if (donaciones == null || donaciones.length == 0) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron donaciones.", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Agregar filas a la tabla
        for (DtMaterial material : donaciones) {
            String id = material.getId();
            String fechaIngreso = dateFormat.format(material.getFechaIngreso());
            String tipo = obtenerTipoMaterial(material);
            String infoAdicional = obtenerInformacionAdicional(material);
            
            Object[] fila = {id, tipo, fechaIngreso, infoAdicional};
            modeloTabla.addRow(fila);
        }
        
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, 
            "Se encontraron " + donaciones.length + " donaciones.", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private String obtenerTipoMaterial(DtMaterial material) {
        if (material instanceof DtLibro) {
            return "Libro";
        } else if (material instanceof DtArticulo) {
            return "Artículo";
        } else {
            return "Material";
        }
    }
    
    private String obtenerInformacionAdicional(DtMaterial material) {
        if (material instanceof DtLibro) {
            DtLibro libro = (DtLibro) material;
            return "Título: " + libro.getTitulo() + " | Páginas: " + libro.getCantidadPaginas();
        } else if (material instanceof DtArticulo) {
            DtArticulo articulo = (DtArticulo) material;
            return "Descripción: " + articulo.getDescripcion() + " | Peso: " + articulo.getPesoKg() + " kg | Dimensiones: " + articulo.getDimensiones();
        } else {
            return "Sin información adicional";
        }
    }
    
    private void limpiarResultado() {
        modeloTabla.setRowCount(0);
        spnFechaIni.setValue(new Date());
        spnFechaFin.setValue(new Date());
    }
}
