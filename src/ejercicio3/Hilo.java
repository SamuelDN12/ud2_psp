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

/**
 *
 * @author Usuario
 */
public class Hilo extends Thread {

    private String rutaFichero;
    private int caracteres = 0;
    private long t_comienzo, t_fin;

    public Hilo(String ruta) {
        this.rutaFichero = ruta;
    }

    public void contarCaracteres() throws FileNotFoundException, IOException {
        t_comienzo = System.currentTimeMillis();

        BufferedReader bf = new BufferedReader(new FileReader(new File(rutaFichero)));
        String linea;
        while ((linea = bf.readLine()) != null) {
            char[] caracteresArray = linea.toCharArray();
            caracteres += caracteresArray.length;
        }
        t_fin = System.currentTimeMillis();

        long tiempototal = t_fin - t_comienzo;

        System.out.println("El proceso ha tardado: " + tiempototal + " milisegundos\nEl archivo " + rutaFichero + " tiene " + caracteres + " caracteres.\n");
    }

}
