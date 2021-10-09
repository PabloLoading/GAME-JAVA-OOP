
package juegosobligatorio;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;


public class JuegoSaltar extends Juego{
    private String nombre;
    
    public JuegoSaltar(){
        this.nombre="Saltar";
        Tablero tablero=new Tablero(24,12,' ');
        super.setTablero(tablero);
        super.tableroDefaultSaltar();
    }
    public int calcularPuntaje(){
        return 5;
    }
}

