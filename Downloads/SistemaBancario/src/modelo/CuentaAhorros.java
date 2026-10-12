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
            saldo = saldo - valor;   
            return true;
        }
        return false;                
    }

    @Override
    public String obtenerDatos(boolean soloSaldo) {
        if (soloSaldo) {
            return super.obtenerDatos(true);
        }
        return "[Ahorros] " + super.obtenerDatos(false);
    }
}
