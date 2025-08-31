package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.presentacion.MenuAltas;
import main.java.presentacion.MenuBajas;
import main.java.presentacion.MenuModificaciones;


public class Principal {
    private JFrame frame;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu menuArchivo;
    private JMenu menuGestion;
    private JMenu menuGestionUsuarios;
    private JMenu menuGestionPrestamos;
    private JMenu menuGestionMaterial;
    private JMenu menuReportes;
    private JMenuItem menuItemSalir;
    private JMenuItem menuItemInicio;
    private JMenuItem menuItemTopPrestados;
    private JMenuItem menuItemPrestamosBibliotecarios;
    private JMenuItem menuItemPrestamosPorZonas;
    private JMenuItem menuItemListarDonaciones;
    private JMenuItem menuItemListarPrestamosPorLector;
    private JMenu menuAgregarUsuario;
    private JMenu menuModificarUsuario;
    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemListarUsuarios;
    private JMenuItem menuItemAgregarLector;
    private JMenuItem menuItemAgregarBibliotecario;
    private JMenuItem menuItemAgregarPrestamo;
    private JMenuItem menuItemCambiarEstado;
    private JMenu menuModificarPrestamo;
    private JMenuItem menuItemEliminarPrestamo;
    private JMenuItem menuItemListarPrestamos;
    private JMenuItem menuItemModificarZona;
    private JMenuItem menuItemModificarEstado;
    private JMenuItem menuItemModificarPrestamo;
    private JMenu menuAgregarMaterial;
    private JMenuItem menuItemAgregarArticulo;
    private JMenuItem menuItemAgregarLibro;
    private JMenuItem menuItemEliminarMaterial;
    private JMenuItem menuItemListarMaterial;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        initialize();
    }

    private void initialize() {
        // Crear el frame principal
        frame = new JFrame("Sistema de Gestión de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Obtener la resolución del monitor
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.75);
        int height = (int) (screenSize.getHeight() * 0.75);
        
        // Centrar la ventana en la pantalla
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        
        frame.setBounds(x, y, width, height);
        frame.setMinimumSize(new Dimension(800, 600));
        
        // Crear el menú
        createMenuBar();
        
        // Crear el panel principal
        createMainPanel();
        
        frame.setContentPane(mainPanel);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        
        // Menú Archivo
        menuArchivo = new JMenu("Archivo");
        menuItemInicio = new JMenuItem("Inicio");
        menuItemInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Cierra todos los frames excepto el principal
            Frame[] frames = Frame.getFrames();
            for (Frame f : frames) {
                if (f != null && f.isDisplayable() && f != frame) {
                    f.dispose();
                }
            }
            }
        });
        menuArchivo.add(menuItemInicio);
        menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuArchivo.add(menuItemSalir);
        
        // Menú Gestión
        menuGestion = new JMenu("Gestión");
        
        // Submenú Gestión Usuarios
        menuGestionUsuarios = new JMenu("Gestión Usuarios");
        menuAgregarUsuario = new JMenu("Agregar Usuario");
        menuModificarUsuario = new JMenu("Modificar Usuario");
        menuItemEliminarUsuario = new JMenuItem("Eliminar Usuario");
        menuItemListarUsuarios = new JMenuItem("Listar Usuarios");
        
        // Crear submenú Agregar Usuario
        menuItemAgregarLector = new JMenuItem("Agregar Lector");
        menuItemAgregarBibliotecario = new JMenuItem("Agregar Bibliotecario");
        
        menuItemAgregarLector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuAltas.mostrarDialogoAgregarLector(frame);
            }
        });
        
        menuItemAgregarBibliotecario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuAltas.mostrarDialogoAgregarBibliotecario(frame);
            }
        });
        
        // Agregar items al submenú Agregar Usuario
        menuAgregarUsuario.add(menuItemAgregarLector);
        menuAgregarUsuario.add(menuItemAgregarBibliotecario);
        
        // Crear submenú Modificar Usuario
        menuItemModificarZona = new JMenuItem("Modificar Zona");
        menuItemModificarZona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuModificaciones.mostrarDialogoModificarZona(frame);
            }
        });
        menuModificarUsuario.add(menuItemModificarZona);

        //Crear opcion para cambiar estado de usuario
        menuItemCambiarEstado = new JMenuItem("Cambiar Estado");
        menuItemCambiarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuModificaciones.mostrarDialogoCambiarEstado(frame);
            }
        });
        menuModificarUsuario.add(menuItemCambiarEstado);
        
        menuItemEliminarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuBajas.mostrarDialogoEliminarUsuario(frame);
            }
        });
        
        menuItemListarUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de listar usuarios");
            }
        });
        
        // Agregar items al submenú Gestión Usuarios
        menuGestionUsuarios.add(menuAgregarUsuario);
        menuGestionUsuarios.add(menuModificarUsuario);
        menuGestionUsuarios.add(menuItemEliminarUsuario);
        menuGestionUsuarios.add(menuItemListarUsuarios);
        
        // Submenú Gestión Préstamos
        menuGestionPrestamos = new JMenu("Gestión Préstamos");
        menuItemAgregarPrestamo = new JMenuItem("Agregar Préstamo");
        menuModificarPrestamo = new JMenu("Modificar Préstamo");
        menuItemEliminarPrestamo = new JMenuItem("Eliminar Préstamo");
        menuItemListarPrestamos = new JMenuItem("Listar Préstamos");
        
        menuItemAgregarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuAltas.mostrarDialogoAgregarPrestamo(frame);
            }
        });
        
        // Crear submenú Modificar Préstamo
        menuItemModificarEstado = new JMenuItem("Modificar Estado");
        menuItemModificarPrestamo = new JMenuItem("Modificar Préstamo");
        
        menuItemModificarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuModificaciones.mostrarDialogoModificarEstadoPrestamo(frame);
            }
        });
        
        menuItemModificarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuModificaciones.mostrarDialogoModificarPrestamo(frame);
            }
        });
        
        menuModificarPrestamo.add(menuItemModificarEstado);
        menuModificarPrestamo.add(menuItemModificarPrestamo);
        
        menuItemEliminarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuBajas.mostrarDialogoEliminarPrestamo(frame);
            }
        });
        
        menuItemListarPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de listar préstamos");
            }
        });
        
        // Agregar items al submenú Gestión Préstamos
        menuGestionPrestamos.add(menuItemAgregarPrestamo);
        menuGestionPrestamos.add(menuModificarPrestamo);
        menuGestionPrestamos.add(menuItemEliminarPrestamo);
        menuGestionPrestamos.add(menuItemListarPrestamos);
        
        // Submenú Gestión Material
        menuGestionMaterial = new JMenu("Gestión Material");
        menuAgregarMaterial = new JMenu("Agregar Material");
        menuItemEliminarMaterial = new JMenuItem("Eliminar Material");
        menuItemListarMaterial = new JMenuItem("Listar Material");
        
        // Crear submenú Agregar Material
        menuItemAgregarArticulo = new JMenuItem("Agregar Artículo");
        menuItemAgregarLibro = new JMenuItem("Agregar Libro");
        
        menuItemAgregarArticulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuAltas.mostrarDialogoAgregarArticulo(frame);
            }
        });
        
        menuItemAgregarLibro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuAltas.mostrarDialogoAgregarLibro(frame);
            }
        });
        
        // Agregar items al submenú Agregar Material
        menuAgregarMaterial.add(menuItemAgregarArticulo);
        menuAgregarMaterial.add(menuItemAgregarLibro);
        
        menuItemEliminarMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuBajas.mostrarDialogoEliminarMaterial(frame);
            }
        });
        
        menuItemListarMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de listar material");
            }
        });
        
        // Agregar items al submenú Gestión Material
        menuGestionMaterial.add(menuAgregarMaterial);
        menuGestionMaterial.add(menuItemEliminarMaterial);
        menuGestionMaterial.add(menuItemListarMaterial);
        
        // Agregar submenús al menú Gestión
        menuGestion.add(menuGestionUsuarios);
        menuGestion.add(menuGestionPrestamos);
        menuGestion.add(menuGestionMaterial);
        
        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);
        
        // Crear menú de reportes
        createReportMenu();
        
        frame.setJMenuBar(menuBar);
    }

    //Menu Reportes
    private void createReportMenu() {
        menuReportes = new JMenu("Reportes");
        menuItemTopPrestados = new JMenuItem("Listar Materiales más prestados");
        menuItemTopPrestados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de generación de reportes");
            }
        });
        menuItemPrestamosBibliotecarios = new JMenuItem("Listar Préstamos de Bibliotecarios");
        menuItemPrestamosBibliotecarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de generación de reportes");
            }
        });
        menuItemPrestamosPorZonas = new JMenuItem("Listar Préstamos por Zonas");
        menuItemPrestamosPorZonas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de generación de reportes");
            }
        });
        menuItemListarDonaciones = new JMenuItem("Listar Donaciones");
        menuItemListarDonaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de generación de reportes");
            }
        });
        menuItemListarPrestamosPorLector = new JMenuItem("Listar Préstamos Activos por Lector");
        menuItemListarPrestamosPorLector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de generación de reportes");
            }
        });
        menuReportes.add(menuItemTopPrestados);
        menuReportes.add(menuItemPrestamosBibliotecarios);
        menuReportes.add(menuItemPrestamosPorZonas);
        menuReportes.add(menuItemListarDonaciones);
        menuReportes.add(menuItemListarPrestamosPorLector);
        menuBar.add(menuReportes);
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Panel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("SISTEMA DE GESTIÓN DE BIBLIOTECA");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Panel central con botones
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 240));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        
             
        // Panel de estado
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(new Color(220, 220, 220));
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        JLabel statusLabel = new JLabel("Sistema listo");
        statusPanel.add(statusLabel);
        
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
    }
}
