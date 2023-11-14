/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class NumeroOculto {

    private final int numero;
    private boolean adivinado;

    public NumeroOculto(int numero) {
        this.numero = numero;
    }

    public synchronized int propuestaNumero(int num) {
        while (!adivinado) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(NumeroOculto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int respuesta = 0;
        if (num == numero) {
            respuesta = 1;
            adivinado = true;
        }
        return respuesta;
    }

    public boolean haAdivinado() {
        return adivinado;
    }

}
