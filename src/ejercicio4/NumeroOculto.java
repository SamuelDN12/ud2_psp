/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

/**
 *
 * @author Usuario
 */
public class NumeroOculto {

    private final int numero;
    private boolean adivinado;

    public NumeroOculto(int numero) {
        this.numero = numero;
        adivinado = false;
    }

    public synchronized int propuestaNumero(int num) throws InterruptedException {
        int respuesta = num == numero ? 1 : adivinado ? -1 : 0;

        if (num != numero) {
            try {
                // Nos bloqueamos hasta que se produzca una notificación
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notifyAll();
        return respuesta;
    }

    public boolean haAdivinado() {
        return adivinado;
    }

}
