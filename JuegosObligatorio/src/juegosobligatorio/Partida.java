
package juegosobligatorio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Partida {
    private String inicio;
    public Jugador jugador;
    private Juego juego;
    private int puntaje;

    public Partida(Juego juego){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        this.juego=juego;
        this.inicio=dtf.format(LocalDateTime.now());
        this.puntaje=0;
    }
    public Juego getJuego(){
        return this.juego;
    }
    public int getPuntaje(){
        return this.puntaje;
    }
    public void setPuntaje(int puntaje){
        this.puntaje=puntaje;
    }
    public void setJugador(Jugador jugador){
        this.jugador=jugador;
    }
}
