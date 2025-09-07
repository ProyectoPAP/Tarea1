package presentacion;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import interfaces.ICtrlModPrestamo;
import logica.ManejadorPrestamo;
import logica.Prestamo;
import datatypes.EstadoPrestamo;
import datatypes.DtPrestamo;

public class ModPrestamo extends JDialog {
    private static final long serialVersionUID = 1L;
    
    private ICtrlModPrestamo ctrlModPrestamo;
    
    // Componentes de la interfaz
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JButton btnSeleccionarPrestamo;
    private JButton btnActualizarTabla;
    private JButton btnModificarPrestamo;
    private JButton btnCancelar;
    
    // Campos de edición
    private JTextField txtEmailLector;
    private JTextField txtEmailBibliotecario;
    private JTextField txtIdMaterial;
    private JTextField txtFechaPrestamo;
    private JTextField txtFechaDevolucion;
    private JComboBox<EstadoPrestamo> cmbEstado;
    
    // Datos del préstamo seleccionado
    private String emailLectorSeleccionado;
    private String emailBibliotecarioSeleccionado;
    private String idMaterialSeleccionado;
    private Prestamo prestamoSeleccionado;
    
    // Panel de edición
    private JPanel panelEdicion;
    private boolean modoEdicion = false;
    
    public ModPrestamo(ICtrlModPrestamo ctrlModPrestamo) {
        this.ctrlModPrestamo = ctrlModPrestamo;
        
        setTitle("Actualizar Préstamo");
        setSize(1000, 700);
        setModal(false);
        setResizable(true);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        
        initComponents();
        setupLayout();
        setupListeners();
        cargarDatos();
    }
    
    private void initComponents() {
        // Tabla de préstamos
        String[] columnas = {"Lector", "Bibliotecario", "Material", "Fecha Préstamo", "Fecha Devolución", "Estado Actual"};
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
        
        // Botones principales
        btnSeleccionarPrestamo = new JButton("Seleccionar Préstamo");
        btnActualizarTabla = new JButton("Actualizar Tabla");
        btnModificarPrestamo = new JButton("Modificar Préstamo");
        btnCancelar = new JButton("Cancelar");
        
        // Campos de edición
        txtEmailLector = new JTextField(20);
        txtEmailBibliotecario = new JTextField(20);
        txtIdMaterial = new JTextField(20);
        txtFechaPrestamo = new JTextField(20);
        txtFechaDevolucion = new JTextField(20);
        cmbEstado = new JComboBox<>(EstadoPrestamo.values());
        
        // Panel de edición
        panelEdicion = new JPanel();
        panelEdicion.setLayout(new GridBagLayout());
        
        // Inicializar variables
        emailLectorSeleccionado = null;
        emailBibliotecarioSeleccionado = null;
        idMaterialSeleccionado = null;
        prestamoSeleccionado = null;
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
        add(new JLabel("Actualizar Información de Préstamo"), gbc);
        
        // Tabla de préstamos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        add(scrollPane, gbc);
        
        // Botones principales
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnActualizarTabla, gbc);
        
        gbc.gridx = 1;
        add(btnSeleccionarPrestamo, gbc);
        
        gbc.gridx = 2;
        add(btnModificarPrestamo, gbc);
        
        // Panel de edición (inicialmente oculto)
        setupPanelEdicion();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.4;
        add(panelEdicion, gbc);
        
        panelEdicion.setVisible(false);
    }
    
    private void setupPanelEdicion() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Título del panel de edición
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panelEdicion.add(new JLabel("Modificar Información del Préstamo"), gbc);
        
        // Email Lector
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panelEdicion.add(new JLabel("Email Lector:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelEdicion.add(txtEmailLector, gbc);
        
        // Email Bibliotecario
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelEdicion.add(new JLabel("Email Bibliotecario:"), gbc);
        
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelEdicion.add(txtEmailBibliotecario, gbc);
        
        // ID Material
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelEdicion.add(new JLabel("ID Material:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelEdicion.add(txtIdMaterial, gbc);
        
        // Fecha Préstamo
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelEdicion.add(new JLabel("Fecha Préstamo (dd/MM/yyyy):"), gbc);
        
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelEdicion.add(txtFechaPrestamo, gbc);
        
        // Fecha Devolución
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panelEdicion.add(new JLabel("Fecha Devolución (dd/MM/yyyy):"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelEdicion.add(txtFechaDevolucion, gbc);
        
        // Estado
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelEdicion.add(new JLabel("Estado:"), gbc);
        
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelEdicion.add(cmbEstado, gbc);
        
        // Botones de edición
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panelEdicion.add(btnModificarPrestamo, gbc);
        
        gbc.gridx = 2;
        panelEdicion.add(btnCancelar, gbc);
    }
    
    private void setupListeners() {
        // Listener para la tabla - cuando se hace clic en una fila, solo seleccionar visualmente
        tablaPrestamos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Solo selección visual, no se hace nada más
                // El usuario debe hacer clic en "Seleccionar Préstamo" para activar la edición
            }
        });
        
        btnSeleccionarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionarPrestamo()) {
                    activarModoEdicion();
                }
            }
        });
        
        btnActualizarTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
        
        btnModificarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modoEdicion) {
                    modificarPrestamo();
                } else {
                    activarModoEdicion();
                }
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarEdicion();
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
                String fechaDevolucionStr = prestamo.getFechaDevolucion() != null ? 
                    sdf.format(prestamo.getFechaDevolucion()) : "No devuelto";
                
                Object[] fila = {
                    prestamo.getLector().getEmail(),
                    prestamo.getBibliotecario().getEmail(),
                    prestamo.getMaterial().getId(),
                    sdf.format(prestamo.getFechaPrestamo()),
                    fechaDevolucionStr,
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
    
    private boolean seleccionarPrestamo() {
        int row = tablaPrestamos.getSelectedRow();
        if (row >= 0) {
            emailLectorSeleccionado = (String) tablaPrestamos.getValueAt(row, 0);
            emailBibliotecarioSeleccionado = (String) tablaPrestamos.getValueAt(row, 1);
            idMaterialSeleccionado = (String) tablaPrestamos.getValueAt(row, 2);
            
            // Buscar el préstamo completo
            try {
                ManejadorPrestamo mP = ManejadorPrestamo.getInstancia();
                prestamoSeleccionado = mP.buscarPrestamoPorParametros(
                    emailLectorSeleccionado, 
                    emailBibliotecarioSeleccionado, 
                    idMaterialSeleccionado
                );
                
                if (prestamoSeleccionado != null) {
                    return true; // Selección exitosa
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "No se encontró el préstamo especificado", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al seleccionar préstamo: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un préstamo de la tabla", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void activarModoEdicion() {
        if (prestamoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un préstamo primero", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Llenar los campos con los datos actuales del préstamo
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        txtEmailLector.setText(prestamoSeleccionado.getLector().getEmail());
        txtEmailBibliotecario.setText(prestamoSeleccionado.getBibliotecario().getEmail());
        txtIdMaterial.setText(prestamoSeleccionado.getMaterial().getId());
        txtFechaPrestamo.setText(sdf.format(prestamoSeleccionado.getFechaPrestamo()));
        
        if (prestamoSeleccionado.getFechaDevolucion() != null) {
            txtFechaDevolucion.setText(sdf.format(prestamoSeleccionado.getFechaDevolucion()));
        } else {
            txtFechaDevolucion.setText("");
        }
        
        cmbEstado.setSelectedItem(prestamoSeleccionado.getEstado());
        
        // Mostrar panel de edición y cambiar modo
        panelEdicion.setVisible(true);
        modoEdicion = true;
        btnModificarPrestamo.setText("Guardar Cambios");
        
        // Hacer que los campos de identificación no sean editables
        txtEmailLector.setEditable(false);
        txtEmailBibliotecario.setEditable(false);
        txtIdMaterial.setEditable(false);
    }
    
    private void cancelarEdicion() {
        panelEdicion.setVisible(false);
        modoEdicion = false;
        btnModificarPrestamo.setText("Modificar Préstamo");
        
        // Limpiar campos
        txtEmailLector.setText("");
        txtEmailBibliotecario.setText("");
        txtIdMaterial.setText("");
        txtFechaPrestamo.setText("");
        txtFechaDevolucion.setText("");
        cmbEstado.setSelectedIndex(0);
        
        // Limpiar selección
        prestamoSeleccionado = null;
        emailLectorSeleccionado = null;
        emailBibliotecarioSeleccionado = null;
        idMaterialSeleccionado = null;
    }
    
    private void modificarPrestamo() {
        try {
            // Validar campos obligatorios
            if (txtFechaPrestamo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "La fecha de préstamo es obligatoria", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar formato de fechas
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            try {
                sdf.parse(txtFechaPrestamo.getText().trim());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, 
                    "Formato de fecha de préstamo inválido. Use dd/MM/yyyy", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!txtFechaDevolucion.getText().trim().isEmpty()) {
                try {
                    sdf.parse(txtFechaDevolucion.getText().trim());
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(this, 
                        "Formato de fecha de devolución inválido. Use dd/MM/yyyy", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Crear DtPrestamo con los datos modificados
            DtPrestamo dtPrestamo = prestamoSeleccionado.getDtPrestamo();
            dtPrestamo.setEstado((EstadoPrestamo) cmbEstado.getSelectedItem());
            
            // Actualizar fechas si se proporcionaron
            try {
                Date fechaPrestamo = sdf.parse(txtFechaPrestamo.getText().trim());
                dtPrestamo.setFechaPrestamo(fechaPrestamo);
                
                if (!txtFechaDevolucion.getText().trim().isEmpty()) {
                    Date fechaDevolucion = sdf.parse(txtFechaDevolucion.getText().trim());
                    dtPrestamo.setFechaDevolucion(fechaDevolucion);
                } else {
                    dtPrestamo.setFechaDevolucion(null);
                }
            } catch (ParseException e) {
                // Este error ya se validó anteriormente, no debería ocurrir aquí
                throw new RuntimeException("Error inesperado al parsear fechas");
            }
            
            // Actualizar el préstamo en la base de datos
            ctrlModPrestamo.modificarPrestamo(dtPrestamo);
            
            JOptionPane.showMessageDialog(this, 
                "Préstamo actualizado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Cancelar edición y actualizar tabla
            cancelarEdicion();
            cargarDatos();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al modificar préstamo: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
