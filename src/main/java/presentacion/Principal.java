package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import interfaces.Fabrica;
import interfaces.ICtrlAltaUsr;
import interfaces.ICtrlListarDonaciones;
import interfaces.ICtrlAltaMaterial;
import interfaces.ICtrlAltaPrestamo;
import interfaces.ICtrlListarPrestamos;
import interfaces.ICtrlCambiarZonaLector;
import interfaces.ICtrlSuspenderUsuario;
import interfaces.ICtrlListarPrestamosBibliotecario;
import interfaces.ICtrlListarPrestamosPorZona;
import interfaces.ICtrlListarCantidadVecesPrestados;
import interfaces.ICtrlModPrestamo;
import interfaces.ICtrlListarPrestActXLector;
import presentacion.AgregarPrestamo;
import presentacion.ListarPrestamos;
import presentacion.CambiarEstadoPrestamo;
import presentacion.ModPrestamo;
import presentacion.ListarPrestamosActivosLector;

public class Principal {
    private JFrame frame;

    private AgregarUsuario agregarUsuarioInternalFrame;
    private AgregarMaterial agregarMaterialInternalFrame;
    private ListarDonaciones listarDonacionesInternalFrame;
    private AgregarPrestamo agregarPrestamoInternalFrame;
    private ListarPrestamos listarPrestamosInternalFrame;
    private CambiarZonaLector cambiarZonaLectorInternalFrame;
    private SuspenderUsuario suspenderUsuarioInternalFrame;
    private ListarPrestamosBibliotecario listarPrestamosBibliotecarioInternalFrame;
    private ListarPrestamosPorZona listarPrestamosPorZonaInternalFrame;
    private ListarCantidadVecesPrestados listarCantidadVecesPrestadosInternalFrame;
    private CambiarEstadoPrestamo cambiarEstadoPrestamoInternalFrame;
    private ModPrestamo modPrestamoInternalFrame;
    private ListarPrestamosActivosLector listarPrestamosActivosLectorInternalFrame;

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

        Fabrica fabrica = Fabrica.getInstancia();
        ICtrlAltaUsr iCtrlAltaUsr = fabrica.getCtrlAltaUsr();
        ICtrlAltaMaterial iCtrlAltaMaterial = fabrica.getCtrlAltaMaterial();
        ICtrlListarDonaciones iCtrlListarDonaciones = fabrica.getCtrlListarDonaciones();
        ICtrlAltaPrestamo iCtrlAltaPrestamo = fabrica.getCtrlAltaPrestamo();
        ICtrlListarPrestamos iCtrlListarPrestamos = fabrica.getCtrlListarPrestamos();
        ICtrlCambiarZonaLector iCtrlCambiarZonaLector = fabrica.getCtrlCambiarZonaLector();
        ICtrlSuspenderUsuario iCtrlSuspenderUsuario = fabrica.getCtrlSuspenderUsuario();
        ICtrlListarPrestamosBibliotecario iCtrlListarPrestamosBibliotecario = fabrica.getCtrlListarPrestamosBibliotecario();
        ICtrlListarPrestamosPorZona iCtrlListarPrestamosPorZona = fabrica.getCtrlListarPrestamosPorZona();
        ICtrlListarCantidadVecesPrestados iCtrlListarCantidadVecesPrestados = fabrica.getCtrlListarCantidadVecesPrestados();
        
        ICtrlModPrestamo iCtrlModPrestamo = fabrica.getCtrlModPrestamo();
        ICtrlListarPrestActXLector iCtrlListarPrestActXLector = fabrica.getCtrlListarPrestActXLector();

        Dimension desktopSize = frame.getSize();
        Dimension jInternalFrameSize;

        agregarUsuarioInternalFrame = new AgregarUsuario(iCtrlAltaUsr);
		jInternalFrameSize = agregarUsuarioInternalFrame.getSize();
		agregarUsuarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		agregarUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarUsuarioInternalFrame);

        agregarMaterialInternalFrame = new AgregarMaterial(iCtrlAltaMaterial);
		jInternalFrameSize = agregarMaterialInternalFrame.getSize();
		agregarMaterialInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		agregarMaterialInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarMaterialInternalFrame);

        listarDonacionesInternalFrame = new ListarDonaciones(iCtrlListarDonaciones);
		jInternalFrameSize = listarDonacionesInternalFrame.getSize();
		listarDonacionesInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		listarDonacionesInternalFrame.setVisible(false);
		frame.getContentPane().add(listarDonacionesInternalFrame);

        agregarPrestamoInternalFrame = new AgregarPrestamo(iCtrlAltaPrestamo, iCtrlAltaUsr, iCtrlAltaMaterial);
		jInternalFrameSize = agregarPrestamoInternalFrame.getSize();
		agregarPrestamoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		agregarPrestamoInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarPrestamoInternalFrame);

        listarPrestamosInternalFrame = new ListarPrestamos(iCtrlListarPrestamos);
		jInternalFrameSize = listarPrestamosInternalFrame.getSize();
		listarPrestamosInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		listarPrestamosInternalFrame.setVisible(false);
		frame.getContentPane().add(listarPrestamosInternalFrame);

        cambiarZonaLectorInternalFrame = new CambiarZonaLector(iCtrlCambiarZonaLector);
		jInternalFrameSize = cambiarZonaLectorInternalFrame.getSize();
		cambiarZonaLectorInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		cambiarZonaLectorInternalFrame.setVisible(false);
		frame.getContentPane().add(cambiarZonaLectorInternalFrame);

        suspenderUsuarioInternalFrame = new SuspenderUsuario(iCtrlSuspenderUsuario);
		jInternalFrameSize = suspenderUsuarioInternalFrame.getSize();
		suspenderUsuarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		suspenderUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(suspenderUsuarioInternalFrame);

        listarPrestamosBibliotecarioInternalFrame = new ListarPrestamosBibliotecario(iCtrlListarPrestamosBibliotecario);
		jInternalFrameSize = listarPrestamosBibliotecarioInternalFrame.getSize();
		listarPrestamosBibliotecarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		listarPrestamosBibliotecarioInternalFrame.setVisible(false);
		frame.getContentPane().add(listarPrestamosBibliotecarioInternalFrame);

        listarPrestamosPorZonaInternalFrame = new ListarPrestamosPorZona(iCtrlListarPrestamosPorZona);
		jInternalFrameSize = listarPrestamosPorZonaInternalFrame.getSize();
		listarPrestamosPorZonaInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		listarPrestamosPorZonaInternalFrame.setVisible(false);
		frame.getContentPane().add(listarPrestamosPorZonaInternalFrame);

        listarCantidadVecesPrestadosInternalFrame = new ListarCantidadVecesPrestados(iCtrlListarCantidadVecesPrestados);
		jInternalFrameSize = listarCantidadVecesPrestadosInternalFrame.getSize();
		listarCantidadVecesPrestadosInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		listarCantidadVecesPrestadosInternalFrame.setVisible(false);
		frame.getContentPane().add(listarCantidadVecesPrestadosInternalFrame);
        cambiarEstadoPrestamoInternalFrame = new CambiarEstadoPrestamo(iCtrlModPrestamo);
		jInternalFrameSize = cambiarEstadoPrestamoInternalFrame.getSize();
		cambiarEstadoPrestamoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		cambiarEstadoPrestamoInternalFrame.setVisible(false);
		frame.getContentPane().add(cambiarEstadoPrestamoInternalFrame);

        modPrestamoInternalFrame = new ModPrestamo(iCtrlModPrestamo);

        listarPrestamosActivosLectorInternalFrame = new ListarPrestamosActivosLector(iCtrlListarPrestActXLector);
		jInternalFrameSize = listarPrestamosActivosLectorInternalFrame.getSize();
		listarPrestamosActivosLectorInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
		    (desktopSize.height- jInternalFrameSize.height)/2);
		listarPrestamosActivosLectorInternalFrame.setVisible(false);
		frame.getContentPane().add(listarPrestamosActivosLectorInternalFrame);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnAltas = new JMenu("Altas");
        menuBar.add(mnAltas);

        JMenuItem mntmAgregarUsuario = new JMenuItem("Agregar Usuario");
        mntmAgregarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarUsuarioInternalFrame.setVisible(true);
            }
        });
        mnAltas.add(mntmAgregarUsuario);

        JMenuItem mntmAgregarMaterial = new JMenuItem("Agregar Material");
        mntmAgregarMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarMaterialInternalFrame.setVisible(true);
            }
        });
        mnAltas.add(mntmAgregarMaterial);

        JMenuItem mntmAgregarPrestamo = new JMenuItem("Agregar Prestamo");
        mntmAgregarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarPrestamoInternalFrame.setVisible(true);
            }
        });
        mnAltas.add(mntmAgregarPrestamo);

        JMenu mnModificaciones = new JMenu("Modificaciones");
        menuBar.add(mnModificaciones);

        JMenuItem mntmCambiarZonaLector = new JMenuItem("Cambiar Zona Lector");
        mntmCambiarZonaLector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarZonaLectorInternalFrame.setVisible(true);
            }
        });
        mnModificaciones.add(mntmCambiarZonaLector);

        JMenuItem mntmSuspenderUsuario = new JMenuItem("Suspender/Activar Usuario");
        mntmSuspenderUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                suspenderUsuarioInternalFrame.setVisible(true);
            }
        });
        mnModificaciones.add(mntmSuspenderUsuario);

        JMenuItem mntmCambiarEstadoPrestamo = new JMenuItem("Cambiar estado de prestamo");
        mntmCambiarEstadoPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarEstadoPrestamoInternalFrame.setVisible(true);
            }
        });
        mnModificaciones.add(mntmCambiarEstadoPrestamo);

        JMenuItem mntmActualizarPrestamo = new JMenuItem("Actualizar Préstamo");
        mntmActualizarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modPrestamoInternalFrame.setVisible(true);
            }
        });
        mnModificaciones.add(mntmActualizarPrestamo);


        JMenu mnListados = new JMenu("Listados");
        menuBar.add(mnListados);
        
        JMenuItem mntmListarDonaciones = new JMenuItem("Listar Donaciones");
        mntmListarDonaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarDonacionesInternalFrame.setVisible(true);
            }
        });
        mnListados.add(mntmListarDonaciones);

        JMenuItem mntmListarPrestamos = new JMenuItem("Listar Prestamos");
        mntmListarPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarPrestamosInternalFrame.setVisible(true);
            }
        });
        mnListados.add(mntmListarPrestamos);

        JMenuItem mntmListarPrestamosBibliotecario = new JMenuItem("Listar Prestamos (Bibliotecario)");
        mntmListarPrestamosBibliotecario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarPrestamosBibliotecarioInternalFrame.setVisible(true);
            }
        });
        mnListados.add(mntmListarPrestamosBibliotecario);

        JMenuItem mntmListarPrestamosPorZona = new JMenuItem("Listar Prestamos (Por Zona)");
        mntmListarPrestamosPorZona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarPrestamosPorZonaInternalFrame.setVisible(true);
            }
        });
        mnListados.add(mntmListarPrestamosPorZona);

        JMenuItem mntmListarCantidadVecesPrestados = new JMenuItem("Listar Cantidad Veces Prestados");
        mntmListarCantidadVecesPrestados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarCantidadVecesPrestadosInternalFrame.setVisible(true);
            }
        });
        mnListados.add(mntmListarCantidadVecesPrestados);
        JMenuItem mntmListarPrestamosActivosLector = new JMenuItem("Listar Prestamos Activos de un Lector");
        mntmListarPrestamosActivosLector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarPrestamosActivosLectorInternalFrame.setVisible(true);
            }
        });
        mnListados.add(mntmListarPrestamosActivosLector);
    }
}