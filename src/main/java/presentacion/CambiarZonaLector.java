package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.ICtrlCambiarZonaLector;
import datatypes.Zona;
import datatypes.DtUsuario;

public class CambiarZonaLector extends JInternalFrame {
    
    private ICtrlCambiarZonaLector ctrlCambiarZona;
    
    // Componentes de la interfaz
    private JComboBox<DtUsuario> cmbLectores;
    private JComboBox<Zona> cmbZona;
    private JButton btnCambiarZona;
    private JButton btnLimpiar;
    private JLabel lblResultado;
    
    public CambiarZonaLector(ICtrlCambiarZonaLector iCtrlCambiarZona) {
        this.ctrlCambiarZona = iCtrlCambiarZona;
        initComponents();
        setupLayout();
        setupEventHandlers();
        cargarDatos();
    }
    
    private void initComponents() {
        // ComboBox para seleccionar lector
        cmbLectores = new JComboBox<>();
        cmbLectores.setPreferredSize(new Dimension(250, 25));
        // Configurar el renderer para mostrar el email del lector
        cmbLectores.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DtUsuario) {
                    setText(((DtUsuario) value).getEmail());
                }
                return this;
            }
        });
        
        // ComboBox para seleccionar zona
        cmbZona = new JComboBox<>(Zona.values());
        cmbZona.setPreferredSize(new Dimension(200, 25));
        
        // Botones
        btnCambiarZona = new JButton("Cambiar Zona");
        btnLimpiar = new JButton("Limpiar");
        
        // Label para mostrar resultado
        lblResultado = new JLabel("");
        lblResultado.setForeground(Color.BLUE);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    private void setupLayout() {
        setTitle("Cambiar Zona de Lector");
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
        JLabel lblTitulo = new JLabel("Cambiar Zona de Lector");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(lblTitulo, gbc);
        
        // Selección de lector
        JLabel lblLector = new JLabel("Seleccionar Lector:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelPrincipal.add(lblLector, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(cmbLectores, gbc);
        
        // Selección de zona
        JLabel lblZona = new JLabel("Nueva Zona:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblZona, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(cmbZona, gbc);
        
        // Botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCambiarZona);
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
        btnCambiarZona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarZonaLector();
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
            DtUsuario[] lectores = ctrlCambiarZona.listarLectores();
            cmbLectores.removeAllItems();
            
            if (lectores.length > 0) {
                for (DtUsuario lector : lectores) {
                    cmbLectores.addItem(lector);
                }
                cmbLectores.setSelectedIndex(0);
            } else {
                // Crear un DtUsuario dummy para mostrar el mensaje
                DtUsuario dummy = new DtUsuario("No hay lectores disponibles", "No hay lectores disponibles");
                cmbLectores.addItem(dummy);
                btnCambiarZona.setEnabled(false);
            }
        } catch (Exception e) {
            lblResultado.setText("Error al cargar lectores: " + e.getMessage());
            lblResultado.setForeground(Color.RED);
        }
    }
    
    private void cambiarZonaLector() {
        try {
            DtUsuario lectorSeleccionado = (DtUsuario) cmbLectores.getSelectedItem();
            
            if (lectorSeleccionado == null || 
                lectorSeleccionado.getEmail().equals("No hay lectores disponibles")) {
                lblResultado.setText("Debe seleccionar un lector válido");
                lblResultado.setForeground(Color.RED);
                return;
            }
            
            Zona nuevaZona = (Zona) cmbZona.getSelectedItem();
            
            ctrlCambiarZona.cambiarZonaLector(lectorSeleccionado.getEmail(), nuevaZona);
            
            lblResultado.setText("Zona cambiada exitosamente para: " + lectorSeleccionado.getEmail());  
            lblResultado.setForeground(Color.BLUE);
            
            // Recargar la lista de lectores
            cargarDatos();
            
        } catch (Exception e) {
            lblResultado.setText("Error al cambiar zona: " + e.getMessage());
            lblResultado.setForeground(Color.RED);
        }
    }
    
    private void limpiarCampos() {
        cmbZona.setSelectedIndex(0);
        lblResultado.setText("");
        if (cmbLectores.getItemCount() > 0) {
            DtUsuario primerItem = (DtUsuario) cmbLectores.getItemAt(0);
            if (!primerItem.getEmail().equals("No hay lectores disponibles")) {
                cmbLectores.setSelectedIndex(0);
            }
        }
    }
}
