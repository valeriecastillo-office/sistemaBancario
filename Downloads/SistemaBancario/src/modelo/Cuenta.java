/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

//agregar todas las clses

public abstract class Cuenta {

    private String numero;
    private String nombreDuenio;
    private Fecha fechaApertura;  // Composición: la cuenta "tiene" una Fecha
    protected double saldo;       // protected: las hijas pueden modificarlo

    // Constructor principal
    public Cuenta(String num, String nom, Fecha f, double s) {
        this.numero = num;
        this.nombreDuenio = nom;
        this.fechaApertura = f;
        this.saldo = s;
    }

    // SOBRECARGA de constructor: mismo nombre, otros parámetros.
    // Crea la cuenta con saldo inicial 0.
    public Cuenta(String num, String nom, Fecha f) {
        this(num, nom, f, 0);
    }

    // Suma el valor al saldo (solo si el valor es positivo)
    public void consignar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
        }
    }

    // Método abstracto: no tiene código aquí. Cada hija lo implementa
    // a su manera (POLIMORFISMO). Devuelve true si el retiro se pudo hacer.
    public abstract boolean retirar(double valor);

    public String getNumero() {
        return numero;
    }

    public String getNombreDuenio() {
        return nombreDuenio;
    }

    public Fecha getFechaApertura() {
        return fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }


    public String obtenerDatos() {
        return obtenerDatos(false);
    }

    public String obtenerDatos(boolean soloSaldo) {
        if (soloSaldo) {
            return "Saldo: $" + saldo;
        }
        return "Cuenta N° " + numero
                + " | Dueño: " + nombreDuenio
                + " | Apertura: " + fechaApertura
                + " | Saldo: $" + saldo;
    }
}
