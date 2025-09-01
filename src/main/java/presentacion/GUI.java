package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import datatypes.*;
import logica.*;

public class GUI extends JFrame {
    
    private JTabbedPane tabbedPane;
    private JTextArea outputArea;
    
    public GUI() {
        setTitle("Sistema de Biblioteca - Controladores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        tabbedPane = new JTabbedPane();
        
        // Crear pestañas solo para los controladores solicitados
        tabbedPane.addTab("Alta Usuario", createAltaUsuarioPanel());
        tabbedPane.addTab("Alta Donación Libro", createAltaDonacionLibroPanel());
        tabbedPane.addTab("Alta Donación Material", createAltaDonacionMaterialPanel());
        
        // Área de salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        
        // Layout principal
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }
    
    private JPanel createAltaUsuarioPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Campos de entrada
        JTextField nombreField = new JTextField(20);
        JTextField correoField = new JTextField(20);
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Lector", "Bibliotecario"});
        JTextField direccionField = new JTextField(20);
        JTextField numEmpleadoField = new JTextField(20);
        JComboBox<Zona> zonaCombo = new JComboBox<>(Zona.values());
        JComboBox<EstadoLector> estadoCombo = new JComboBox<>(EstadoLector.values());
        
        // Fecha de registro
        JSpinner diaSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        JSpinner mesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        JSpinner anioSpinner = new JSpinner(new SpinnerNumberModel(2024, 1900, 2100, 1));
        
        // Botón de ejecutar
        JButton ejecutarBtn = new JButton("Crear Usuario");
        ejecutarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreField.getText();
                    String correo = correoField.getText();
                    String tipo = (String) tipoCombo.getSelectedItem();
                    
                    CtrlAltaUsuario ctrl = CtrlAltaUsuario.getInstancia();
                    ctrl.altaUsuario(nombre, correo, tipo);
                    
                    if (tipo.equals("Lector")) {
                        String direccion = direccionField.getText();
                        DtFecha fechaRegistro = new DtFecha(
                            (Integer) diaSpinner.getValue(),
                            (Integer) mesSpinner.getValue(),
                            (Integer) anioSpinner.getValue()
                        );
                        EstadoLector estado = (EstadoLector) estadoCombo.getSelectedItem();
                        Zona zona = (Zona) zonaCombo.getSelectedItem();
                        ctrl.crearLector(direccion, fechaRegistro, estado, zona);
                    } else {
                        int numEmpleado = Integer.parseInt(numEmpleadoField.getText());
                        Zona zona = (Zona) zonaCombo.getSelectedItem();
                        ctrl.crearBibliotecario(numEmpleado, zona);
                    }
                    
                    outputArea.append("Usuario creado exitosamente: " + nombre + " (" + tipo + ")\n");
                } catch (Exception ex) {
                    outputArea.append("Error al crear usuario: " + ex.getMessage() + "\n");
                }
            }
        });
        
        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panel.add(nombreField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        panel.add(correoField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        panel.add(tipoCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        panel.add(direccionField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Número Empleado:"), gbc);
        gbc.gridx = 1;
        panel.add(numEmpleadoField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Zona:"), gbc);
        gbc.gridx = 1;
        panel.add(zonaCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        panel.add(estadoCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Fecha Registro (D/M/A):"), gbc);
        gbc.gridx = 1;
        JPanel fechaPanel = new JPanel();
        fechaPanel.add(diaSpinner);
        fechaPanel.add(new JLabel("/"));
        fechaPanel.add(mesSpinner);
        fechaPanel.add(new JLabel("/"));
        fechaPanel.add(anioSpinner);
        panel.add(fechaPanel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(ejecutarBtn, gbc);
        
        return panel;
    }
    
    private JPanel createAltaDonacionLibroPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JTextField tituloField = new JTextField(20);
        JTextField autorField = new JTextField(20);
        JSpinner cantPagSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
        
        JButton ejecutarBtn = new JButton("Registrar Donación Libro");
        ejecutarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = tituloField.getText();
                    String autor = autorField.getText();
                    int cantPag = (Integer) cantPagSpinner.getValue();
                    
                    CtrlAltaMaterial ctrl = CtrlAltaMaterial.getInstancia();
                    ctrl.altaDonacionLibro(titulo, autor, cantPag);
                    
                    outputArea.append("Donación de libro registrada exitosamente: " + titulo + "\n");
                } catch (Exception ex) {
                    outputArea.append("Error al registrar donación de libro: " + ex.getMessage() + "\n");
                }
            }
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        panel.add(tituloField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        panel.add(autorField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Cantidad de Páginas:"), gbc);
        gbc.gridx = 1;
        panel.add(cantPagSpinner, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(ejecutarBtn, gbc);
        
        return panel;
    }
    
    private JPanel createAltaDonacionMaterialPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JTextField nombreField = new JTextField(20);
        JTextField descripcionField = new JTextField(20);
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"DVD", "CD", "Revista", "Otro"});
        
        JButton ejecutarBtn = new JButton("Registrar Donación Material");
        ejecutarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreField.getText();
                    String descripcion = descripcionField.getText();
                    String tipo = (String) tipoCombo.getSelectedItem();
                    
                    CtrlAltaMaterial ctrl = CtrlAltaMaterial.getInstancia();
                    ctrl.altaDonacionMaterial(nombre, descripcion, tipo);
                    
                    outputArea.append("Donación de material registrada exitosamente: " + nombre + "\n");
                } catch (Exception ex) {
                    outputArea.append("Error al registrar donación: " + ex.getMessage() + "\n");
                }
            }
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panel.add(nombreField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        panel.add(descripcionField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        panel.add(tipoCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(ejecutarBtn, gbc);
        
        return panel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
