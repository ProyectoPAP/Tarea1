import java.util.Date;
import interfaces.ICtrlAltaUsuario;
import logica.Bibliotecario;
import logica.Lector;
import logica.ManejadorUsuario;
import logica.Usuario;
import net.bytebuddy.implementation.bind.annotation.Super;
public class CtrlAltaUsuario implements ICtrlAltaUsuario{
        public CtrlAltaUsuario(){
            super();
        }
        private String nombre;
        private String correo;
        @Override
        public void altaUsuario(String nombre, String correo, String tipo){
            this.nombre=nombre;
            this.correo=correo;
        }
        @Override
        public void crearLector(String direccion, DtFecha fechaRegistro, estadoUsuario estado, zona zona){
            ManejadorLector ml=new ManejadorLector.getInstancia();
            Lector Lector=ml.buscarLector(this.correo);
            if(Lector!=null)
                System.out.println("0");
            Lector=new Lector(this.nombre,this.correo,direccion,fechaRegistro,estado,zona);
            ml.agregarLector(Lector);
        }

        @Override
        public void crearBibliotecario(int numEmpleado, zona zona){
            ManejadorBibliotecario mb=new ManejadorBibliotecario.getInstancia();
            Bibliotecario Bibliotecario=mb.buscarBibliotecario(this.nombre);
            if(Bibliotecario!=null)
                System.out.println("0");
            Bibliotecario=new Bibliotecario(this.nombre,this.correo,numEmpleado); //falta zona
            mb.agregarBibliotecario(Bibliotecario);     
        }
}
