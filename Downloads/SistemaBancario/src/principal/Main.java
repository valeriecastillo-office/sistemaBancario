package principal;

import controlador.ControladorBanco;
import vista.VistaBanco;


 
public class Main {

    public static void main(String[] args) {
        VistaBanco vista = new VistaBanco();
        ControladorBanco controlador = new ControladorBanco(vista);
        vista.setVisible(true);
    }
}
