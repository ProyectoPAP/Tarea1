package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {
    private JFrame frame;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu menuArchivo;
    private JMenu menuGestion;
    private JMenu menuReportes;
    private JMenuItem menuItemSalir;
    private JMenuItem menuItemUsuarios;
    private JMenuItem menuItemPrestamos;
    private JMenuItem menuItemInicio;
    private JMenuItem menuItemTopPrestados;
    private JMenuItem menuItemPrestamosBibliotecarios;
    private JMenuItem menuItemPrestamosPorZonas;
    

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
        menuItemUsuarios = new JMenuItem("Gestionar Usuarios");
        menuItemPrestamos = new JMenuItem("Gestionar Préstamos");
        menuItemUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            JDialog userManagementDialog = new JDialog(frame, "Gestión de Usuarios", false);
            // Calcular el 80% del tamaño del frame principal
            int frameWidth = (int) (frame.getWidth() * 0.8);
            int frameHeight = (int) (frame.getHeight() * 0.8);
            userManagementDialog.setSize(frameWidth, frameHeight);
            // Centrar el frame con respecto al frame principal
            int x = frame.getX() + (frame.getWidth() - frameWidth) / 2;
            int y = frame.getY() + (frame.getHeight() - frameHeight) / 2;
            userManagementDialog.setLocation(x, y);
            userManagementDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            userManagementDialog.setAlwaysOnTop(true);
            userManagementDialog.setModal(false);

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            JButton btnAgregar = new JButton("Agregar Usuario");
            JButton btnModificar = new JButton("Modificar Usuario");
            JButton btnEliminar = new JButton("Eliminar Usuario");
            JButton btnListar = new JButton("Listar Usuarios");

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(btnAgregar, gbc);

            gbc.gridy = 1;
            panel.add(btnModificar, gbc);

            gbc.gridy = 2;
            panel.add(btnEliminar, gbc);

            gbc.gridy = 3;
            panel.add(btnListar, gbc);

            userManagementDialog.add(panel);
            userManagementDialog.setVisible(true);
            }
        });
        
        menuItemPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de gestión de préstamos");
            }
        });
        
        menuGestion.add(menuItemUsuarios);
        menuGestion.add(menuItemPrestamos);
        
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
        menuReportes.add(menuItemTopPrestados);
        menuReportes.add(menuItemPrestamosBibliotecarios);
        menuReportes.add(menuItemPrestamosPorZonas);
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
        
        JButton btnUsuarios = new JButton("Gestionar Usuarios");
        btnUsuarios.setPreferredSize(new Dimension(200, 50));
        btnUsuarios.setFont(new Font("Arial", Font.BOLD, 14));
        btnUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog userManagementDialog = new JDialog(frame, "Gestión de Usuarios", false);
                // Calcular el 80% del tamaño del frame principal
                int frameWidth = (int) (frame.getWidth() * 0.8);
                int frameHeight = (int) (frame.getHeight() * 0.8);
                userManagementDialog.setSize(frameWidth, frameHeight);
                // Centrar el frame con respecto al frame principal
                int x = frame.getX() + (frame.getWidth() - frameWidth) / 2;
                int y = frame.getY() + (frame.getHeight() - frameHeight) / 2;
                userManagementDialog.setLocation(x, y);
                userManagementDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                userManagementDialog.setAlwaysOnTop(true);
                userManagementDialog.setModal(false);

                JPanel panel = new JPanel();
                panel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);

                JButton btnAgregar = new JButton("Agregar Usuario");
                JButton btnModificar = new JButton("Modificar Usuario");
                JButton btnEliminar = new JButton("Eliminar Usuario");
                JButton btnListar = new JButton("Listar Usuarios");

                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(btnAgregar, gbc);

                gbc.gridy = 1;
                panel.add(btnModificar, gbc);

                gbc.gridy = 2;
                panel.add(btnEliminar, gbc);

                gbc.gridy = 3;
                panel.add(btnListar, gbc);

                userManagementDialog.add(panel);
                userManagementDialog.setVisible(true);
            }
        });
        
        JButton btnPrestamos = new JButton("Gestionar Préstamos");
        btnPrestamos.setPreferredSize(new Dimension(200, 50));
        btnPrestamos.setFont(new Font("Arial", Font.BOLD, 14));
        btnPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de gestión de préstamos");
            }
        });
        
        JButton btnReportes = new JButton("Generar Reportes");
        btnReportes.setPreferredSize(new Dimension(200, 50));
        btnReportes.setFont(new Font("Arial", Font.BOLD, 14));
        btnReportes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Función de generación de reportes");
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(btnUsuarios, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(btnPrestamos, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(btnReportes, gbc);
        
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
