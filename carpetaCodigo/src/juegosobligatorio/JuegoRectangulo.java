//Pablo Duran (270956) Santiago Villar (256345) 

package juegosobligatorio;

public class JuegoRectangulo extends Juego {
    
    public JuegoRectangulo(){
        super.setNombre("Juego Rectangulo");
        Tablero tablero=new Tablero(22,41,' ');
        super.setTablero(tablero);
        super.tableroDefaultRectangulo();
    }
}
