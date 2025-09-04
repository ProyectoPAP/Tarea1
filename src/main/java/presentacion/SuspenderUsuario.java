package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaces.ICtrlSuspenderUsuario;
import datatypes.EstadoLector;
import datatypes.DtUsuario;

public class SuspenderUsuario extends JInternalFrame {
    
    private ICtrlSuspenderUsuario ctrlSuspenderUsuario;
    
    // Componentes de la interfaz
    private JComboBox<DtUsuario> cmbUsuarios;
    private JComboBox<EstadoLector> cmbEstado;
    private JButton btnCambiarEstado;
    private JButton btnLimpiar;
    private JLabel lblResultado;
    
    public SuspenderUsuario(ICtrlSuspenderUsuario iCtrlSuspenderUsuario) {
        this.ctrlSuspenderUsuario = iCtrlSuspenderUsuario;
        initComponents();
        setupLayout();
        setupEventHandlers();
        cargarDatos();
    }
    
    private void initComponents() {
        // ComboBox para seleccionar usuario
        cmbUsuarios = new JComboBox<>();
        cmbUsuarios.setPreferredSize(new Dimension(250, 25));
        // Configurar el renderer para mostrar el email del usuario
        cmbUsuarios.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DtUsuario) {
                    setText(((DtUsuario) value).getEmail());
                }
                return this;
            }
        });
        
        // ComboBox para seleccionar estado
        cmbEstado = new JComboBox<>(EstadoLector.values());
        cmbEstado.setPreferredSize(new Dimension(200, 25));
        
        // Botones
        btnCambiarEstado = new JButton("Cambiar Estado");
        btnLimpiar = new JButton("Limpiar");
        
        // Label para mostrar resultado
        lblResultado = new JLabel("");
        lblResultado.setForeground(Color.BLUE);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    private void setupLayout() {
        setTitle("Suspender/Activar Lector");
        setSize(500, 300);
        setClosable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        
        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Título
        JLabel lblTitulo = new JLabel("Cambiar Estado de Lector");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(lblTitulo, gbc);
        
        // Selección de usuario
        JLabel lblUsuario = new JLabel("Seleccionar Lector:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelPrincipal.add(lblUsuario, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(cmbUsuarios, gbc);
        
        // Selección de estado
        JLabel lblEstado = new JLabel("Nuevo Estado:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblEstado, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(cmbEstado, gbc);
        
        // Botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCambiarEstado);
        panelBotones.add(btnLimpiar);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(panelBotones, gbc);
        
        // Resultado
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(lblResultado, gbc);
        
        add(panelPrincipal, BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        btnCambiarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarEstadoUsuario();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    
    private void cargarDatos() {
        try {
            DtUsuario[] usuarios = ctrlSuspenderUsuario.obtenerUsuarios();
            cmbUsuarios.removeAllItems();
            
            if (usuarios.length > 0) {
                for (DtUsuario usuario : usuarios) {
                    cmbUsuarios.addItem(usuario);
                }
                cmbUsuarios.setSelectedIndex(0);
            } else {
                        // Crear un DtUsuario dummy para mostrar el mensaje
        DtUsuario dummy = new DtUsuario("No hay lectores disponibles", "No hay lectores disponibles");
                cmbUsuarios.addItem(dummy);
                btnCambiarEstado.setEnabled(false);
            }
        } catch (Exception e) {
            lblResultado.setText("Error al cargar usuarios: " + e.getMessage());
            lblResultado.setForeground(Color.RED);
        }
    }
    
    private void cambiarEstadoUsuario() {
        try {
            DtUsuario usuarioSeleccionado = (DtUsuario) cmbUsuarios.getSelectedItem();
            
            if (usuarioSeleccionado == null || 
                usuarioSeleccionado.getEmail().equals("No hay lectores disponibles")) {
                lblResultado.setText("Debe seleccionar un lector válido");
                lblResultado.setForeground(Color.RED);
                return;
            }
            
            EstadoLector nuevoEstado = (EstadoLector) cmbEstado.getSelectedItem();
            
            ctrlSuspenderUsuario.suspenderUsuario(usuarioSeleccionado.getEmail(), nuevoEstado);
            
            String accion = nuevoEstado == EstadoLector.SUSPENDIDO ? "suspendido" : "activado";
            lblResultado.setText("Lector " + accion + " exitosamente: " + usuarioSeleccionado.getEmail());  
            lblResultado.setForeground(Color.BLUE);
            
            // Recargar la lista de usuarios
            cargarDatos();
            
        } catch (Exception e) {
            lblResultado.setText("Error al cambiar estado: " + e.getMessage());
            lblResultado.setForeground(Color.RED);
        }
    }
    
    private void limpiarCampos() {
        cmbEstado.setSelectedIndex(0);
        lblResultado.setText("");
        if (cmbUsuarios.getItemCount() > 0) {
            DtUsuario primerItem = (DtUsuario) cmbUsuarios.getItemAt(0);
            if (!primerItem.getEmail().equals("No hay lectores disponibles")) {
                cmbUsuarios.setSelectedIndex(0);
            }
        }
    }
}
