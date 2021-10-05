/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegosobligatorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Sistema {
    
    ArrayList<Jugador> listaJugadores=new ArrayList<Jugador>();
        ArrayList<Partida> listaPartidas=new ArrayList<Partida>();

    public Sistema(){
        listaJugadores = new ArrayList<>();
        listaPartidas = new ArrayList<>();
    }
    public ArrayList<Jugador> getListaJugadores(){
        return listaJugadores;}    
    
    public ArrayList<Partida> getListaPartidas(){
        return listaPartidas;}
    
    public void ingresarJugador(Jugador unJugador){
        listaJugadores.add(unJugador);
    } 
    
        public void mostrarPartidas() {
        System.out.println("\nLista de Partidas");
        for (Partida unaPartida : listaPartidas) {
            System.out.println(unaPartida);
        }
        System.out.println("");
    }
    
        public void ordenarPorPuntaje() {
        //Collections.sort(listaGastos, new CriterioMonto());
        listaPartidas.sort(new CriterioPuntaje());
    }

    public void ordenarPorAlias() {
        Collections.sort(listaPartidas, new Comparator<Partida>(){
            public int compare(Partida partida1,Partida partida2){
                return partida1.jugador.getAlias().compareTo(partida2.jugador.getAlias());
            }
        });
    }

    private class CriterioPuntaje implements Comparator<Partida> {

        @Override
        public int compare(Partida partida1, Partida partida2) {
            return partida2.getPuntaje() - partida1.getPuntaje();
        }
    }
}
