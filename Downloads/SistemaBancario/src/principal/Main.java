package principal;

import controlador.ControladorBanco;
import vista.VistaBanco;


 // miin el prinviapl
public class Main {

    public static void main(String[] args) {
        VistaBanco vista = new VistaBanco();
        ControladorBanco controlador = new ControladorBanco(vista);
        vista.setVisible(true);
    }
}
