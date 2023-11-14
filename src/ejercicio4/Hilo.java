/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Hilo extends Thread {

    private NumeroOculto numeroOculto;
    private int identificador;
    private int numeroPropuesto;

    public Hilo(int identificador, NumeroOculto numeroOculto) {
        this.numeroOculto = numeroOculto;
        this.identificador = identificador;
    }

    @Override
    public void run() {
        try {
            HiloAdivina();
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void HiloAdivina() throws InterruptedException {
        while (!numeroOculto.haAdivinado()) {
            numeroPropuesto = new Random().nextInt(101);
            int resultado = numeroOculto.propuestaNumero(numeroPropuesto);
            System.out.println("Hilo " + identificador + ": " + numeroPropuesto);
            if (resultado == 1) {
                System.out.println("El hilo " + identificador + " ha acertado el número: " + numeroPropuesto);
            }
        }
    }

}
