
package juegosobligatorio;

import java.util.concurrent.ThreadLocalRandom;


public class JuegoSaltar extends Juego{
    private String nombre;
    
    public JuegoSaltar(){
        this.nombre="Saltar";
        super.setTablero(new Tablero(11,4,'*'));
    }
    
    public void tableroRandom(){
        char mat[][]=super.getTablero().getMatriz();
        mat[0][0]=1;
        mat[0][1]=2;
        mat[0][2]=3;
        mat[0][3]=4;
        for (int i = 1; i < 5; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            

        }
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

