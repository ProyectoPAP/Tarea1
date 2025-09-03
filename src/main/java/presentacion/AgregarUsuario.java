package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import interfaces.ICtrlAltaUsr;
import datatypes.DtUsuario;
import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.EstadoLector;
import datatypes.Zona;

public class AgregarUsuario extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlAltaUsr ctrlAltaUsr;
    
    // Campos comunes
    private JTextField txtNombre;
    private JTextField txtEmail;
    
    // Selector de tipo de usuario
    private JComboBox<String> cmbTipoUsuario;
    
    // Campos específicos para Lector
    private JTextField txtDireccion;
    private JSpinner spnFechaRegistro;
    private JComboBox<EstadoLector> cbEstado;
    private JComboBox<Zona> cbZona;
    private JPanel panelLector;
    
    // Campos específicos para Bibliotecario
    private JTextField txtNumeroEmpleado;
    private JPanel panelBibliotecario;
    
    // Botones
    private JButton btnAgregar;
    private JButton btnLimpiar;
    
    // Formato de fecha
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public AgregarUsuario(ICtrlAltaUsr ctrlAltaUsr) {
        this.ctrlAltaUsr = ctrlAltaUsr;
        
        setTitle("Agregar Usuario");
        setSize(500, 450);
        setClosable(true);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(true);
        
        initComponents();
        setupLayout();
        setupListeners();
        
        // Por defecto no mostrar ningún panel específico
        cmbTipoUsuario.setSelectedIndex(0);
        mostrarCamposEspecificos("Seleccione tipo...");
    }
    
    private void initComponents() {
        // Campos comunes
        txtNombre = new JTextField(20);
        txtEmail = new JTextField(20);
        
        // Selector de tipo de usuario
        String[] tipos = {"Seleccione tipo...", "Lector", "Bibliotecario"};
        cmbTipoUsuario = new JComboBox<>(tipos);
        
        // Campos específicos para Lector
        txtDireccion = new JTextField(20);
        
        // Fecha de registro automática (solo lectura)
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spnFechaRegistro = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechaRegistro, "dd/MM/yyyy");
        spnFechaRegistro.setEditor(dateEditor);
        spnFechaRegistro.setEnabled(false); // Hacer no editable
        
        cbEstado = new JComboBox<>(EstadoLector.values());
        cbZona = new JComboBox<>(Zona.values());
        
        // Panel para campos de Lector
        panelLector = new JPanel(new GridBagLayout());
        setupPanelLector();
        
        // Campos específicos para Bibliotecario
        txtNumeroEmpleado = new JTextField(20);
        
        // Panel para campos de Bibliotecario
        panelBibliotecario = new JPanel(new GridBagLayout());
        setupPanelBibliotecario();
        
        // Botones
        btnAgregar = new JButton("Agregar Usuario");
        btnLimpiar = new JButton("Limpiar");
        
        // Inicialmente ocultar ambos paneles
        panelLector.setVisible(false);
        panelBibliotecario.setVisible(false);
    }
    
    private void setupPanelLector() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelLector.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        panelLector.add(txtDireccion, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelLector.add(new JLabel("Fecha de Registro:"), gbc);
        gbc.gridx = 1;
        panelLector.add(spnFechaRegistro, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panelLector.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        panelLector.add(cbEstado, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panelLector.add(new JLabel("Zona:"), gbc);
        gbc.gridx = 1;
        panelLector.add(cbZona, gbc);
    }
    
    private void setupPanelBibliotecario() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelBibliotecario.add(new JLabel("Número de Empleado:"), gbc);
        gbc.gridx = 1;
        panelBibliotecario.add(txtNumeroEmpleado, gbc);
    }
    
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Formulario de Alta de Usuario"), gbc);
        
        // Tipo de Usuario
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Tipo de Usuario:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(cmbTipoUsuario, gbc);
        
        // Email (campo principal)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtEmail, gbc);
        
        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtNombre, gbc);
        
        // Panel de Lector
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelLector, gbc);
        
        // Panel de Bibliotecario
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBibliotecario, gbc);
        
        // Botones
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnLimpiar, gbc);
        
        gbc.gridx = 1;
        add(btnAgregar, gbc);
    }
    
    private void setupListeners() {
        // Listener para el cambio de tipo de usuario
        cmbTipoUsuario.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String tipoSeleccionado = (String) cmbTipoUsuario.getSelectedItem();
                    mostrarCamposEspecificos(tipoSeleccionado);
                }
            }
        });
        
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    
    private void mostrarCamposEspecificos(String tipo) {
        panelLector.setVisible(false);
        panelBibliotecario.setVisible(false);
        
        if ("Lector".equals(tipo)) {
            panelLector.setVisible(true);
        } else if ("Bibliotecario".equals(tipo)) {
            panelBibliotecario.setVisible(true);
        }
        
        // Ajustar el tamaño de la ventana
        pack();
    }
    
    private void agregarUsuario() {
        String email = txtEmail.getText().trim();
        String nombre = txtNombre.getText().trim();
        String tipoSeleccionado = (String) cmbTipoUsuario.getSelectedItem();
        
        if (email.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete los campos obligatorios (Email y Nombre)", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar formato de email
        if (!validarFormatoEmail(email)) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese un formato de email válido", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if ("Seleccione tipo...".equals(tipoSeleccionado)) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un tipo de usuario", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            DtUsuario usuario;
            
            if ("Lector".equals(tipoSeleccionado)) {
                // Validar campos específicos de Lector
                String direccion = txtDireccion.getText().trim();
                if (direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Por favor complete la dirección", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Fecha automática del sistema
                Date fechaRegistro = new Date();
                EstadoLector estado = (EstadoLector) cbEstado.getSelectedItem();
                Zona zona = (Zona) cbZona.getSelectedItem();
                
                usuario = new DtLector(nombre, email, direccion, fechaRegistro, estado, zona);
            } else if ("Bibliotecario".equals(tipoSeleccionado)) {
                // Validar campos específicos de Bibliotecario
                String numeroEmpleado = txtNumeroEmpleado.getText().trim();
                if (numeroEmpleado.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Por favor complete el número de empleado", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                usuario = new DtBibliotecario(nombre, email, numeroEmpleado);
            } else {
                return; // No debería llegar aquí
            }
            
            ctrlAltaUsr.altaUsuario(usuario);
            
            JOptionPane.showMessageDialog(this, 
                "Usuario " + tipoSeleccionado + " agregado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar usuario: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarFormatoEmail(String email) {
        // Validación básica de formato de email
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtNumeroEmpleado.setText("");
        spnFechaRegistro.setValue(new Date());
        cbEstado.setSelectedIndex(0);
        cbZona.setSelectedIndex(0);
        cmbTipoUsuario.setSelectedIndex(0);
        
        panelLector.setVisible(false);
        panelBibliotecario.setVisible(false);
        
        txtEmail.requestFocus();
    }
}
