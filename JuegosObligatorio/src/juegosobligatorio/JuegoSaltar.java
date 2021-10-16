//Pablo Duran (270956) Santiago Villar (256345) 

package juegosobligatorio;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;


public class JuegoSaltar extends Juego{
    public JuegoSaltar(){
        super.setNombre("Juego Saltar");
        Tablero tablero=new Tablero(24,12,' ');
        super.setTablero(tablero);
        super.tableroDefaultSaltar();
    }
    
}

