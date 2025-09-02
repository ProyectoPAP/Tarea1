package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBajas {
    
    public static void mostrarDialogoEliminarUsuario(JFrame parentFrame) {
        JDialog eliminarDialog = new JDialog(parentFrame, "Eliminar Usuario", false);
        // Calcular el 60% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.6);
        int frameHeight = (int) (parentFrame.getHeight() * 0.4);
        eliminarDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        eliminarDialog.setLocation(x, y);
        eliminarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        eliminarDialog.setAlwaysOnTop(true);
        eliminarDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Eliminar Usuario");
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

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(eliminarDialog, 
                        "Por favor ingrese el email del usuario a eliminar", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Confirmación antes de eliminar
                int confirmacion = JOptionPane.showConfirmDialog(eliminarDialog,
                    "¿Está seguro que desea eliminar el usuario con email: " + email + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                                 if (confirmacion == JOptionPane.YES_OPTION) {
                     // Aquí se llamaría a la lógica para eliminar el usuario
                     // TODO: Implementar llamada al controlador cuando esté disponible
                     JOptionPane.showMessageDialog(eliminarDialog, 
                         "Solicitud de eliminación procesada.\n" +
                         "Email del usuario: " + email + "\n\n" +
                         "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                         "Solicitud Procesada", JOptionPane.INFORMATION_MESSAGE);
                     eliminarDialog.dispose();
                 }
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarDialog.dispose();
            }
        });
        
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        eliminarDialog.add(panel);
        eliminarDialog.setVisible(true);
    }
    
    public static void mostrarDialogoEliminarMaterial(JFrame parentFrame) {
        JDialog eliminarDialog = new JDialog(parentFrame, "Eliminar Material", false);
        // Calcular el 60% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.6);
        int frameHeight = (int) (parentFrame.getHeight() * 0.4);
        eliminarDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        eliminarDialog.setLocation(x, y);
        eliminarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        eliminarDialog.setAlwaysOnTop(true);
        eliminarDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Eliminar Material");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // ID del material
        JLabel lblId = new JLabel("ID del material:");
        JTextField txtId = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblId, gbc);
        gbc.gridx = 1;
        panel.add(txtId, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText().trim();
                
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(eliminarDialog, 
                        "Por favor ingrese el ID del material a eliminar", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Confirmación antes de eliminar
                int confirmacion = JOptionPane.showConfirmDialog(eliminarDialog,
                    "¿Está seguro que desea eliminar el material con ID: " + id + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                                 if (confirmacion == JOptionPane.YES_OPTION) {
                     // Aquí se llamaría a la lógica para eliminar el material
                     // TODO: Implementar llamada al controlador cuando esté disponible
                     JOptionPane.showMessageDialog(eliminarDialog, 
                         "Solicitud de eliminación procesada.\n" +
                         "ID del material: " + id + "\n\n" +
                         "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                         "Solicitud Procesada", JOptionPane.INFORMATION_MESSAGE);
                     eliminarDialog.dispose();
                 }
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarDialog.dispose();
            }
        });
        
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        eliminarDialog.add(panel);
        eliminarDialog.setVisible(true);
    }
    
    public static void mostrarDialogoEliminarPrestamo(JFrame parentFrame) {
        JDialog eliminarDialog = new JDialog(parentFrame, "Eliminar Préstamo", false);
        // Calcular el 70% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.7);
        int frameHeight = (int) (parentFrame.getHeight() * 0.6);
        eliminarDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        eliminarDialog.setLocation(x, y);
        eliminarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        eliminarDialog.setAlwaysOnTop(true);
        eliminarDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Eliminar Préstamo");
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

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idMaterial = txtIdMaterial.getText().trim();
                String nombreLector = txtNombreLector.getText().trim();
                String idBibliotecario = txtIdBibliotecario.getText().trim();
                
                if (idMaterial.isEmpty()) {
                    JOptionPane.showMessageDialog(eliminarDialog, 
                        "Por favor ingrese el ID del material", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (nombreLector.isEmpty()) {
                    JOptionPane.showMessageDialog(eliminarDialog, 
                        "Por favor ingrese el nombre del lector", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (idBibliotecario.isEmpty()) {
                    JOptionPane.showMessageDialog(eliminarDialog, 
                        "Por favor ingrese el ID del bibliotecario", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Confirmación antes de eliminar
                int confirmacion = JOptionPane.showConfirmDialog(eliminarDialog,
                    "¿Está seguro que desea eliminar el préstamo con:\n" +
                    "ID Material: " + idMaterial + "\n" +
                    "Nombre Lector: " + nombreLector + "\n" +
                    "ID Bibliotecario: " + idBibliotecario + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                                 if (confirmacion == JOptionPane.YES_OPTION) {
                     // Aquí se llamaría a la lógica para eliminar el préstamo
                     // TODO: Implementar llamada al controlador cuando esté disponible
                     JOptionPane.showMessageDialog(eliminarDialog, 
                         "Solicitud de eliminación procesada.\n" +
                         "ID Material: " + idMaterial + "\n" +
                         "Nombre Lector: " + nombreLector + "\n" +
                         "ID Bibliotecario: " + idBibliotecario + "\n\n" +
                         "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                         "Solicitud Procesada", JOptionPane.INFORMATION_MESSAGE);
                     eliminarDialog.dispose();
                 }
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarDialog.dispose();
            }
        });
        
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        eliminarDialog.add(panel);
        eliminarDialog.setVisible(true);
    }
}
