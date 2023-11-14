/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

/**
 *
 * @author DAM
 */
class HiloPrioridad extends Thread {
  private int c = 0;
  private boolean stopHilo = false;
  public long getContador () {
    return c;
  }
  public void pararHilo() {
    stopHilo = true;
  }
  @Override
  public void run() {
    while (!stopHilo) c++;
  }
}

public class EjemploHiloPrioridad {
  public static void main(String args[]) {
    HiloPrioridad h1 = new HiloPrioridad();
    HiloPrioridad h2 = new HiloPrioridad();
    HiloPrioridad h3 = new HiloPrioridad();

    h1.setPriority(Thread.NORM_PRIORITY);
    h2.setPriority(Thread.MAX_PRIORITY);
    h3.setPriority(Thread.MIN_PRIORITY);

    h1.start();
    h2.start();
    h3.start();

    try {
      Thread.sleep(10000);
        } catch (InterruptedException e) {}

    h1.pararHilo();
    h2.pararHilo();
    h3.pararHilo();

    System.out.println("h2 (Prio. Máx: "+h2.getContador());
    System.out.println("h1 (Prio. Normal: "+h1.getContador());
    System.out.println("h3 (Prio. Mínima: "+h3.getContador());
    
    
  }
}
