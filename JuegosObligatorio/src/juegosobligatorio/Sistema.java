

package juegosobligatorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Sistema {
    
    private ArrayList<Jugador> listaJugadores=new ArrayList<Jugador>();
    private ArrayList<Partida> listaPartidas=new ArrayList<Partida>();
    private Partida partidaActual;

    
    public Sistema(){
        listaJugadores = new ArrayList<>();
        listaPartidas = new ArrayList<>();
    }
    public ArrayList<Jugador> getListaJugadores(){
        return listaJugadores;}    
    
    public ArrayList<Partida> getListaPartidas(){
        return listaPartidas;}
    
    public void crearJuegoSaltar(){
        Juego juego=new JuegoSaltar();
        this.partidaActual=new Partida(juego);
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }
    
    
    public void setearJugador(Jugador jugador){
        this.partidaActual.setJugador(jugador);
    }
    
    public Tablero getTableroActual(){
        return this.partidaActual.getJuego().getTablero();
    }
    
    public void ordenarPorPuntaje() {
        //Collections.sort(listaGastos, new CriterioMonto());
        listaPartidas.sort(new CriterioPuntaje());
    }
    
    public void agregarJugador(Jugador jugador){
        this.listaJugadores.add(jugador);
    }
    public boolean existeAlias(String alias){
        boolean existe=false;
        for(Jugador jugador : this.listaJugadores){
            if(jugador.getAlias().equals(alias)){
                existe=true;
            }
        }
        return existe;
    }
    
    public Jugador buscarJugadorAlias(String alias){
        Jugador jugadorHallado=new Jugador("x",1,"x");
        for(Jugador jugador : this.listaJugadores){
            if(jugador.getAlias()==alias){
                jugadorHallado=jugador;
            }
        }
        return jugadorHallado;
    }
    
    public void ordenarPorAlias() {
        Collections.sort(listaPartidas, new Comparator<Partida>(){
            public int compare(Partida partida1,Partida partida2){
                return partida1.jugador.getAlias().compareTo(partida2.jugador.getAlias());
            }
        });
    }
    
    
    public boolean aliasUnico(String alias){
        boolean unico=true;
        for (int i = 0; i < this.listaJugadores.size() && unico; i++) {
            if(this.listaJugadores.get(i).getAlias()==alias){
                unico=false;
            }
        }
        return unico;
    }
    
    
    private class CriterioPuntaje implements Comparator<Partida> {

        @Override
        public int compare(Partida partida1, Partida partida2) {
            return partida2.getPuntaje() - partida1.getPuntaje();
        }
    }
    
    
    
}
