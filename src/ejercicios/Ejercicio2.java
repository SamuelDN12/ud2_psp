/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios;

/**
 *
 * @author Usuario
 */
class Hilo2 implements Runnable {

  private final String nombre;

  Hilo2(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public void run() {
    System.out.printf("Hola, soy el hilo: %s.\n", this.nombre);
    System.out.printf("Hilo %s terminado.\n", this.nombre);
  }

}

public class Ejercicio2 {

    public static void main(String[] args) {

    }

}
