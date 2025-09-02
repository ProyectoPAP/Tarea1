package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import interfaces.ICtrlAltaMaterial;
import datatypes.DtMaterial;

public class AgregarMaterial extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlAltaMaterial ctrlAltaMaterial;
    
    private JTextField txtId;
    private JSpinner spnFechaIngreso;
    private JButton btnAgregar;
    private JButton btnLimpiar;
    
    public AgregarMaterial(ICtrlAltaMaterial ctrlAltaMaterial) {
        this.ctrlAltaMaterial = ctrlAltaMaterial;
        
        setTitle("Agregar Material");
        setSize(400, 300);
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
        
        // Crear un spinner de fecha con la fecha actual
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spnFechaIngreso = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechaIngreso, "dd/MM/yyyy");
        spnFechaIngreso.setEditor(dateEditor);
        
        btnAgregar = new JButton("Agregar Material");
        btnLimpiar = new JButton("Limpiar");
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
        
        // Fecha de Ingreso
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Fecha de Ingreso:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(spnFechaIngreso, gbc);
        
        // Botones
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnLimpiar, gbc);
        
        gbc.gridx = 1;
        add(btnAgregar, gbc);
    }
    
    private void setupListeners() {
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
    
    private void agregarMaterial() {
        String id = txtId.getText().trim();
        Date fechaIngreso = (Date) spnFechaIngreso.getValue();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete el campo ID", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            DtMaterial material = new DtMaterial(id, fechaIngreso);
            ctrlAltaMaterial.altaMaterial(material);
            
            JOptionPane.showMessageDialog(this, 
                "Material agregado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
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
        spnFechaIngreso.setValue(new Date());
        txtId.requestFocus();
    }
}
