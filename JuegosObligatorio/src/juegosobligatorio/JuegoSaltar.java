
package juegosobligatorio;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;


public class JuegoSaltar extends Juego{
    private String nombre;
    
    public JuegoSaltar(){
        this.nombre="Saltar";
        Tablero tablero=new Tablero(24,12,' ');
        super.setTablero(tablero);
    }
    
    //Funciones solo declaradas, hay que programarlas bien
    public boolean jugadaValida(int jugada){
        return false;
    }
    public boolean colorConJugadas(){
        boolean existeJugada=false;
        for (int i = 1; i < 5 && !existeJugada; i++) {
            if(jugadaValida(i)){
                existeJugada=true;
            }
        }
        return existeJugada;
    }
    
    public int calcularPuntaje(){
        return 5;
    }
    public boolean jugadaValida(){
        return false;
    }
}

