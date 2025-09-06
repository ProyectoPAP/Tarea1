package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import interfaces.ICtrlAltaPrestamo;
import interfaces.ICtrlAltaUsr;
import interfaces.ICtrlAltaMaterial;
import logica.ManejadorUsuario;
import logica.ManejadorMaterial;
import logica.Usuario;
import logica.Lector;
import logica.Bibliotecario;
import logica.Material;
import logica.Libro;
import logica.Articulo;

public class AgregarPrestamo extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    private ICtrlAltaPrestamo ctrlAltaPrestamo;
    private ICtrlAltaUsr ctrlAltaUsr;
    private ICtrlAltaMaterial ctrlAltaMaterial;
    
    // Componentes de selección
    private JComboBox<String> cmbLector;
    private JComboBox<String> cmbBibliotecario;
    private JComboBox<String> cmbMaterial;
    
    // Tabla de materiales disponibles
    private JTable tablaMateriales;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    
    // Botones
    private JButton btnAgregarPrestamo;
    private JButton btnLimpiar;
    private JButton btnActualizarTabla;
    
    // Listas de datos
    private ArrayList<String> lectores;
    private ArrayList<String> bibliotecarios;
    private ArrayList<String> materiales;
    
    public AgregarPrestamo(ICtrlAltaPrestamo ctrlAltaPrestamo, ICtrlAltaUsr ctrlAltaUsr, ICtrlAltaMaterial ctrlAltaMaterial) {
        this.ctrlAltaPrestamo = ctrlAltaPrestamo;
        this.ctrlAltaUsr = ctrlAltaUsr;
        this.ctrlAltaMaterial = ctrlAltaMaterial;
        
        setTitle("Agregar Préstamo");
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
        // Combo boxes para selección
        cmbLector = new JComboBox<>();
        cmbBibliotecario = new JComboBox<>();
        cmbMaterial = new JComboBox<>();
        
        // Tabla de materiales
        String[] columnas = {"ID", "Tipo", "Fecha Ingreso", "Disponible", "Información"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        
        tablaMateriales = new JTable(modeloTabla);
        tablaMateriales.setRowHeight(25);
        tablaMateriales.getTableHeader().setReorderingAllowed(false);
        
        scrollPane = new JScrollPane(tablaMateriales);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        // Botones
        btnAgregarPrestamo = new JButton("Crear Préstamo");
        btnLimpiar = new JButton("Limpiar");
        btnActualizarTabla = new JButton("Actualizar Tabla");
        
        // Inicializar listas
        lectores = new ArrayList<>();
        bibliotecarios = new ArrayList<>();
        materiales = new ArrayList<>();
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
        add(new JLabel("Formulario de Alta de Préstamo"), gbc);
        
        // Selección de Lector
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Lector (Email):"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(cmbLector, gbc);
        
        // Selección de Bibliotecario
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Bibliotecario (Email):"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(cmbBibliotecario, gbc);
        
        // Selección de Material
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Material (ID):"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(cmbMaterial, gbc);
        
        // Botones de acción
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnLimpiar, gbc);
        
        gbc.gridx = 1;
        add(btnActualizarTabla, gbc);
        
        gbc.gridx = 2;
        add(btnAgregarPrestamo, gbc);
        
        // Tabla de materiales
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);
    }
    
    private void setupListeners() {
        // Listener para la tabla - cuando se hace clic en una fila, seleccionar el material
        tablaMateriales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tablaMateriales.getSelectedRow();
                if (row >= 0) {
                    String idMaterial = (String) tablaMateriales.getValueAt(row, 0);
                    cmbMaterial.setSelectedItem(idMaterial);
                }
            }
        });
        
        btnAgregarPrestamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPrestamo();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
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
            // Cargar lectores
            ManejadorUsuario mU = ManejadorUsuario.getInstancia();
            ArrayList<String> emailsUsuarios = mU.obtenerUsuarios();
            
            lectores.clear();
            bibliotecarios.clear();
            cmbLector.removeAllItems();
            cmbBibliotecario.removeAllItems();
            
            for (String email : emailsUsuarios) {
                Usuario usuario = mU.buscarUsuario(email);
                if (usuario instanceof Lector) {
                    lectores.add(email);
                    cmbLector.addItem(email);
                } else if (usuario instanceof Bibliotecario) {
                    bibliotecarios.add(email);
                    cmbBibliotecario.addItem(email);
                }
            }
            
            // Cargar materiales
            ManejadorMaterial mM = ManejadorMaterial.getInstancia();
            ArrayList<String> idsMateriales = mM.obtenerMateriales();
            
            materiales.clear();
            cmbMaterial.removeAllItems();
            
            for (String id : idsMateriales) {
                materiales.add(id);
                cmbMaterial.addItem(id);
            }
            
            // Actualizar tabla
            actualizarTablaMateriales();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar datos: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarTablaMateriales() {
        modeloTabla.setRowCount(0);
        
        try {
            ManejadorMaterial mM = ManejadorMaterial.getInstancia();
            
            for (String idMaterial : materiales) {
                Material material = mM.buscarMaterial(idMaterial);
                if (material != null) {
                    String tipo = obtenerTipoMaterial(material);
                    String fechaIngreso = new java.text.SimpleDateFormat("dd/MM/yyyy").format(material.getFechaIngreso());
                    String infoAdicional = obtenerInformacionAdicional(material);
                    
                    Object[] fila = {
                        idMaterial, 
                        tipo, 
                        fechaIngreso, 
                        infoAdicional
                    };
                    modeloTabla.addRow(fila);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al actualizar tabla: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String obtenerTipoMaterial(Material material) {
        if (material instanceof Libro) {
            return "Libro";
        } else if (material instanceof Articulo) {
            return "Artículo";
        } else {
            return "Material";
        }
    }
    
    private String obtenerInformacionAdicional(Material material) {
        if (material instanceof Libro) {
            Libro libro = (Libro) material;
            return "Título: " + libro.getTitulo() + " | Páginas: " + libro.getCantidadPaginas();
        } else if (material instanceof Articulo) {
            Articulo articulo = (Articulo) material;
            return "Descripción: " + articulo.getDescripcion() + " | Peso: " + articulo.getPesoKg() + " kg";
        } else {
            return "Sin información adicional";
        }
    }
    
    private void crearPrestamo() {
        String emailLector = (String) cmbLector.getSelectedItem();
        String emailBibliotecario = (String) cmbBibliotecario.getSelectedItem();
        String idMaterial = (String) cmbMaterial.getSelectedItem();
        
        if (emailLector == null || emailBibliotecario == null || idMaterial == null) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione lector, bibliotecario y material", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            ctrlAltaPrestamo.altaPrestamo(emailLector, emailBibliotecario, idMaterial);
            
            JOptionPane.showMessageDialog(this, 
                "Préstamo creado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();
            actualizarTablaMateriales(); // Actualizar disponibilidad
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al crear préstamo: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        cmbLector.setSelectedIndex(-1);
        cmbBibliotecario.setSelectedIndex(-1);
        cmbMaterial.setSelectedIndex(-1);
    }
}
