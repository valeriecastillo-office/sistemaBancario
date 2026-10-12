package modelo;


public abstract class Cuenta {

    private String numero;
    private String nombreDuenio;
    private Fecha fechaApertura;  
    protected double saldo;      

   
    public Cuenta(String num, String nom, Fecha f, double s) {
        this.numero = num;
        this.nombreDuenio = nom;
        this.fechaApertura = f;
        this.saldo = s;
    }

   
    // Crea la cuenta con saldo inicial 0.
    public Cuenta(String num, String nom, Fecha f) {
        this(num, nom, f, 0);
    }

    public void consignar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
        }
    }

  
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
