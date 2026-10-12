/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * CuentaCorriente hereda de Cuenta (extends).
 * Regla: puede quedar en saldo negativo hasta llegar al cupo de sobregiro.
 */
public class CuentaCorriente extends Cuenta {

    private double cupoSobregiro;

    // Constructor: usa super para los datos de la cuenta y guarda el cupo
    public CuentaCorriente(String num, String nom, Fecha f, double s, double cupoSobregiro) {
        super(num, nom, f, s);
        this.cupoSobregiro = cupoSobregiro;
    }

    // SOBRECARGA de constructor: saldo inicial 0 y cupo 0
    public CuentaCorriente(String num, String nom, Fecha f) {
        super(num, nom, f);
        this.cupoSobregiro = 0;
    }

    public double getCupoSobregiro() {
        return cupoSobregiro;
    }

    // Implementación de retirar() para corriente (polimorfismo).
    // Sigue el flujo del retiro con sobregiro.
    @Override
    public boolean retirar(double valor) {
        // El valor debe ser positivo
        if (valor <= 0) {
            return false;
        }

        if (valor <= saldo) {
            // Caso 1: el saldo alcanza, es un retiro normal
            saldo = saldo - valor;
            return true;
        } else if (valor <= saldo + cupoSobregiro) {
            // Caso 2: el saldo no alcanza, pero saldo + cupo sí cubre el valor.
            // El saldo queda negativo (sobregiro permitido)
            saldo = saldo - valor;
            return true;
        } else {
            // Caso 3: el valor supera saldo + cupo, se rechaza el retiro
            return false;
        }
    }

    // Sobreescritura: agrega el cupo de sobregiro a la información
    @Override
    public String obtenerDatos(boolean soloSaldo) {
        if (soloSaldo) {
            return super.obtenerDatos(true);
        }
        return "[Corriente] " + super.obtenerDatos(false)
                + " | Cupo sobregiro: $" + cupoSobregiro;
    }
}
