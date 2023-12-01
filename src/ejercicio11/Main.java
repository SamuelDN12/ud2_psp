/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
class Arbitro {

    private int numeroAdivinar;
    private boolean finalizado = false;
    private int numeroJugadores;
    private int turno;

    public Arbitro(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
        numeroAdivinar = new Random().nextInt(10) + 1;
        turno = new Random().nextInt(numeroJugadores);
        System.out.println("NÚMERO A ADIVINAR: " + numeroAdivinar);
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized boolean isFinalizado() {
        return finalizado;
    }

    public synchronized void comprobarJugada(int numeroDadoJugador, int idJugador) {
        if (idJugador == turno) {
            System.out.println("Jugador " + (idJugador + 1) + " dice: " + numeroDadoJugador);

            if (numeroDadoJugador == numeroAdivinar) {
                finalizado = true;
                System.out.println("¡El jugador " + (idJugador + 1) + " ha adivinado el número!");
            } else {
                turno++;
                if (turno == numeroJugadores) {
                    turno = 0;
                }
                System.out.println("Le toca al jugador " + (turno + 1));
            }
        }
    }

}

class Jugador extends Thread {

    public int idJugador;
    public Arbitro arbitro;

    public Jugador(int idJugador, Arbitro arbitro) {
        this.idJugador = idJugador;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {
        while (!arbitro.isFinalizado()) {
            int numeroAdivinar = new Random().nextInt(10) + 1;
            arbitro.comprobarJugada(numeroAdivinar, idJugador);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el número de jugadores:");
        int numero = Integer.parseInt(sc.nextLine());
        ArrayList<Jugador> lista = new ArrayList<>();
        Arbitro arbitro = new Arbitro(numero);
        for (int i = 0; i < numero; i++) {
            lista.add(new Jugador(i, arbitro));
        }
        for (Jugador jugador : lista) {
            jugador.start();

        }
    }
}
