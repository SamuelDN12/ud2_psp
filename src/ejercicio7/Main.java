/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio7;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
class Cadena {

    private char[] cadena = new char[6];
    private int contador = 0;
    private int elementos = 0;

    synchronized void put(char c) {
        while (elementos > 6) {
            try {
                wait();
            } catch (Exception e) {

            }
        }

        cadena[contador] = c;
        System.out.println("Depositado el caracter " + cadena[contador] + " del búfer");
        contador++;
        elementos++;

        notifyAll();
    }

    synchronized char get() {
        while (elementos == 0) {
            try {
                wait();
            } catch (Exception e) {

            }
        }

        char caracter = cadena[--contador];
        System.out.printf("Recogido el caracter %s del buffer\n", caracter);
        elementos--;
        notifyAll();
        return caracter;

    }

}

class Productor extends Thread {

    private String abc = "abcdefghijklmnñopqrstuvwxyz";

    private Cadena cadena;

    public Productor(Cadena cadena) {
        this.cadena = cadena;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            char caracter = abc.charAt(new Random().nextInt(abc.length()));
            cadena.put(caracter);
        }
    }

}

class Consumidor extends Thread {

    private Cadena cadena;

    char letraConsumida;

    public Consumidor(Cadena cadena) {
        this.cadena = cadena;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            char c = cadena.get();
        }
    }

}

public class Main {

    public static void main(String[] args) {
        try {
            Cadena cadena = new Cadena();
            Productor productor = new Productor(cadena);
            Consumidor consumidor = new Consumidor(cadena);

            productor.start();
            consumidor.start();

            productor.join();
            consumidor.join();

        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
