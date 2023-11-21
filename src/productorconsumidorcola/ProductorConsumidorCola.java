package productorconsumidorcola;

/**
 *
 * @author DAM
 */
class Cola {

    private int numero;
    private boolean disponible = false;

    synchronized int get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        //visualizar número
        disponible = false;
        notifyAll();
        return numero;
    }

    synchronized void put(int valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        numero = valor;
        disponible = true;
        //visualizar número
        notifyAll();
    }

    synchronized boolean isDisponible() {

        return disponible;
    }

}

class Productor extends Thread {

    private Cola cola;
    private int n;

    public Productor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i); //pone el número
            System.out.println(i + "=>Productor : " + n + ", produce : " + i);

        }
    }
}

class Consumidor extends Thread {

    private Cola cola;
    private int n;

    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); //recoge el número
            System.out.println(i + "=>Consumidor: " + n + ", consume: " + valor);
        }

    }
}

public class ProductorConsumidorCola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cola cola = new Cola();
        Productor p = new Productor(cola, 1);
        Consumidor c = new Consumidor(cola, 1);
        Consumidor c2 = new Consumidor(cola, 2);

        p.start();
        c.start();
        c2.start();
    }

}
