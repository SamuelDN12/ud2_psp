/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        NumeroOculto numeroOculto = new NumeroOculto(33);
        ArrayList<Hilo> hilos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Hilo hilo = new Hilo(i + 1, numeroOculto);
            hilos.add(hilo);
        }
        
        for (Hilo hilo : hilos) {
            hilo.start();
            hilo.join();
        }
        
        
    }

}
