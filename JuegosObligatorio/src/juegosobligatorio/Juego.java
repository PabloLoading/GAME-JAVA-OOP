
package juegosobligatorio;

public class Juego {
    private Tablero tablero;
    
    public void tableroVacio(){
        String mat[][]=this.tablero.getMatriz();
        
        //espacio doble en col de los puntajes, asi printea prolijo
        for (int i = 0; i < mat.length; i++) {
            mat[i][0]="  ";
        }
        //columnas indicadas
        mat[0][4]="1";
        mat[0][6]="2";
        mat[0][8]="3";
        mat[0][10]="4";
        //Ponemos puntajes
        mat[23][0]="60";
        mat[21][0]="40";
        mat[19][0]="30";
        mat[17][0]="20";
        mat[15][0]="10";
        //Dibujamos las lineas del tablero
        for (int i=1; i < mat.length; i++) {
            for (int j = 3; j < mat[0].length; j++) {
                if(i%2!=0){
                    if(j%2!=0){
                        mat[i][j]="+";
                    }
                    else mat[i][j]="-";
                }
                else{
                    if(j%2!=0){
                        mat[i][j]="|";
                    }
                }
            }
        }
        
        for (int i = 2; i < 9; i=i+2) {
            for (int j = 4; j < mat[0].length;j=j+2) {
                mat[i][j]="#";
            }
        }
        
        this.tablero.setMatriz(mat);
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    
}
