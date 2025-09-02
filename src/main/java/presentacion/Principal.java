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

public class Principal {
    private JFrame frame;

    private AgregarUsuario agregarUsuarioInternalFrame;
    private AgregarMaterial agregarMaterialInternalFrame;
    private ListarDonaciones listarDonacionesInternalFrame;

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



        JMenu mnListados = new JMenu("Listados");
        menuBar.add(mnListados);
        
        JMenuItem mntmListarDonaciones = new JMenuItem("Listar Donaciones");
        mntmListarDonaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarDonacionesInternalFrame.setVisible(true);
            }
        });
        mnListados.add(mntmListarDonaciones);
    }
}