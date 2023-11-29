/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio8;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


class Producto {
    
}

class Recurso {
    private ArrayList<Integer> lista = new ArrayList<>();
    private int contador = 0;
    
    synchronized public void add() {
        while (lista.size() > 6) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Depositando el producto " + contador);
        lista.add(contador);
        contador++;
        notifyAll();
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
        notifyAll();
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
            recu.add();
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
