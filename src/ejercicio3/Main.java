/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) throws IOException {
        hilos(args);
        secuencial(args);
    }

    private static void secuencial(String[] args) throws FileNotFoundException, IOException {
        System.out.println("\nPROCESO HECHO SECUENCIAL");
        long t_comienzo, t_fin;
        t_comienzo = System.currentTimeMillis();

        for (String arg : args) {
            int caracteres = 0;

            BufferedReader bf = new BufferedReader(new FileReader(new File(arg)));
            String linea;
            while ((linea = bf.readLine()) != null) {
                char[] caracteresArray = linea.toCharArray();
                caracteres += caracteresArray.length;
            }
            t_fin = System.currentTimeMillis();

            long tiempototal = t_fin - t_comienzo;

            System.out.println("El proceso ha tardado: " + tiempototal + " milisegundos\nEl archivo " + arg + " tiene " + caracteres + " caracteres.\n");
        }

    }

    private static void hilos(String[] args) throws IOException {
        System.out.println("PROCESO HECHO CON HILOS");
        ArrayList<Thread> hilos = new ArrayList<>();
        for (String arg : args) {
            Hilo hilo = new Hilo(arg);
            hilos.add(hilo);
        }

        for (Thread hilo : hilos) {
            Hilo hilo1 = (Hilo) hilo;
            hilo1.contarCaracteres();
        }
    }

}
