package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuModificaciones {
    
    public static void mostrarDialogoModificarZona(JFrame parentFrame) {
        JDialog modificarDialog = new JDialog(parentFrame, "Modificar Zona de Lector", false);
        // Calcular el 70% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.7);
        int frameHeight = (int) (parentFrame.getHeight() * 0.5);
        modificarDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        modificarDialog.setLocation(x, y);
        modificarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        modificarDialog.setAlwaysOnTop(true);
        modificarDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Modificar Zona de Lector");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Email del lector
        JLabel lblEmail = new JLabel("Email del lector:");
        JTextField txtEmail = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblEmail, gbc);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        // Nueva zona
        JLabel lblNuevaZona = new JLabel("Nueva zona:");
        String[] zonas = {"BIBLIOTECA_CENTRAL", "SUCURSAL_ESTE", "SUCURSAL_OESTE", "BIBLIOTECA_INFANTIL", "ARCHIVO_GENERAL"};
        JComboBox<String> cmbZona = new JComboBox<>(zonas);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNuevaZona, gbc);
        gbc.gridx = 1;
        panel.add(cmbZona, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnModificar = new JButton("Modificar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String nuevaZona = (String) cmbZona.getSelectedItem();
                
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese el email del lector", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                                 // Aquí se llamaría a la lógica para modificar la zona
                 // TODO: Implementar llamada al controlador cuando esté disponible
                 JOptionPane.showMessageDialog(modificarDialog, 
                     "Formulario completado correctamente.\n" +
                     "Datos ingresados:\n" +
                     "Email: " + email + "\n" +
                     "Nueva zona: " + nuevaZona + "\n\n" +
                     "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                     "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                modificarDialog.dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarDialog.dispose();
            }
        });
        
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        modificarDialog.add(panel);
        modificarDialog.setVisible(true);
    }
    
    public static void mostrarDialogoCambiarEstado(JFrame parentFrame) {
        JDialog modificarDialog = new JDialog(parentFrame, "Cambiar Estado de Usuario", false);
        // Calcular el 70% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.7);
        int frameHeight = (int) (parentFrame.getHeight() * 0.5);
        modificarDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        modificarDialog.setLocation(x, y);
        modificarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        modificarDialog.setAlwaysOnTop(true);
        modificarDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Cambiar Estado de Usuario");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Email del usuario
        JLabel lblEmail = new JLabel("Email del usuario:");
        JTextField txtEmail = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblEmail, gbc);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        // Nuevo estado
        JLabel lblNuevoEstado = new JLabel("Nuevo estado:");
        String[] estados = {"ACTIVO", "SUSPENDIDO"};
        JComboBox<String> cmbEstado = new JComboBox<>(estados);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNuevoEstado, gbc);
        gbc.gridx = 1;
        panel.add(cmbEstado, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnModificar = new JButton("Modificar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String nuevoEstado = (String) cmbEstado.getSelectedItem();
                
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese el email del usuario", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                                 // Aquí se llamaría a la lógica para cambiar el estado
                 // TODO: Implementar llamada al controlador cuando esté disponible
                 JOptionPane.showMessageDialog(modificarDialog, 
                     "Formulario completado correctamente.\n" +
                     "Datos ingresados:\n" +
                     "Email: " + email + "\n" +
                     "Nuevo estado: " + nuevoEstado + "\n\n" +
                     "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                     "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                modificarDialog.dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarDialog.dispose();
            }
        });
        
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        modificarDialog.add(panel);
        modificarDialog.setVisible(true);
    }
    
    public static void mostrarDialogoModificarEstadoPrestamo(JFrame parentFrame) {
        JDialog modificarDialog = new JDialog(parentFrame, "Modificar Estado de Préstamo", false);
        // Calcular el 80% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.8);
        int frameHeight = (int) (parentFrame.getHeight() * 0.7);
        modificarDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        modificarDialog.setLocation(x, y);
        modificarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        modificarDialog.setAlwaysOnTop(true);
        modificarDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Modificar Estado de Préstamo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Clave compuesta del préstamo
        JLabel lblIdMaterial = new JLabel("ID Material:");
        JTextField txtIdMaterial = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblIdMaterial, gbc);
        gbc.gridx = 1;
        panel.add(txtIdMaterial, gbc);

        JLabel lblNombreLector = new JLabel("Nombre Lector:");
        JTextField txtNombreLector = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNombreLector, gbc);
        gbc.gridx = 1;
        panel.add(txtNombreLector, gbc);

        JLabel lblIdBibliotecario = new JLabel("ID Bibliotecario:");
        JTextField txtIdBibliotecario = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblIdBibliotecario, gbc);
        gbc.gridx = 1;
        panel.add(txtIdBibliotecario, gbc);

        // Nuevo estado
        JLabel lblNuevoEstado = new JLabel("Nuevo estado:");
        String[] estados = {"PENDIENTE", "EN_CURSO", "DEVUELTO"};
        JComboBox<String> cmbEstado = new JComboBox<>(estados);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblNuevoEstado, gbc);
        gbc.gridx = 1;
        panel.add(cmbEstado, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnModificar = new JButton("Modificar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idMaterial = txtIdMaterial.getText().trim();
                String nombreLector = txtNombreLector.getText().trim();
                String idBibliotecario = txtIdBibliotecario.getText().trim();
                String nuevoEstado = (String) cmbEstado.getSelectedItem();
                
                if (idMaterial.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese el ID del material", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (nombreLector.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese el nombre del lector", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (idBibliotecario.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese el ID del bibliotecario", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                                 // Aquí se llamaría a la lógica para modificar el estado del préstamo
                 // TODO: Implementar llamada al controlador cuando esté disponible
                 JOptionPane.showMessageDialog(modificarDialog, 
                     "Formulario completado correctamente.\n" +
                     "Datos ingresados:\n" +
                     "ID Material: " + idMaterial + "\n" +
                     "Nombre Lector: " + nombreLector + "\n" +
                     "ID Bibliotecario: " + idBibliotecario + "\n" +
                     "Nuevo estado: " + nuevoEstado + "\n\n" +
                     "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                     "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                modificarDialog.dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarDialog.dispose();
            }
        });
        
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        modificarDialog.add(panel);
        modificarDialog.setVisible(true);
    }
    
    public static void mostrarDialogoModificarPrestamo(JFrame parentFrame) {
        JDialog modificarDialog = new JDialog(parentFrame, "Modificar Préstamo", false);
        // Calcular el 90% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.9);
        int frameHeight = (int) (parentFrame.getHeight() * 0.8);
        modificarDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        modificarDialog.setLocation(x, y);
        modificarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        modificarDialog.setAlwaysOnTop(true);
        modificarDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Modificar Préstamo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Clave compuesta del préstamo
        JLabel lblIdMaterial = new JLabel("ID Material:");
        JTextField txtIdMaterial = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblIdMaterial, gbc);
        gbc.gridx = 1;
        panel.add(txtIdMaterial, gbc);

        JLabel lblNombreLector = new JLabel("Nombre Lector:");
        JTextField txtNombreLector = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNombreLector, gbc);
        gbc.gridx = 1;
        panel.add(txtNombreLector, gbc);

        JLabel lblIdBibliotecario = new JLabel("ID Bibliotecario:");
        JTextField txtIdBibliotecario = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblIdBibliotecario, gbc);
        gbc.gridx = 1;
        panel.add(txtIdBibliotecario, gbc);

        // Nueva fecha de devolución
        JLabel lblFechaDevolucion = new JLabel("Nueva fecha de devolución:");
        JTextField txtFechaDevolucion = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblFechaDevolucion, gbc);
        gbc.gridx = 1;
        panel.add(txtFechaDevolucion, gbc);

        // Nuevo estado
        JLabel lblNuevoEstado = new JLabel("Nuevo estado:");
        String[] estados = {"PENDIENTE", "EN_CURSO", "DEVUELTO"};
        JComboBox<String> cmbEstado = new JComboBox<>(estados);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lblNuevoEstado, gbc);
        gbc.gridx = 1;
        panel.add(cmbEstado, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnModificar = new JButton("Modificar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idMaterial = txtIdMaterial.getText().trim();
                String nombreLector = txtNombreLector.getText().trim();
                String idBibliotecario = txtIdBibliotecario.getText().trim();
                String nuevaFecha = txtFechaDevolucion.getText().trim();
                String nuevoEstado = (String) cmbEstado.getSelectedItem();
                
                if (idMaterial.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese el ID del material", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (nombreLector.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese el nombre del lector", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (idBibliotecario.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese el ID del bibliotecario", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (nuevaFecha.isEmpty()) {
                    JOptionPane.showMessageDialog(modificarDialog, 
                        "Por favor ingrese la nueva fecha de devolución", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                                 // Aquí se llamaría a la lógica para modificar el préstamo
                 // TODO: Implementar llamada al controlador cuando esté disponible
                 JOptionPane.showMessageDialog(modificarDialog, 
                     "Formulario completado correctamente.\n" +
                     "Datos ingresados:\n" +
                     "ID Material: " + idMaterial + "\n" +
                     "Nombre Lector: " + nombreLector + "\n" +
                     "ID Bibliotecario: " + idBibliotecario + "\n" +
                     "Nueva fecha de devolución: " + nuevaFecha + "\n" +
                     "Nuevo estado: " + nuevoEstado + "\n\n" +
                     "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                     "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                modificarDialog.dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarDialog.dispose();
            }
        });
        
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        modificarDialog.add(panel);
        modificarDialog.setVisible(true);
    }
}
