package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.ICtrlAltaMaterial;
import datatypes.DtLibro;
import datatypes.DtArticulo;

public class AgregarMaterial extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlAltaMaterial ctrlAltaMaterial;
    
    // Campos comunes
    private JTextField txtId;
    private JSpinner spnFechaIngreso;
    private JComboBox<String> cmbTipoMaterial;
    
    // Campos específicos para Libro
    private JTextField txtTitulo;
    private JTextField txtCantidadPaginas;
    private JPanel panelLibro;
    
    // Campos específicos para Artículo
    private JTextField txtDescripcion;
    private JTextField txtPesoKg;
    private JTextField txtDimensiones;
    private JPanel panelArticulo;
    
    // Botones
    private JButton btnAgregar;
    private JButton btnLimpiar;
    
    public AgregarMaterial(ICtrlAltaMaterial ctrlAltaMaterial) {
        this.ctrlAltaMaterial = ctrlAltaMaterial;
        
        setTitle("Agregar Material");
        setSize(500, 450);
        setClosable(true);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(true);
        
        initComponents();
        setupLayout();
        setupListeners();
    }
    
    private void initComponents() {
        txtId = new JTextField(20);
        
        // Crear un spinner de fecha con la fecha actual (solo lectura)
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spnFechaIngreso = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechaIngreso, "dd/MM/yyyy");
        spnFechaIngreso.setEditor(dateEditor);
        spnFechaIngreso.setEnabled(false); // Hacer no editable
        
        // Combo box para seleccionar tipo de material
        String[] tipos = {"Seleccione tipo...", "Libro", "Artículo"};
        cmbTipoMaterial = new JComboBox<>(tipos);
        
        // Campos para Libro
        txtTitulo = new JTextField(20);
        txtCantidadPaginas = new JTextField(20);
        panelLibro = new JPanel(new GridBagLayout());
        setupPanelLibro();
        
        // Campos para Artículo
        txtDescripcion = new JTextField(20);
        txtPesoKg = new JTextField(20);
        txtDimensiones = new JTextField(20);
        panelArticulo = new JPanel(new GridBagLayout());
        setupPanelArticulo();
        
        btnAgregar = new JButton("Agregar Material");
        btnLimpiar = new JButton("Limpiar");
        
        // Inicialmente ocultar ambos paneles
        panelLibro.setVisible(false);
        panelArticulo.setVisible(false);
    }
    
    private void setupPanelLibro() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelLibro.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        panelLibro.add(txtTitulo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelLibro.add(new JLabel("Cantidad de Páginas:"), gbc);
        gbc.gridx = 1;
        panelLibro.add(txtCantidadPaginas, gbc);
    }
    
    private void setupPanelArticulo() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelArticulo.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        panelArticulo.add(txtDescripcion, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelArticulo.add(new JLabel("Peso (kg):"), gbc);
        gbc.gridx = 1;
        panelArticulo.add(txtPesoKg, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panelArticulo.add(new JLabel("Dimensiones:"), gbc);
        gbc.gridx = 1;
        panelArticulo.add(txtDimensiones, gbc);
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
        add(new JLabel("Formulario de Alta de Material"), gbc);
        
        // ID
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtId, gbc);
        
        // Fecha de Ingreso (automática)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Fecha de Ingreso:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(spnFechaIngreso, gbc);
        
        // Tipo de Material
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Tipo de Material:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(cmbTipoMaterial, gbc);
        
        // Panel de Libro
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelLibro, gbc);
        
        // Panel de Artículo
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelArticulo, gbc);
        
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
        cmbTipoMaterial.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String tipoSeleccionado = (String) cmbTipoMaterial.getSelectedItem();
                    mostrarCamposEspecificos(tipoSeleccionado);
                }
            }
        });
        
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarMaterial();
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
        panelLibro.setVisible(false);
        panelArticulo.setVisible(false);
        
        if ("Libro".equals(tipo)) {
            panelLibro.setVisible(true);
        } else if ("Artículo".equals(tipo)) {
            panelArticulo.setVisible(true);
        }
        
        // Ajustar el tamaño de la ventana
        pack();
    }
    
    private void agregarMaterial() {
        String id = txtId.getText().trim();
        String tipoSeleccionado = (String) cmbTipoMaterial.getSelectedItem();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete el campo ID", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if ("Seleccione tipo...".equals(tipoSeleccionado)) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un tipo de material", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Date fechaIngreso = new Date(); // Fecha actual del sistema
            
            if ("Libro".equals(tipoSeleccionado)) {
                String titulo = txtTitulo.getText().trim();
                String cantidadPaginas = txtCantidadPaginas.getText().trim();
                
                if (titulo.isEmpty() || cantidadPaginas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Por favor complete todos los campos del libro", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                DtLibro libro = new DtLibro(id, fechaIngreso, titulo, cantidadPaginas);
                ctrlAltaMaterial.altaMaterial(libro);
                
                JOptionPane.showMessageDialog(this, 
                    "Libro agregado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                
            } else if ("Artículo".equals(tipoSeleccionado)) {
                String descripcion = txtDescripcion.getText().trim();
                String pesoStr = txtPesoKg.getText().trim();
                String dimensiones = txtDimensiones.getText().trim();
                
                if (descripcion.isEmpty() || pesoStr.isEmpty() || dimensiones.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Por favor complete todos los campos del artículo", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                float pesoKg;
                try {
                    pesoKg = Float.parseFloat(pesoStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, 
                        "El peso debe ser un número válido", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                DtArticulo articulo = new DtArticulo(id, fechaIngreso, descripcion, pesoKg, dimensiones);
                ctrlAltaMaterial.altaMaterial(articulo);
                
                JOptionPane.showMessageDialog(this, 
                    "Artículo agregado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
            limpiarCampos();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar material: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtId.setText("");
        txtTitulo.setText("");
        txtCantidadPaginas.setText("");
        txtDescripcion.setText("");
        txtPesoKg.setText("");
        txtDimensiones.setText("");
        cmbTipoMaterial.setSelectedIndex(0);
        spnFechaIngreso.setValue(new Date());
        
        panelLibro.setVisible(false);
        panelArticulo.setVisible(false);
        
        txtId.requestFocus();
    }
}
