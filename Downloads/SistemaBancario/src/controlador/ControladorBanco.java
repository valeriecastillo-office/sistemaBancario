package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Cuenta;
import modelo.CuentaAhorros;
import modelo.CuentaCorriente;
import modelo.Fecha;
import vista.VistaBanco;


public class ControladorBanco implements ActionListener {

    private ArrayList<Cuenta> cuentas;  
    private VistaBanco vista;

  
    public ControladorBanco(VistaBanco vista) {
        this.vista = vista;
        this.cuentas = new ArrayList<Cuenta>();
        this.vista.setEscuchador(this);
    }

    // Se ejecuta cada vez que el usuario hace clic en un botón
    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();

        if (accion.equals("CREAR")) {
            crearCuenta();
        } else if (accion.equals("CONSIGNAR")) {
            consignar();
        } else if (accion.equals("RETIRAR")) {
            retirar();
        } else if (accion.equals("SALDO")) {
            consultar(true);    // true = solo saldo
        } else if (accion.equals("INFO")) {
            consultar(false);   // false = todos los datos
        } else if (accion.equals("TIPO")) {
            // Solo la cuenta corriente usa cupo de sobregiro
            vista.activarCupo(vista.getTipo().equals("Corriente"));
        }
    }

    // Busca una cuenta por su número. Devuelve null si no existe.
    public Cuenta buscarCuenta(String numero) {
        for (Cuenta c : cuentas) {
            if (c.getNumero().equals(numero)) {
                return c;
            }
        }
        return null;
    }

    // Crea una cuenta nueva (ahorros o corriente) con los datos de la vista
    private void crearCuenta() {
        String numero = vista.getNumero();
        String nombre = vista.getNombre();

        // Validación: campos obligatorios
        if (numero.isEmpty() || nombre.isEmpty()) {
            vista.mostrarMensaje("El número y el nombre son obligatorios.");
            return;
        }
        // Validación: el número de cuenta no se puede repetir
        if (buscarCuenta(numero) != null) {
            vista.mostrarMensaje("Ya existe una cuenta con ese número.");
            return;
        }

        try {
            // Convertimos el texto a números (puede lanzar NumberFormatException)
            int dia = Integer.parseInt(vista.getDia());
            int mes = Integer.parseInt(vista.getMes());
            int anio = Integer.parseInt(vista.getAnio());
            double saldo = Double.parseDouble(vista.getSaldoInicial());

            // Validación: fecha y saldo
            if (!fechaValida(dia, mes, anio)) {
                vista.mostrarMensaje("La fecha de apertura no es válida.");
                return;
            }
            if (saldo < 0) {
                vista.mostrarMensaje("El saldo inicial no puede ser negativo.");
                return;
            }

            Fecha fecha = new Fecha(dia, mes, anio);
            Cuenta nueva;

            // Según el tipo elegido se crea una hija distinta.
            // La variable es de tipo Cuenta (padre): esto es polimorfismo.
            if (vista.getTipo().equals("Ahorros")) {
                nueva = new CuentaAhorros(numero, nombre, fecha, saldo);
            } else {
                double cupo = Double.parseDouble(vista.getCupo());
                if (cupo < 0) {
                    vista.mostrarMensaje("El cupo de sobregiro no puede ser negativo.");
                    return;
                }
                nueva = new CuentaCorriente(numero, nombre, fecha, saldo, cupo);
            }

            cuentas.add(nueva);
            vista.mostrarResultado("Cuenta creada -> " + nueva.obtenerDatos());
            vista.limpiarCampos();

        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Revise que fecha, saldo y cupo sean números válidos.");
        }
    }

    // Deposita dinero en la cuenta indicada
    private void consignar() {
        Cuenta c = buscarCuenta(vista.getNumero());
        if (c == null) {
            vista.mostrarMensaje("La cuenta no existe.");
            return;
        }
        try {
            double monto = Double.parseDouble(vista.getMonto());
            if (monto <= 0) {
                vista.mostrarMensaje("El monto debe ser mayor que cero.");
                return;
            }
            c.consignar(monto);
            vista.mostrarResultado("Consignación exitosa. " + c.obtenerDatos(true));
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Ingrese un monto numérico válido.");
        }
    }

    // Retira dinero de la cuenta indicada
    private void retirar() {
        Cuenta c = buscarCuenta(vista.getNumero());
        if (c == null) {
            vista.mostrarMensaje("La cuenta no existe.");
            return;
        }
        try {
            double monto = Double.parseDouble(vista.getMonto());

            // POLIMORFISMO: c es de tipo Cuenta, pero Java ejecuta el retirar()
            // de la clase real (CuentaAhorros o CuentaCorriente).
            if (c.retirar(monto)) {
                vista.mostrarResultado("Retiro exitoso. " + c.obtenerDatos(true));
            } else {
                vista.mostrarMensaje("Retiro rechazado: fondos o cupo insuficientes.");
            }
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Ingrese un monto numérico válido.");
        }
    }

    // Muestra solo el saldo (true) o todos los datos (false)
    private void consultar(boolean soloSaldo) {
        Cuenta c = buscarCuenta(vista.getNumero());
        if (c == null) {
            vista.mostrarMensaje("La cuenta no existe.");
            return;
        }
        vista.mostrarResultado(c.obtenerDatos(soloSaldo));
    }

    // Revisa que mes esté entre 1 y 12 y que el día exista en ese mes
    private boolean fechaValida(int dia, int mes, int anio) {
        if (anio < 1 || mes < 1 || mes > 12 || dia < 1) {
            return false;
        }
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int maximo = diasPorMes[mes - 1];

        // Febrero tiene 29 días en año bisiesto
        boolean bisiesto = (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
        if (mes == 2 && bisiesto) {
            maximo = 29;
        }
        return dia <= maximo;
    }
}
