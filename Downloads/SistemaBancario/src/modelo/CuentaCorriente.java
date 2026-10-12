package modelo;


public class CuentaCorriente extends Cuenta {

    private double cupoSobregiro;


    public CuentaCorriente(String num, String nom, Fecha f, double s, double cupoSobregiro) {
        super(num, nom, f, s);
        this.cupoSobregiro = cupoSobregiro;
    }
    public CuentaCorriente(String num, String nom, Fecha f) {
        super(num, nom, f);
        this.cupoSobregiro = 0;
    }

    public double getCupoSobregiro() {
        return cupoSobregiro;
    }

    @Override
    public boolean retirar(double valor) {

        if (valor <= 0) {
            return false;
        }

        if (valor <= saldo) {
            
            saldo = saldo - valor;
            return true;
        } else if (valor <= saldo + cupoSobregiro) {
          
            saldo = saldo - valor;
            return true;
        } else {
           
            return false;
        }
    }


    @Override
    public String obtenerDatos(boolean soloSaldo) {
        if (soloSaldo) {
            return super.obtenerDatos(true);
        }
        return "[Corriente] " + super.obtenerDatos(false)
                + " | Cupo sobregiro: $" + cupoSobregiro;
    }
}
