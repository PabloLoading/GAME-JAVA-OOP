
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
    public int calcularPuntaje(){
        return 5;
    }
    
}

