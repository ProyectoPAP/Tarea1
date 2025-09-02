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
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
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
    private JRadioButton rbLector;
    private JRadioButton rbBibliotecario;
    private ButtonGroup bgTipoUsuario;
    
    // Campos específicos para Lector
    private JTextField txtDireccion;
    private JTextField txtFechaRegistro;
    private JButton btnCalendario;
    private JComboBox<EstadoLector> cbEstado;
    private JComboBox<Zona> cbZona;
    
    // Campos específicos para Bibliotecario
    private JTextField txtNumeroEmpleado;
    
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
        
        // Por defecto seleccionar Lector
        rbLector.setSelected(true);
        actualizarCamposVisibles();
    }
    
    private void initComponents() {
        // Campos comunes
        txtNombre = new JTextField(20);
        txtEmail = new JTextField(20);
        
        // Selector de tipo de usuario
        rbLector = new JRadioButton("Lector");
        rbBibliotecario = new JRadioButton("Bibliotecario");
        bgTipoUsuario = new ButtonGroup();
        bgTipoUsuario.add(rbLector);
        bgTipoUsuario.add(rbBibliotecario);
        
        // Campos específicos para Lector
        txtDireccion = new JTextField(20);
        
        txtFechaRegistro = new JTextField(15);
        txtFechaRegistro.setEditable(false);
        txtFechaRegistro.setText(dateFormat.format(new Date()));
        
        btnCalendario = new JButton("📅");
        btnCalendario.setPreferredSize(new Dimension(30, 20));
        
        cbEstado = new JComboBox<>(EstadoLector.values());
        cbZona = new JComboBox<>(Zona.values());
        
        // Campos específicos para Bibliotecario
        txtNumeroEmpleado = new JTextField(20);
        
        // Botones
        btnAgregar = new JButton("Agregar Usuario");
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
        add(new JLabel("Formulario de Alta de Usuario"), gbc);
        
        // Tipo de Usuario
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Tipo de Usuario:"), gbc);
        
        JPanel panelTipo = new JPanel();
        panelTipo.add(rbLector);
        panelTipo.add(rbBibliotecario);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(panelTipo, gbc);
        
        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtNombre, gbc);
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtEmail, gbc);
        
        // Campos específicos para Lector
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Dirección:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtDireccion, gbc);
        
        // Fecha de Registro con botón de calendario
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Fecha de Registro:"), gbc);
        
        JPanel panelFecha = new JPanel(new BorderLayout());
        panelFecha.add(txtFechaRegistro, BorderLayout.CENTER);
        panelFecha.add(btnCalendario, BorderLayout.EAST);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(panelFecha, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Estado:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(cbEstado, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Zona:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(cbZona, gbc);
        
        // Campos específicos para Bibliotecario
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Número de Empleado:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtNumeroEmpleado, gbc);
        
        // Botones
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnLimpiar, gbc);
        
        gbc.gridx = 1;
        add(btnAgregar, gbc);
    }
    
    private void setupListeners() {
        // Listeners para el cambio de tipo de usuario
        rbLector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    actualizarCamposVisibles();
                }
            }
        });
        
        rbBibliotecario.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    actualizarCamposVisibles();
                }
            }
        });
        
        // Listener para el botón de calendario
        btnCalendario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCalendario();
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
    
    private void mostrarCalendario() {
        JPopupMenu popup = new JPopupMenu();
        
        // Crear panel del calendario
        JPanel panelCalendario = new JPanel(new BorderLayout());
        panelCalendario.setPreferredSize(new Dimension(300, 250));
        
        // Obtener fecha actual
        Calendar cal = Calendar.getInstance();
        int mesActual = cal.get(Calendar.MONTH);
        int añoActual = cal.get(Calendar.YEAR);
        
        // Crear tabla del calendario
        String[] columnas = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        
        // Llenar la tabla con los días del mes
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int primerDia = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int diasEnMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        Object[] fila = new Object[7];
        int dia = 1;
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j < primerDia) {
                    fila[j] = "";
                } else if (dia <= diasEnMes) {
                    fila[j] = dia++;
                } else {
                    fila[j] = "";
                }
            }
            model.addRow(fila);
            fila = new Object[7];
        }
        
        JTable tabla = new JTable(model);
        tabla.setRowHeight(30);
        tabla.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                return this;
            }
        });
        
        // Agregar listener para seleccionar fecha
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tabla.getSelectedRow();
                int col = tabla.getSelectedColumn();
                Object valor = tabla.getValueAt(row, col);
                
                if (valor != null && !valor.toString().isEmpty()) {
                    try {
                        int diaSeleccionado = Integer.parseInt(valor.toString());
                        cal.set(Calendar.DAY_OF_MONTH, diaSeleccionado);
                        Date fechaSeleccionada = cal.getTime();
                        txtFechaRegistro.setText(dateFormat.format(fechaSeleccionada));
                        popup.setVisible(false);
                    } catch (NumberFormatException ex) {
                        // Ignorar clics en celdas vacías
                    }
                }
            }
        });
        
        // Panel superior con mes y año
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Mes: " + obtenerNombreMes(mesActual) + " " + añoActual));
        
        panelCalendario.add(panelSuperior, BorderLayout.NORTH);
        panelCalendario.add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        popup.add(panelCalendario);
        
        // Mostrar popup debajo del botón de calendario
        popup.show(btnCalendario, 0, btnCalendario.getHeight());
    }
    
    private String obtenerNombreMes(int mes) {
        String[] nombresMeses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        return nombresMeses[mes];
    }
    
    private void actualizarCamposVisibles() {
        if (rbLector.isSelected()) {
            // Mostrar campos de Lector
            txtDireccion.setVisible(true);
            txtFechaRegistro.setVisible(true);
            btnCalendario.setVisible(true);
            cbEstado.setVisible(true);
            cbZona.setVisible(true);
            
            // Ocultar campos de Bibliotecario
            txtNumeroEmpleado.setVisible(false);
            
            // Actualizar etiquetas
            txtDireccion.getParent().getComponent(0).setVisible(true);
            txtFechaRegistro.getParent().getParent().getComponent(0).setVisible(true);
            cbEstado.getParent().getComponent(0).setVisible(true);
            cbZona.getParent().getComponent(0).setVisible(true);
            txtNumeroEmpleado.getParent().getComponent(0).setVisible(false);
        } else {
            // Mostrar campos de Bibliotecario
            txtNumeroEmpleado.setVisible(true);
            
            // Ocultar campos de Lector
            txtDireccion.setVisible(false);
            txtFechaRegistro.setVisible(false);
            btnCalendario.setVisible(false);
            cbEstado.setVisible(false);
            cbZona.setVisible(false);
            
            // Actualizar etiquetas
            txtNumeroEmpleado.getParent().getComponent(0).setVisible(true);
            txtFechaRegistro.getParent().getParent().getComponent(0).setVisible(false);
            txtDireccion.getParent().getComponent(0).setVisible(false);
            cbEstado.getParent().getComponent(0).setVisible(false);
            cbZona.getParent().getComponent(0).setVisible(false);
        }
        
        // Revalidar y repintar
        revalidate();
        repaint();
    }
    
    private void agregarUsuario() {
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        
        if (nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete los campos obligatorios (Nombre y Email)", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            DtUsuario usuario;
            
            if (rbLector.isSelected()) {
                // Validar campos específicos de Lector
                String direccion = txtDireccion.getText().trim();
                if (direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Por favor complete la dirección", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Parsear fecha del campo de texto
                Date fechaRegistro;
                try {
                    fechaRegistro = dateFormat.parse(txtFechaRegistro.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, 
                        "Por favor seleccione una fecha válida", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                EstadoLector estado = (EstadoLector) cbEstado.getSelectedItem();
                Zona zona = (Zona) cbZona.getSelectedItem();
                
                usuario = new DtLector(nombre, email, direccion, fechaRegistro, estado, zona);
            } else {
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
            }
            
            ctrlAltaUsr.altaUsuario(usuario);
            
            JOptionPane.showMessageDialog(this, 
                "Usuario " + (rbLector.isSelected() ? "Lector" : "Bibliotecario") + " agregado exitosamente", 
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
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        txtNumeroEmpleado.setText("");
        txtFechaRegistro.setText(dateFormat.format(new Date()));
        cbEstado.setSelectedIndex(0);
        cbZona.setSelectedIndex(0);
        rbLector.setSelected(true);
        actualizarCamposVisibles();
        txtNombre.requestFocus();
    }
}
