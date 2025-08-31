package main.java.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MenuAltas {
    
    public static void mostrarDialogoAgregarLector(JFrame parentFrame) {
        JDialog userManagementDialog = new JDialog(parentFrame, "Agregar Lector", false);
        // Calcular el 80% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.8);
        int frameHeight = (int) (parentFrame.getHeight() * 0.8);
        userManagementDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        userManagementDialog.setLocation(x, y);
        userManagementDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        userManagementDialog.setAlwaysOnTop(true);
        userManagementDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Formulario para Agregar Lector");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblEmail, gbc);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        // Dirección
        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblDireccion, gbc);
        gbc.gridx = 1;
        panel.add(txtDireccion, gbc);

        // Estado
        JLabel lblEstado = new JLabel("Estado:");
        String[] estados = {"ACTIVO", "SUSPENDIDO"};
        JComboBox<String> cmbEstado = new JComboBox<>(estados);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lblEstado, gbc);
        gbc.gridx = 1;
        panel.add(cmbEstado, gbc);

        // Zona
        JLabel lblZona = new JLabel("Zona:");
        String[] zonas = {"BIBLIOTECA_CENTRAL", "SUCURSAL_ESTE", "SUCURSAL_OESTE", "BIBLIOTECA_INFANTIL", "ARCHIVO_GENERAL"};
        JComboBox<String> cmbZona = new JComboBox<>(zonas);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lblZona, gbc);
        gbc.gridx = 1;
        panel.add(cmbZona, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí se procesarían los datos del formulario
                String nombre = txtNombre.getText();
                String email = txtEmail.getText();
                String direccion = txtDireccion.getText();
                LocalDate today = LocalDate.now();
                String fechaRegistro = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String estado = (String) cmbEstado.getSelectedItem();
                String zona = (String) cmbZona.getSelectedItem();
                
                // Validación básica
                if (nombre.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(userManagementDialog, 
                        "Por favor complete todos los campos obligatorios", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                                 // Aquí se llamaría a la lógica para crear el lector
                 // TODO: Implementar llamada al controlador cuando esté disponible
                 JOptionPane.showMessageDialog(userManagementDialog, 
                     "Formulario completado correctamente.\n" +
                     "Datos ingresados:\n" +
                     "Nombre: " + nombre + "\n" +
                     "Email: " + email + "\n" +
                     "Dirección: " + direccion + "\n" +
                     "Fecha: " + fechaRegistro + "\n" +
                     "Estado: " + estado + "\n" +
                     "Zona: " + zona + "\n\n" +
                     "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                     "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                
                userManagementDialog.dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userManagementDialog.dispose();
            }
        });
        
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        userManagementDialog.add(panel);
        userManagementDialog.setVisible(true);
    }
    
    public static void mostrarDialogoAgregarBibliotecario(JFrame parentFrame) {
        JDialog userManagementDialog = new JDialog(parentFrame, "Agregar Bibliotecario", false);
        // Calcular el 80% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.8);
        int frameHeight = (int) (parentFrame.getHeight() * 0.8);
        userManagementDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        userManagementDialog.setLocation(x, y);
        userManagementDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        userManagementDialog.setAlwaysOnTop(true);
        userManagementDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Formulario para Agregar Bibliotecario");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblEmail, gbc);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        // Número de Empleado
        JLabel lblNumeroEmpleado = new JLabel("Número de Empleado:");
        JTextField txtNumeroEmpleado = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblNumeroEmpleado, gbc);
        gbc.gridx = 1;
        panel.add(txtNumeroEmpleado, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí se procesarían los datos del formulario
                String nombre = txtNombre.getText();
                String email = txtEmail.getText();
                String numeroEmpleado = txtNumeroEmpleado.getText();
                
                // Validación básica
                if (nombre.isEmpty() || email.isEmpty() || numeroEmpleado.isEmpty()) {
                    JOptionPane.showMessageDialog(userManagementDialog, 
                        "Por favor complete todos los campos obligatorios", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Validación de formato de email
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(userManagementDialog, 
                        "Por favor ingrese un email válido", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                                 // Aquí se llamaría a la lógica para crear el bibliotecario
                 // TODO: Implementar llamada al controlador cuando esté disponible
                 JOptionPane.showMessageDialog(userManagementDialog, 
                     "Formulario completado correctamente.\n" +
                     "Datos ingresados:\n" +
                     "Nombre: " + nombre + "\n" +
                     "Email: " + email + "\n" +
                     "Número de Empleado: " + numeroEmpleado + "\n\n" +
                     "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                     "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                
                userManagementDialog.dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userManagementDialog.dispose();
            }
        });
        
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        userManagementDialog.add(panel);
        userManagementDialog.setVisible(true);
    }
    
    public static void mostrarDialogoAgregarPrestamo(JFrame parentFrame) {
        JDialog prestamoDialog = new JDialog(parentFrame, "Agregar Préstamo", false);
        // Calcular el 80% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.8);
        int frameHeight = (int) (parentFrame.getHeight() * 0.8);
        prestamoDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        prestamoDialog.setLocation(x, y);
        prestamoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        prestamoDialog.setAlwaysOnTop(true);
        prestamoDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Formulario para Agregar Préstamo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // ID Material
        JLabel lblIdMaterial = new JLabel("ID Material:");
        JTextField txtIdMaterial = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblIdMaterial, gbc);
        gbc.gridx = 1;
        panel.add(txtIdMaterial, gbc);

        // Nombre Lector
        JLabel lblNombreLector = new JLabel("Nombre Lector:");
        JTextField txtNombreLector = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNombreLector, gbc);
        gbc.gridx = 1;
        panel.add(txtNombreLector, gbc);

        // ID Bibliotecario
        JLabel lblIdBibliotecario = new JLabel("Nombre Bibliotecario:");
        JTextField txtIdBibliotecario = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblIdBibliotecario, gbc);
        gbc.gridx = 1;
        panel.add(txtIdBibliotecario, gbc);

        // Fecha Solicitud
        JLabel lblFechaSolicitud = new JLabel("Fecha Solicitud:");
        JTextField txtFechaSolicitud = new JTextField(20);
        txtFechaSolicitud.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblFechaSolicitud, gbc);
        gbc.gridx = 1;
        panel.add(txtFechaSolicitud, gbc);

        // Fecha Devolución
        JLabel lblFechaDevolucion = new JLabel("Fecha Devolución:");
        JTextField txtFechaDevolucion = new JTextField(20);
        txtFechaDevolucion.setText(LocalDate.now().plusDays(15).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lblFechaDevolucion, gbc);
        gbc.gridx = 1;
        panel.add(txtFechaDevolucion, gbc);

        // Estado
        JLabel lblEstado = new JLabel("Estado:");
        String[] estados = {"PENDIENTE", "EN_CURSO", "DEVUELTO"};
        JComboBox<String> cmbEstado = new JComboBox<>(estados);
        cmbEstado.setSelectedItem("PENDIENTE");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lblEstado, gbc);
        gbc.gridx = 1;
        panel.add(cmbEstado, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar campos
                    if (txtIdMaterial.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(prestamoDialog, "El ID del material es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (txtNombreLector.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(prestamoDialog, "El nombre del lector es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (txtIdBibliotecario.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(prestamoDialog, "El ID del bibliotecario es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                                         // Aquí se llamaría al controlador para agregar el préstamo
                     // TODO: Implementar llamada al controlador cuando esté disponible
                     JOptionPane.showMessageDialog(prestamoDialog, 
                         "Formulario completado correctamente.\n" +
                         "Datos ingresados:\n" +
                         "ID Material: " + txtIdMaterial.getText() + "\n" +
                         "Nombre Lector: " + txtNombreLector.getText() + "\n" +
                         "ID Bibliotecario: " + txtIdBibliotecario.getText() + "\n" +
                         "Fecha Solicitud: " + txtFechaSolicitud.getText() + "\n" +
                         "Fecha Devolución: " + txtFechaDevolucion.getText() + "\n" +
                         "Estado: " + cmbEstado.getSelectedItem() + "\n\n" +
                         "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                         "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                    prestamoDialog.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(prestamoDialog, "Error al agregar préstamo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prestamoDialog.dispose();
            }
        });
        
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        prestamoDialog.add(panel);
        prestamoDialog.setVisible(true);
    }
    
    public static void mostrarDialogoAgregarArticulo(JFrame parentFrame) {
        JDialog materialDialog = new JDialog(parentFrame, "Agregar Artículo", false);
        // Calcular el 80% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.8);
        int frameHeight = (int) (parentFrame.getHeight() * 0.8);
        materialDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        materialDialog.setLocation(x, y);
        materialDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        materialDialog.setAlwaysOnTop(true);
        materialDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Formulario para Agregar Artículo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // ID
        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblId, gbc);
        gbc.gridx = 1;
        panel.add(txtId, gbc);

        // Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextField txtDescripcion = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblDescripcion, gbc);
        gbc.gridx = 1;
        panel.add(txtDescripcion, gbc);

        // Peso en kg
        JLabel lblPeso = new JLabel("Peso (kg):");
        JTextField txtPeso = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblPeso, gbc);
        gbc.gridx = 1;
        panel.add(txtPeso, gbc);

        // Dimensiones
        JLabel lblDimensiones = new JLabel("Dimensiones:");
        JTextField txtDimensiones = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblDimensiones, gbc);
        gbc.gridx = 1;
        panel.add(txtDimensiones, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí se procesarían los datos del formulario
                String id = txtId.getText();
                String descripcion = txtDescripcion.getText();
                String pesoStr = txtPeso.getText();
                String dimensiones = txtDimensiones.getText();
                
                // Validación básica
                if (id.isEmpty() || descripcion.isEmpty() || pesoStr.isEmpty() || dimensiones.isEmpty()) {
                    JOptionPane.showMessageDialog(materialDialog, 
                        "Por favor complete todos los campos obligatorios", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Validación de peso (debe ser un número)
                try {
                    float peso = Float.parseFloat(pesoStr);
                    if (peso <= 0) {
                        JOptionPane.showMessageDialog(materialDialog, 
                            "El peso debe ser un número positivo", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(materialDialog, 
                        "El peso debe ser un número válido", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                                 // Aquí se llamaría a la lógica para crear el artículo
                 // TODO: Implementar llamada al controlador cuando esté disponible
                 JOptionPane.showMessageDialog(materialDialog, 
                     "Formulario completado correctamente.\n" +
                     "Datos ingresados:\n" +
                     "ID: " + id + "\n" +
                     "Descripción: " + descripcion + "\n" +
                     "Peso: " + pesoStr + " kg\n" +
                     "Dimensiones: " + dimensiones + "\n\n" +
                     "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                     "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                
                materialDialog.dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                materialDialog.dispose();
            }
        });
        
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        materialDialog.add(panel);
        materialDialog.setVisible(true);
    }
    
    public static void mostrarDialogoAgregarLibro(JFrame parentFrame) {
        JDialog materialDialog = new JDialog(parentFrame, "Agregar Libro", false);
        // Calcular el 80% del tamaño del frame principal
        int frameWidth = (int) (parentFrame.getWidth() * 0.8);
        int frameHeight = (int) (parentFrame.getHeight() * 0.8);
        materialDialog.setSize(frameWidth, frameHeight);
        // Centrar el frame con respecto al frame principal
        int x = parentFrame.getX() + (parentFrame.getWidth() - frameWidth) / 2;
        int y = parentFrame.getY() + (parentFrame.getHeight() - frameHeight) / 2;
        materialDialog.setLocation(x, y);
        materialDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        materialDialog.setAlwaysOnTop(true);
        materialDialog.setModal(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel lblTitulo = new JLabel("Formulario para Agregar Libro");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        // ID
        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblId, gbc);
        gbc.gridx = 1;
        panel.add(txtId, gbc);

        // Título del libro
        JLabel lblTituloLibro = new JLabel("Título:");
        JTextField txtTituloLibro = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblTituloLibro, gbc);
        gbc.gridx = 1;
        panel.add(txtTituloLibro, gbc);

        // Cantidad de páginas
        JLabel lblPaginas = new JLabel("Cantidad de Páginas:");
        JTextField txtPaginas = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblPaginas, gbc);
        gbc.gridx = 1;
        panel.add(txtPaginas, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí se procesarían los datos del formulario
                String id = txtId.getText();
                String titulo = txtTituloLibro.getText();
                String paginas = txtPaginas.getText();
                
                // Validación básica
                if (id.isEmpty() || titulo.isEmpty() || paginas.isEmpty()) {
                    JOptionPane.showMessageDialog(materialDialog, 
                        "Por favor complete todos los campos obligatorios", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Validación de páginas (debe ser un número)
                try {
                    int numPaginas = Integer.parseInt(paginas);
                    if (numPaginas <= 0) {
                        JOptionPane.showMessageDialog(materialDialog, 
                            "La cantidad de páginas debe ser un número positivo", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(materialDialog, 
                        "La cantidad de páginas debe ser un número válido", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                                 // Aquí se llamaría a la lógica para crear el libro
                 // TODO: Implementar llamada al controlador cuando esté disponible
                 JOptionPane.showMessageDialog(materialDialog, 
                     "Formulario completado correctamente.\n" +
                     "Datos ingresados:\n" +
                     "ID: " + id + "\n" +
                     "Título: " + titulo + "\n" +
                     "Páginas: " + paginas + "\n\n" +
                     "Nota: La lógica de negocio será implementada por otros desarrolladores.", 
                     "Formulario Completado", JOptionPane.INFORMATION_MESSAGE);
                
                materialDialog.dispose();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                materialDialog.dispose();
            }
        });
        
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        materialDialog.add(panel);
        materialDialog.setVisible(true);
    }
}
