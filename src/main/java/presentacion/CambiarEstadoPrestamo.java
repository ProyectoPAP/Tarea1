package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import interfaces.ICtrlModPrestamo;
import logica.ManejadorPrestamo;
import logica.Prestamo;
import datatypes.EstadoPrestamo;
import datatypes.DtPrestamo;

public class CambiarEstadoPrestamo extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlModPrestamo ctrlModPrestamo;
    
    // Componentes de la interfaz
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JComboBox<EstadoPrestamo> cmbNuevoEstado;
    private JButton btnSeleccionarPrestamo;
    private JButton btnCambiarEstado;
    private JButton btnActualizarTabla;
    
    // Datos del préstamo seleccionado
    private String emailLectorSeleccionado;
    private String emailBibliotecarioSeleccionado;
    private String idMaterialSeleccionado;
    
    public CambiarEstadoPrestamo(ICtrlModPrestamo ctrlModPrestamo) {
        this.ctrlModPrestamo = ctrlModPrestamo;
        
        setTitle("Cambiar Estado de Préstamo");
        setSize(900, 600);
        setClosable(true);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(true);
        
        initComponents();
        setupLayout();
        setupListeners();
        cargarDatos();
    }
    
    private void initComponents() {
        // Tabla de préstamos
        String[] columnas = {"Lector", "Bibliotecario", "Material", "Fecha Préstamo", "Estado Actual"};
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
        
        // Combo box para nuevo estado
        cmbNuevoEstado = new JComboBox<>(EstadoPrestamo.values());
        
        // Botones
        btnSeleccionarPrestamo = new JButton("Seleccionar Préstamo");
        btnCambiarEstado = new JButton("Cambiar Estado");
        btnActualizarTabla = new JButton("Actualizar Tabla");
        
        // Inicializar variables
        emailLectorSeleccionado = null;
        emailBibliotecarioSeleccionado = null;
        idMaterialSeleccionado = null;
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
        add(new JLabel("Cambiar Estado de Préstamo"), gbc);
        
        // Tabla de préstamos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);
        
        // Selección de nuevo estado
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Nuevo Estado:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(cmbNuevoEstado, gbc);
        
        // Botones
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnActualizarTabla, gbc);
        
        gbc.gridx = 1;
        add(btnSeleccionarPrestamo, gbc);
        
        gbc.gridx = 2;
        add(btnCambiarEstado, gbc);
    }
    
    private void setupListeners() {
        // Listener para la tabla - cuando se hace clic en una fila, seleccionar el préstamo
        tablaPrestamos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tablaPrestamos.getSelectedRow();
                if (row >= 0) {
                    emailLectorSeleccionado = (String) tablaPrestamos.getValueAt(row, 0);
                    emailBibliotecarioSeleccionado = (String) tablaPrestamos.getValueAt(row, 1);
                    idMaterialSeleccionado = (String) tablaPrestamos.getValueAt(row, 2);
                    
                    JOptionPane.showMessageDialog(CambiarEstadoPrestamo.this, 
                        "Préstamo seleccionado:\n" +
                        "Lector: " + emailLectorSeleccionado + "\n" +
                        "Bibliotecario: " + emailBibliotecarioSeleccionado + "\n" +
                        "Material: " + idMaterialSeleccionado, 
                        "Préstamo Seleccionado", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        btnSeleccionarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tablaPrestamos.getSelectedRow();
                if (row >= 0) {
                    emailLectorSeleccionado = (String) tablaPrestamos.getValueAt(row, 0);
                    emailBibliotecarioSeleccionado = (String) tablaPrestamos.getValueAt(row, 1);
                    idMaterialSeleccionado = (String) tablaPrestamos.getValueAt(row, 2);
                    
                    JOptionPane.showMessageDialog(CambiarEstadoPrestamo.this, 
                        "Préstamo seleccionado:\n" +
                        "Lector: " + emailLectorSeleccionado + "\n" +
                        "Bibliotecario: " + emailBibliotecarioSeleccionado + "\n" +
                        "Material: " + idMaterialSeleccionado, 
                        "Préstamo Seleccionado", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(CambiarEstadoPrestamo.this, 
                        "Por favor seleccione un préstamo de la tabla", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnCambiarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarEstadoPrestamo();
            }
        });
        
        btnActualizarTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
    }
    
    private void cargarDatos() {
        try {
            ManejadorPrestamo mP = ManejadorPrestamo.getInstancia();
            ArrayList<Prestamo> prestamos = mP.obtenerPrestamos();
            
            modeloTabla.setRowCount(0);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            for (Prestamo prestamo : prestamos) {
                Object[] fila = {
                    prestamo.getLector().getEmail(),
                    prestamo.getBibliotecario().getEmail(),
                    prestamo.getMaterial().getId(),
                    sdf.format(prestamo.getFechaPrestamo()),
                    prestamo.getEstado().toString()
                };
                modeloTabla.addRow(fila);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar datos: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cambiarEstadoPrestamo() {
        if (emailLectorSeleccionado == null || emailBibliotecarioSeleccionado == null || idMaterialSeleccionado == null) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un préstamo primero", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        EstadoPrestamo nuevoEstado = (EstadoPrestamo) cmbNuevoEstado.getSelectedItem();
        if (nuevoEstado == null) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un nuevo estado", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Crear DtPrestamo con el nuevo estado
            ManejadorPrestamo mP = ManejadorPrestamo.getInstancia();
            Prestamo prestamoActual = mP.buscarPrestamoPorParametros(emailLectorSeleccionado, emailBibliotecarioSeleccionado, idMaterialSeleccionado);
            
            if (prestamoActual == null) {
                throw new RuntimeException("No se encontró el préstamo especificado");
            }
            
            // Crear DtPrestamo con el nuevo estado
            DtPrestamo dtPrestamo = prestamoActual.getDtPrestamo();
            dtPrestamo.setEstado(nuevoEstado);
            
            ctrlModPrestamo.modificarPrestamo(dtPrestamo);
            
            JOptionPane.showMessageDialog(this, 
                "Estado del préstamo cambiado exitosamente a: " + nuevoEstado.toString(), 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar selección y actualizar tabla
            emailLectorSeleccionado = null;
            emailBibliotecarioSeleccionado = null;
            idMaterialSeleccionado = null;
            cargarDatos();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al cambiar estado del préstamo: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}

