/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filosofosComensales;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author DAM
 */
public class Filosofo extends Thread {

    private Tenedor izquierdo;
    private Tenedor derecho;
    private final int id;
    private final int factorPeso;
    private Random rand = new Random(47);

    private void pausa() throws InterruptedException {
        if (factorPeso == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(factorPeso * 250));
    }

    public Filosofo(Tenedor izquierdo, Tenedor derecho, int id, int peso) {
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.id = id;
        factorPeso = peso;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "pensando");
                pausa();
                System.out.println(this + " " + "cogiendo tenedor derecho");
                derecho.tomar();
                System.out.println(this + " " + "cogiendo tenedor izquierdo");
                izquierdo.tomar();
                System.out.println(this + " " + "comiendo");
                pausa();
                derecho.dejar();
                izquierdo.dejar();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "saliendo por una interrupción");
        }
    }

    public String toString() {
        return "Filósofo " + (id+1);
    }
    
}