package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import interfaces.ICtrlListarDonaciones;

public class ListarDonaciones extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlListarDonaciones ctrlListarDonaciones;
    
    private JSpinner spnFechaIni;
    private JSpinner spnFechaFin;
    private JButton btnListarTodas;
    private JButton btnListarPorFecha;
    private JButton btnLimpiar;
    private JTextArea txtResultado;
    private JScrollPane scrollPane;
    
    public ListarDonaciones(ICtrlListarDonaciones ctrlListarDonaciones) {
        this.ctrlListarDonaciones = ctrlListarDonaciones;
        
        setTitle("Listar Donaciones");
        setSize(500, 400);
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
        
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        
        scrollPane = new JScrollPane(txtResultado);
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
        
        // Área de resultado
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
            String[] donaciones = ctrlListarDonaciones.listarDonaciones();
            mostrarResultado(donaciones, "Todas las donaciones");
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
            String[] donaciones = ctrlListarDonaciones.listarDonacionesPorFecha(fechaIni, fechaFin);
            mostrarResultado(donaciones, "Donaciones del " + fechaIni + " al " + fechaFin);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al listar donaciones por fecha: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarResultado(String[] donaciones, String titulo) {
        StringBuilder sb = new StringBuilder();
        sb.append(titulo).append(":\n\n");
        
        if (donaciones == null || donaciones.length == 0) {
            sb.append("No se encontraron donaciones.");
        } else {
            for (int i = 0; i < donaciones.length; i++) {
                sb.append(i + 1).append(". ").append(donaciones[i]).append("\n");
            }
        }
        
        txtResultado.setText(sb.toString());
        txtResultado.setCaretPosition(0);
    }
    
    private void limpiarResultado() {
        txtResultado.setText("");
        spnFechaIni.setValue(new Date());
        spnFechaFin.setValue(new Date());
    }
}
