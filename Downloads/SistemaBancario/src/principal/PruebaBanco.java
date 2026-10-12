package principal;

import modelo.Cuenta;
import modelo.CuentaAhorros;
import modelo.CuentaCorriente;
import modelo.Fecha;


 
public class PruebaBanco {

    
    static void probar(String caso, boolean resultado) {
        if (resultado) {
            System.out.println("OK     " + caso);
        } else {
            System.out.println("FALLÓ  " + caso);
        }
    }

    public static void main(String[] args) {
        Fecha f = new Fecha(5, 3, 2026);

        
        Cuenta a = new CuentaAhorros("A1", "Ana", f, 100);
        probar("Ahorros: retirar 60 de 100 -> saldo 40", a.retirar(60) && a.getSaldo() == 40);
        probar("Ahorros: retirar 50 de 40 se rechaza", !a.retirar(50) && a.getSaldo() == 40);
        a.consignar(10);
        probar("Ahorros: consignar 10 -> saldo 50", a.getSaldo() == 50);

        Cuenta c = new CuentaCorriente("C1", "Luis", f, 100, 200);
        probar("Corriente: retirar 80 (alcanza saldo) -> saldo 20", c.retirar(80) && c.getSaldo() == 20);
        probar("Corriente: retirar 150 (usa sobregiro) -> saldo -130", c.retirar(150) && c.getSaldo() == -130);
        probar("Corriente: retirar 100 (excede cupo) se rechaza", !c.retirar(100) && c.getSaldo() == -130);
        probar("Corriente: retirar 70 (justo el límite) -> saldo -200", c.retirar(70) && c.getSaldo() == -200);
        probar("Corriente: retirar 1 (sin cupo) se rechaza", !c.retirar(1));

        
        Cuenta d = new CuentaAhorros("A2", "Eva", f);
        probar("Constructor sobrecargado: saldo inicial 0", d.getSaldo() == 0);
        probar("obtenerDatos(true) muestra solo saldo", d.obtenerDatos(true).startsWith("Saldo:"));
        probar("obtenerDatos() muestra todos los datos", d.obtenerDatos().contains("Dueño: Eva"));
        probar("Corriente muestra el cupo", c.obtenerDatos().contains("Cupo sobregiro"));
    }
}
