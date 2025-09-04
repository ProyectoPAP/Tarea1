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
import interfaces.ICtrlListarPrestActXLector;
import presentacion.AgregarPrestamo;
import presentacion.ListarPrestamos;
import presentacion.ListarPrestamosActivosLector;

public class Principal {
    private JFrame frame;

    private AgregarUsuario agregarUsuarioInternalFrame;
    private AgregarMaterial agregarMaterialInternalFrame;
    private ListarDonaciones listarDonacionesInternalFrame;
    private AgregarPrestamo agregarPrestamoInternalFrame;
    private ListarPrestamos listarPrestamosInternalFrame;
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
        ICtrlListarPrestActXLector iCtrlListarPreActLector = fabrica.getCtrlListarPreActLector();

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

        listarPrestamosActivosLectorInternalFrame = new ListarPrestamosActivosLector(iCtrlListarPreActLector);
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

        JMenu mnModificaciones = new JMenu("Modificaciones");
        menuBar.add(mnModificaciones);

        JMenuItem mntmAgregarPrestamo = new JMenuItem("Agregar Prestamo");
        mntmAgregarPrestamo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarPrestamoInternalFrame.setVisible(true);
            }
        });
        mnModificaciones.add(mntmAgregarPrestamo);


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

        JMenuItem mntmListarPrestamosActivosLector = new JMenuItem("Listar Préstamos Activos por Lector");
        mntmListarPrestamosActivosLector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarPrestamosActivosLectorInternalFrame.setVisible(true);
            }
        });
        mnListados.add(mntmListarPrestamosActivosLector);
    }
}