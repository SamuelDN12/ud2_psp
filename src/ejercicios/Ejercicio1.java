/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios;

/**
 *
 * @author Usuario
 */

class Hilo extends Thread {

    private String id = null;

    Hilo(String id) {
        this.id = id;
    }
    
    Hilo() {}

    @Override
    public void run() {
        if (id == null) {
            System.out.println("Hola mundo");
        } else {
            System.out.println("Hola mundo - " + id);

        }
    }
}

public class Ejercicio1 {

    public static void main(String[] args) {
        Thread[] arrayHilo1 = new Thread[]{
            new Hilo(),
            new Hilo(),
            new Hilo(),
            new Hilo(),
            new Hilo()
        };

        Thread[] arrayHilo2 = new Thread[]{
            new Hilo("hilo2"),
            new Hilo("hilo2"),
            new Hilo("hilo2"),
            new Hilo("hilo2"),
            new Hilo("hilo2")
        };
        primeraEjecucion(arrayHilo1);
        segundaEjecucion(arrayHilo2);
    }

    private static void primeraEjecucion(Thread[] h) {
        for (Thread thread : h) {
            thread.start();
        }
    }

    private static void segundaEjecucion(Thread[] h) {
        for (Thread thread : h) {
            thread.start();
        }
    }

}
