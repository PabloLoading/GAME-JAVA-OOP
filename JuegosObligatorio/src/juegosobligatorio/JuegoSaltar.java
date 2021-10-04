
package juegosobligatorio;


public class JuegoSaltar extends Juego{
    private String nombre;
    private Tablero tablero;
    
    public JuegoSaltar(){
        this.nombre="Saltar";
        this.tablero=new Tablero(11,4,'*');
        jugarSaltar();
    }
    public void jugarSaltar(){
        this.tablero.mostrarTablero();
    }
    
    
    
}

