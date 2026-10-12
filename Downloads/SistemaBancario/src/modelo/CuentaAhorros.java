/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * CuentaAhorros hereda de Cuenta (extends).
 * Regla: solo se puede retirar lo que hay en el saldo.
 */
public class CuentaAhorros extends Cuenta {

    // Constructor: llama al constructor del padre con super
    public CuentaAhorros(String num, String nom, Fecha f, double s) {
        super(num, nom, f, s);
    }

    // SOBRECARGA de constructor: cuenta de ahorros con saldo inicial 0
    public CuentaAhorros(String num, String nom, Fecha f) {
        super(num, nom, f);
    }

    // Implementación de retirar() para ahorros (polimorfismo).
    // Retorna true si el retiro se hizo y false si fue rechazado.
    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo = saldo - valor;   // hay saldo suficiente
            return true;
        }
        return false;                // no alcanza el saldo
    }

    // Texto con el tipo de cuenta (sobreescritura del método del padre)
    @Override
    public String obtenerDatos(boolean soloSaldo) {
        if (soloSaldo) {
            return super.obtenerDatos(true);
        }
        return "[Ahorros] " + super.obtenerDatos(false);
    }
}