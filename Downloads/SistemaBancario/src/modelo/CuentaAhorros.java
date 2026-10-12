/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class CuentaAhorros extends Cuenta {


    public CuentaAhorros(String num, String nom, Fecha f, double s) {
        super(num, nom, f, s);
    }

  
    public CuentaAhorros(String num, String nom, Fecha f) {
        super(num, nom, f);
    }

   
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