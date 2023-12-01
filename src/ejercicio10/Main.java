/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio10;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;




class Recurso {
    private ArrayList<Integer> lista = new ArrayList<>();
    private int contador = 0;
    
    synchronized public void put() {
        while (lista.size() > 7) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Depositando el producto " + contador);
        lista.add(contador);
        contador++;
        notify();
    }
    
     synchronized public void get() {
        while (lista.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Recogiendo el producto " + lista.get(0));
        lista.remove(0);
        notify();
    }
    
}

class Productor extends Thread {
    
    Recurso recu; 

    public Productor(Recurso recu) {
        this.recu = recu;
    }
    
    @Override
    public void run() {
        while (true) {
            recu.put();
        }
    }
    
}

class Consumidor extends Thread {
    
    Recurso recu; 

    public Consumidor(Recurso recu) {
        this.recu = recu;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            recu.get();
        }
    }
    
}

public class Main {

    public static void main(String[] args) {
        Recurso recu= new Recurso();
        Productor productor = new Productor(recu);
        Consumidor consumidor = new Consumidor(recu);
        
        productor.start();
        consumidor.start();
        
        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }


    
}
