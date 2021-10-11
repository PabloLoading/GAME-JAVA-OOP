

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
    public void crearJuegoRectangulo(){
        Juego juego=new JuegoRectangulo();
        this.partidaActual=new Partida(juego);
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }
    public Partida getPartidaActual(){
        return this.partidaActual;
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

    public void agregarPartidaActual(){
        this.listaPartidas.add(this.partidaActual);
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
    
    
    public void ponerPuntaje(int puntaje){
        this.partidaActual.setPuntaje(puntaje);
    }
    
    
    public boolean colorJugableSaltarS(char letraColor){
        return this.partidaActual.getJuego().colorJugableSaltar(letraColor);
    }
    public boolean colJugableSaltarS(char letraColor,int col){
        return this.partidaActual.getJuego().colJugableSaltar(letraColor,col);
    }
    public String ayudaSaltarS(char letraColor){
        return this.partidaActual.getJuego().ayudaSaltar(letraColor);
    }
    public void hacerJugadaSaltarS(char letraColor,int col){
        this.partidaActual.getJuego().hacerJugadaSaltar(letraColor,col);
    }
    public boolean ganoSaltarS(){
        return this.partidaActual.getJuego().ganoSaltar();
    }
    public int calcularPuntajeSaltarS(){
        return this.partidaActual.getJuego().calcularPuntajeSaltar();
    }
    public String mostrarErrorSaltarS(char letraColor, int col){
        return this.partidaActual.getJuego().mostrarErrorSaltar(letraColor,col);
    }
    
    public void tableroRandomSaltarS(){
        this.partidaActual.getJuego().tableroRandomSaltar();
    }
    
    
    public boolean jugadaValidaRectanguloS(String respuesta[],char letraColor,boolean primeraJugada){
        return this.partidaActual.getJuego().jugadaValidaRectangulo(respuesta,letraColor,primeraJugada);
    }
    public String mostrarErrorRectanguloS(String respuesta[],char letraColor,boolean primeraJugada){
       return this.partidaActual.getJuego().mostrarErrorRectangulo(respuesta, letraColor, primeraJugada);
    }
    public void hacerJugadaRectanguloS(String respuesta[],char letraColor){
        this.partidaActual.getJuego().hacerJugadaRectangulo(respuesta, letraColor);
    }
    public int calcularPuntajeRectanguloS(){
        return this.partidaActual.getJuego().calcularPuntajeRectangulo();
    }
    public void tableroRandomRectanguloS(){
        this.partidaActual.getJuego().tableroRandomRectangulo();
    }
    public boolean hayJugadaRectanguloS(String respuesta[]){
        return this.partidaActual.getJuego().hayJugadaRectangulo(respuesta);
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
