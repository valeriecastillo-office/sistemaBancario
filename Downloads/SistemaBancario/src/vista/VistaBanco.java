/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Vista: la ventana (JFrame). Solo muestra los componentes y entrega
 * lo que el usuario escribió. No tiene lógica del banco.
 */
public class VistaBanco extends JFrame {

    private JTextField txtNumero = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextField txtDia = new JTextField(3);
    private JTextField txtMes = new JTextField(3);
    private JTextField txtAnio = new JTextField(5);
    private JTextField txtSaldoInicial = new JTextField("0");
    private JComboBox<String> cmbTipo = new JComboBox<String>(new String[]{"Ahorros", "Corriente"});
    private JTextField txtCupo = new JTextField("0");
    private JTextField txtMonto = new JTextField(10);

    private JButton btnCrear = new JButton("Crear cuenta");
    private JButton btnConsignar = new JButton("Consignar");
    private JButton btnRetirar = new JButton("Retirar");
    private JButton btnSaldo = new JButton("Consultar saldo");
    private JButton btnInfo = new JButton("Ver todos los datos");

    private JTextArea areaResultado = new JTextArea(8, 40);

    // Constructor: arma la ventana
    public VistaBanco() {
        setTitle("Sistema Bancario");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Panel con los datos de la cuenta
        JPanel panelDatos = new JPanel(new GridLayout(0, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos de la cuenta"));
        panelDatos.add(new JLabel("Número:"));
        panelDatos.add(txtNumero);
        panelDatos.add(new JLabel("Nombre del dueño:"));
        panelDatos.add(txtNombre);

        JPanel panelFecha = new JPanel();
        panelFecha.add(new JLabel("Día"));
        panelFecha.add(txtDia);
        panelFecha.add(new JLabel("Mes"));
        panelFecha.add(txtMes);
        panelFecha.add(new JLabel("Año"));
        panelFecha.add(txtAnio);
        panelDatos.add(new JLabel("Fecha de apertura:"));
        panelDatos.add(panelFecha);

        panelDatos.add(new JLabel("Saldo inicial:"));
        panelDatos.add(txtSaldoInicial);
        panelDatos.add(new JLabel("Tipo de cuenta:"));
        panelDatos.add(cmbTipo);
        panelDatos.add(new JLabel("Cupo de sobregiro:"));
        panelDatos.add(txtCupo);
        txtCupo.setEnabled(false); // al inicio es Ahorros, no usa cupo

        // Panel con los botones de operaciones
        JPanel panelOperaciones = new JPanel();
        panelOperaciones.setBorder(BorderFactory.createTitledBorder("Operaciones"));
        panelOperaciones.add(new JLabel("Monto:"));
        panelOperaciones.add(txtMonto);
        panelOperaciones.add(btnCrear);
        panelOperaciones.add(btnConsignar);
        panelOperaciones.add(btnRetirar);
        panelOperaciones.add(btnSaldo);
        panelOperaciones.add(btnInfo);

        // Área donde se muestran los resultados
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);

        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelDatos, BorderLayout.NORTH);
        panelNorte.add(panelOperaciones, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // centra la ventana
    }

    // Registra al controlador como "escucha" de los botones y del combo.
    // El actionCommand es el nombre con el que el controlador reconoce el evento.
    public void setEscuchador(ActionListener escuchador) {
        btnCrear.setActionCommand("CREAR");
        btnConsignar.setActionCommand("CONSIGNAR");
        btnRetirar.setActionCommand("RETIRAR");
        btnSaldo.setActionCommand("SALDO");
        btnInfo.setActionCommand("INFO");
        cmbTipo.setActionCommand("TIPO");

        btnCrear.addActionListener(escuchador);
        btnConsignar.addActionListener(escuchador);
        btnRetirar.addActionListener(escuchador);
        btnSaldo.addActionListener(escuchador);
        btnInfo.addActionListener(escuchador);
        cmbTipo.addActionListener(escuchador);
    }

    // Getters: devuelven lo escrito (texto). El controlador lo valida y convierte.
    public String getNumero() {
        return txtNumero.getText().trim();
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getDia() {
        return txtDia.getText().trim();
    }

    public String getMes() {
        return txtMes.getText().trim();
    }

    public String getAnio() {
        return txtAnio.getText().trim();
    }

    public String getSaldoInicial() {
        return txtSaldoInicial.getText().trim();
    }

    public String getTipo() {
        return (String) cmbTipo.getSelectedItem();
    }

    public String getCupo() {
        return txtCupo.getText().trim();
    }

    public String getMonto() {
        return txtMonto.getText().trim();
    }

    // Activa o desactiva el campo del cupo (solo sirve para cuenta corriente)
    public void activarCupo(boolean activo) {
        txtCupo.setEnabled(activo);
        if (!activo) {
            txtCupo.setText("0");
        }
    }

    // Agrega una línea de texto al área de resultados
    public void mostrarResultado(String texto) {
        areaResultado.append(texto + "\n");
    }

    // Muestra una ventanita emergente con un mensaje
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Deja los campos listos para crear otra cuenta
    public void limpiarCampos() {
        txtNumero.setText("");
        txtNombre.setText("");
        txtDia.setText("");
        txtMes.setText("");
        txtAnio.setText("");
        txtSaldoInicial.setText("0");
        txtCupo.setText("0");
        txtMonto.setText("");
    }
}
